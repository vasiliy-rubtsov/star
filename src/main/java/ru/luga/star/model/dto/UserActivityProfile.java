package ru.luga.star.model.dto;

import org.springframework.data.relational.core.sql.In;

import java.util.HashMap;
import java.util.Map;

public class UserActivityProfile {

    private final Map<String, Map<String, Integer>> data;

    public UserActivityProfile() {
        this.data = new HashMap<>();
    }

    /**
     * Добавить активность пользователя в структуру
     */
    public void addUserActivity(UserActivity userActivity) {
        String productType = userActivity.getProductType();
        String transactionType = userActivity.getTransactonType();
        Integer amount = userActivity.getAmount();

        if (!this.data.containsKey(productType)) {
            this.data.put(productType, new HashMap<>());
        }

        this.data.get(productType).put(transactionType, amount);
    }

    /**
     *  Проверить, содержит ли профиль даные о продукте с указанным типом
     */
    public boolean containsProductType(String productType) {
        return this.data.containsKey(productType);
    }

    /**
     * Вернуть сумму пополнения по продуктам данного типа
     */
    public Integer getDepositAmount(String productType) {
        if (!this.data.containsKey(productType)) {
            return 0;
        }

        if (this.data.get(productType).containsKey("DEPOSIT")) {
            return this.data.get(productType).get("DEPOSIT");
        }

        return 0;
    }

    /**
     * Вернуть сумму трат по продуктам данного типа
     */
    public Integer getWithDrawAmount(String productType) {
        if (!this.data.containsKey(productType)) {
            return 0;
        }

        if (this.data.get(productType).containsKey("WITHDRAW")) {
            return this.data.get(productType).get("WITHDRAW");
        }

        return 0;
    }

}
