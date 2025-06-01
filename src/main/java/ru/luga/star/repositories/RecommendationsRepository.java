package ru.luga.star.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.luga.star.model.dto.recommendation.UserDto;
import ru.luga.star.model.useractivity.UserActivity;
import ru.luga.star.model.useractivity.UserActivityProfile;

import java.util.List;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate recommendationsJdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate recommendationsJdbcTemplate) {
        this.recommendationsJdbcTemplate = recommendationsJdbcTemplate;
    }

    /**
     *  Возвращает пользователя по логину
     */
    @Cacheable(value = "User", key = "{#userLogin}", condition = "#result != null")
    public UserDto findUserByLogin(String userLogin) {
        String sql = "SELECT\n" +
                "    ID AS \"USER_ID\",\n" +
                "    USERNAME AS \"USER_LOGIN\",\n" +
                "    CONCAT(FIRST_NAME, ' ', LAST_NAME) AS \"USER_FULL_NAME\"\n" +
                "FROM USERS WHERE USERNAME = ?";

        List<UserDto> result = recommendationsJdbcTemplate.query(sql, (resultset, rownum) -> {
            UserDto user = new UserDto(
                resultset.getString("USER_ID"),
                resultset.getString("USER_LOGIN"),
                resultset.getString("USER_FULL_NAME")
            );
            return user;
        }, userLogin);

        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    /**
     * Возвращает профиль активности пользователя по типам продуктов
     */
    @Cacheable(value = "UserActivityProfile", key = "{#userId}")
    public UserActivityProfile getUserActivityProfile(String userId) {
        String sql= "SELECT\n" +
                "\tp.\"TYPE\" AS \"PRODUCT_TYPE\",\n" +
                "\tt.\"TYPE\" AS \"TRANSACTION_TYPE\",\n" +
                "\tSUM(t.AMOUNT) AS \"AMOUNT\",\n" +
                "\tCOUNT(t.ID) AS \"TRANSACTION_COUNT\"\n" +
                "FROM\n" +
                "\tTRANSACTIONS t\n" +
                "\t\tINNER JOIN PRODUCTS p ON t.PRODUCT_ID = p.ID \n" +
                "WHERE\n" +
                "\tt.USER_ID = ?\n" +
                "GROUP BY\n" +
                "\tp.\"TYPE\", t.\"TYPE\"";

        List<UserActivity> userActivities = recommendationsJdbcTemplate.query(sql, (resultSet, rowNum) -> {
            UserActivity userActivity = new UserActivity();
            userActivity.setProductType(resultSet.getString("PRODUCT_TYPE"));
            userActivity.setTransactonType(resultSet.getString("TRANSACTION_TYPE"));
            userActivity.setAmount(resultSet.getInt("AMOUNT"));
            userActivity.setTransactionCount(resultSet.getInt("TRANSACTION_COUNT"));

            return userActivity;
        }, userId);

        UserActivityProfile result = new UserActivityProfile();
        userActivities.forEach(result::addUserActivity);
        return result;
    }

}
