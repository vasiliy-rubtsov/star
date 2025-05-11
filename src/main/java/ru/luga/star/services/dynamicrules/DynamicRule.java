package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.Rule;
import ru.luga.star.model.useractivity.UserActivityProfile;

import java.util.ArrayList;
import java.util.List;

public interface DynamicRule {
    static DynamicRule build(Rule rule) {
        DynamicRule dynamicRule;
        switch (rule.getQuery()) {
            case "USER_OF":
                dynamicRule = new UserOfRule();
                break;
            case "ACTIVE_USER_OF":
                dynamicRule = new ActiveUserOfRule();
                break;
            case "TRANSACTION_SUM_COMPARE":
                dynamicRule = new TransactionSumCompareRule();
                break;
            default:
                dynamicRule = new TransactionSumCompareDepositWithdrawRule();
        }
        List<String> arguments = new ArrayList<>();
        rule.getArguments().forEach(argument -> {arguments.add(argument.getValue());});

        dynamicRule.setArguments(arguments);
        return dynamicRule;
    }

    void setArguments(List<String> arguments);

    boolean apply(UserActivityProfile userActivityProfile);
}
