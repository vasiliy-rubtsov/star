package ru.luga.star.services.rules;

import ru.luga.star.model.dto.AllRecommendations;
import ru.luga.star.model.dto.Recommendation;
import ru.luga.star.model.useractivity.UserActivityProfile;

public class RecommendationRule2 implements IRecommendationRule {

    /**
     * Пользователь использует как минимум один продукт с типом DEBIT.
     * Сумма пополнений по всем продуктам типа DEBIT больше или равна 50 000 ₽ ИЛИ Сумма пополнений по всем продуктам типа SAVING больше или равна 50 000 ₽.
     * Сумма пополнений по всем продуктам типа DEBIT больше, чем сумма трат по всем продуктам типа DEBIT.
     */
    @Override
    public void apply(UserActivityProfile userActivityProfile, AllRecommendations allRecommendations) {
        boolean condition = true;
        condition = condition && userActivityProfile.containsProductType("DEBIT"); // Пользователь использует как минимум один продукт с типом DEBIT
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

        if (condition) {
            allRecommendations.addRecommendation(getRecommendation());
        }
    }

    @Override
    public Recommendation getRecommendation() {
        Recommendation recommendation = new Recommendation();
        recommendation.setId("59efc529-2fff-41af-baff-90ccd7402925");
        recommendation.setName("Top Saving");
        recommendation.setText("Откройте свою собственную «Копилку» с нашим банком! «Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. Больше никаких забытых чеков и потерянных квитанций — всё под контролем!\n" +
                "\n" +
                "Преимущества «Копилки»:\n" +
                "\n" +
                "Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить определенную сумму на ваш счет.\n" +
                "\n" +
                "Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте стратегию при необходимости.\n" +
                "\n" +
                "Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное приложение или интернет-банкинг.\n" +
                "\n" +
                "Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!");
        return recommendation;
    }
}
