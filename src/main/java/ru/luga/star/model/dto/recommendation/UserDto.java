package ru.luga.star.model.dto.recommendation;

/**
 * Выходной DTO c описанием пользователя
 */
public class UserDto {
    private String userId;          // ID пользователя в банковской программе
    private String userLogin;       // Логин
    private String userFullName;    // Полное имя пользователя

    public UserDto() {
    }

    public UserDto(String userId, String userLogin, String userFullName) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userFullName = userFullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userFullName='" + userFullName + '\'' +
                '}';
    }
}
