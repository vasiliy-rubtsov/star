package ru.luga.star.model.dto;

import java.util.HashMap;

public class UserActivityProfile extends HashMap<String, HashMap<String, Integer>> {

    public void addUserActivity(UserActivity userActivity) {
        String productType = userActivity.getProductType();
        String transactionType = userActivity.getTransactonType();
        Integer amount = userActivity.getAmount();

        if (!this.containsKey(productType)) {
            this.put(productType, new HashMap<>());
        }

        this.get(productType).put(transactionType, amount);
    }

    public Integer getDepositAmount(String productType) {
        if (!this.containsKey(productType)) {
            return 0;
        }

        if (this.get(productType).containsKey("DEPOSIT")) {
            return this.get(productType).get("DEPOSIT");
        }

        return 0;
    }

    public Integer getWithDrawAmount(String productType) {
        if (!this.containsKey(productType)) {
            return 0;
        }

        if (this.get(productType).containsKey("WITHDRAW")) {
            return this.get(productType).get("WITHDRAW");
        }

        return 0;
    }

}
