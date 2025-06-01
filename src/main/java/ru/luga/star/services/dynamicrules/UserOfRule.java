package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

import java.util.List;

/**
 * Динамическое правило на основе запроса USER_OF
 */
public class UserOfRule implements DynamicRule {
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
        boolean condition = (userActivityProfile.getDepositTranCount(productType) + userActivityProfile.getWithDrawTranCount(productType)) > 0;
        if (negate) {
            condition = !condition;
        }
        return condition;
    }

}
