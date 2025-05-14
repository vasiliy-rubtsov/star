package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicRuleBuilder {
    private final static Map<Long, DynamicRule> dynamicRules = new HashMap<>();

    public static  DynamicRule build(Rule rule) {
        String queryType = rule.getQuery();
        Long key = rule.getId();
        DynamicRule dynamicRule;
        if (dynamicRules.containsKey(key)) {
            dynamicRule =  dynamicRules.get(key);
        } else {
            dynamicRule = switch (queryType) {
                case "USER_OF" -> new UserOfRule();
                case "ACTIVE_USER_OF" -> new ActiveUserOfRule();
                case "TRANSACTION_SUM_COMPARE" -> new TransactionSumCompareRule();
                default -> new TransactionSumCompareDepositWithdrawRule();
            };
            dynamicRules.put(key, dynamicRule);
        }
        List<String> arguments = new ArrayList<>();
        rule.getArguments().forEach(argument -> {arguments.add(argument.getValue());});

        dynamicRule.setNegate(rule.isNegate());
        dynamicRule.setArguments(arguments);

        return dynamicRule;
    }
}
