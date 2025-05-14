package ru.luga.star.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
     * Получение профиля активности пользователя по типам продуктов
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
