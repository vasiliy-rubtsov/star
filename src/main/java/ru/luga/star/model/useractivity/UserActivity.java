package ru.luga.star.model.useractivity;

/**
 * Активность пользователя по продукту и типу транзакции
 */
public class UserActivity {
    private String productType;     // тип продукта из банковской системы
    private String transactonType;  // тип транзакции из банковской системы (приход/расход)
    private Integer amount;         // сумма по всем транзакциям данного типа
    private Integer transactionCount; // кол-во транзакций данного типа

    public UserActivity() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTransactonType() {
        return transactonType;
    }

    public void setTransactonType(String transactonType) {
        this.transactonType = transactonType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }
}
