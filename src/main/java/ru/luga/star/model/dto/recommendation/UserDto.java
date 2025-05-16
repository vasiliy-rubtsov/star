package ru.luga.star.model.dto.recommendation;

public class UserDto {
    private String userId;
    private String userLogin;
    private String userFullName;

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
