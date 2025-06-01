package ru.luga.star.model.useractivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Профиль активности пользователя
 */
public class UserActivityProfile {

    /**
     * Профиль активности пользователя, представляющий структуру следующего типа
     * [
     * 	    {
     * 		"<Тип продукта из банковской системы>": {
     * 			"<Тип транзации из банковской системы>": {
     * 				"AMOUNT": <Сумма транзакций с данным типом>,
     * 				"COUNT": <Количество тразнакций с данным типом>
     *            }
     *        }
     *    },
     * 	...
     * ]
     */
    private final Map<String, Map<String, Map<String, Integer>>> data;

    public UserActivityProfile() {
        this.data = new HashMap<>();
    }

    /**
     * Добавить активность пользователя в структуру
     * @param userActivity
     */
    public void addUserActivity(UserActivity userActivity) {
        String productType = userActivity.getProductType();
        String transactionType = userActivity.getTransactonType();
        Integer amount = userActivity.getAmount();
        Integer transactionCount = userActivity.getTransactionCount();

        if (!this.data.containsKey(productType)) {
            this.data.put(productType, new HashMap<>());
        }

        Map<String, Integer> data = Map.of("AMOUNT", amount, "COUNT", transactionCount);

        this.data.get(productType).put(transactionType, data);
    }

    /**
     * Проверить, содержит ли профиль даные о продукте с указанным типом
     * @param productType
     * @return
     */
    public boolean containsProductType(String productType) {
        return this.data.containsKey(productType);
    }

    /**
     * Вернуть сумму пополнения по продуктам данного типа
     * @param productType
     * @return
     */
    public Integer getDepositAmount(String productType) {
        if (!this.data.containsKey(productType)) {
            return 0;
        }

        if (this.data.get(productType).containsKey("DEPOSIT")) {
            return this.data.get(productType).get("DEPOSIT").get("AMOUNT");
        }

        return 0;
    }

    /**
     * Вернуть количество транзакций пополнения по продуктам данного типа
     * @param productType
     * @return
     */
    public Integer getDepositTranCount(String productType) {
        if (!this.data.containsKey(productType)) {
            return 0;
        }

        if (this.data.get(productType).containsKey("DEPOSIT")) {
            return this.data.get(productType).get("DEPOSIT").get("COUNT");
        }

        return 0;
    }

    /**
     * Вернуть сумму трат по продуктам данного типа
     * @param productType
     * @return
     */
    public Integer getWithDrawAmount(String productType) {
        if (!this.data.containsKey(productType)) {
            return 0;
        }

        if (this.data.get(productType).containsKey("WITHDRAW")) {
            return this.data.get(productType).get("WITHDRAW").get("AMOUNT");
        }

        return 0;
    }

    /**
     * Вернуть количество транзакций списания средств по продуктам данного типа
     * @param productType
     * @return
     */
    public Integer getWithDrawTranCount(String productType) {
        if (!this.data.containsKey(productType)) {
            return 0;
        }

        if (this.data.get(productType).containsKey("WITHDRAW")) {
            return this.data.get(productType).get("WITHDRAW").get("COUNT");
        }

        return 0;
    }

}
