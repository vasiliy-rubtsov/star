package ru.luga.star.model.useractivity;

public class UserActivity {
    private String productType;     // тип продукта
    private String transactonType;  // тип транзакции (приход/расход)
    private Integer amount;         // сумма

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
}
