package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

import java.util.List;

/**
 * Динамическое правило на основе запроса TRANSACTION_SUM_COMPARE
 */
public class TransactionSumCompareRule implements DynamicRule {
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
        String transactionType = arguments.get(1);
        String operation = arguments.get(2);
        int criteria = Integer.parseInt(arguments.get(3));
        int amount;
        boolean condition;

        if (transactionType.equals("WITHDRAW")) {
            amount = userActivityProfile.getWithDrawAmount(productType);
        } else {
            amount = userActivityProfile.getDepositAmount(productType);
        }

        condition = switch (operation) {
            case ">" -> amount > criteria;
            case "<" -> amount < criteria;
            case ">=" -> amount >= criteria;
            case "<=" -> amount <= criteria;
            default -> amount == criteria;
        };

        if (negate) {
            condition = !condition;
        }

        return condition;
    }

}
