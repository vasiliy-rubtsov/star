package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

import java.util.List;

public class TransactionSumCompareDepositWithdrawRule implements DynamicRule {
    private List<String> arguments;

    @Override
    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean apply(UserActivityProfile userActivityProfile) {
        return false;
    }

}
