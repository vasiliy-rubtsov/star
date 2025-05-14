package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

import java.util.List;

public class TransactionSumCompareDepositWithdrawRule implements DynamicRule {
    private List<String> arguments;
    private boolean negate = false;

    @Override
    public void setNegate(boolean negate) {
        this.negate = negate;
    }

    @Override
    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean apply(UserActivityProfile userActivityProfile) {
        String productType = arguments.get(0);
        String operation = arguments.get(1);
        int depositAmount = userActivityProfile.getDepositAmount(productType);
        int withDrawAmount = userActivityProfile.getWithDrawAmount(productType);
        boolean condition;

        condition = switch (operation) {
            case ">" -> depositAmount > withDrawAmount;
            case "<" -> depositAmount < withDrawAmount;
            case ">=" -> depositAmount >= withDrawAmount;
            case "<=" -> depositAmount <= withDrawAmount;
            default -> depositAmount == withDrawAmount;
        };

        if (negate) {
            condition = !condition;
        }

        return condition;

    }

}
