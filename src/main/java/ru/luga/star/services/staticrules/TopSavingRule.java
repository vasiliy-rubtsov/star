package ru.luga.star.services.staticrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

/**
 * Статическое правило для продукта Top Saving
 */
public class TopSavingRule implements StaticRule {
    @Override
    public boolean apply(UserActivityProfile userActivityProfile) {
        boolean condition = userActivityProfile.containsProductType("DEBIT"); // Пользователь использует как минимум один продукт с типом DEBIT
        condition = condition && (
                // Сумма пополнений по всем продуктам типа DEBIT больше или равна 50 000 ₽
                (
                    userActivityProfile.getDepositAmount("DEBIT") >= 50000
                )
                // ИЛИ Сумма пополнений по всем продуктам типа SAVING больше или равна 50 000 ₽
                || (
                    userActivityProfile.getDepositAmount("SAVING") >= 50000
                )
        );

        // Сумма пополнений по всем продуктам типа DEBIT больше, чем сумма трат по всем продуктам типа DEBIT
        condition = condition && userActivityProfile.getDepositAmount("DEBIT")  > userActivityProfile.getWithDrawAmount("DEBIT");

        return condition;
    }
}
