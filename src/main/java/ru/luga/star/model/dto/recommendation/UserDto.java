package ru.luga.star.model.dto.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Выходной DTO c описанием пользователя
 */
@Schema(description = "Сведения о пользователе")
public class UserDto {
    @Schema(description = "ИД пользователя в банковской программе", example = "1f9b149c-6577-448a-bc94-16bea229b71a")
    private String userId;          // ID пользователя в банковской программе

    @Schema(description = "Логин")
    private String userLogin;       // Логин

    @Schema(description = "Пароль")
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
