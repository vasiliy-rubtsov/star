package ru.luga.star.services.rules;

import ru.luga.star.model.dto.recommendation.AllRecommendations;
import ru.luga.star.model.dto.recommendation.Recommendation;
import ru.luga.star.model.useractivity.UserActivityProfile;

public class RecommendationRule1 implements IRecommendationRule {

    /**
     * Пользователь использует как минимум один продукт с типом DEBIT.
     * Пользователь не использует продукты с типом INVEST.
     * Сумма пополнений продуктов с типом SAVING больше 1000 ₽.
     */
    @Override
    public void apply(UserActivityProfile userActivityProfile, AllRecommendations allRecommendations) {
        boolean condition = true;
        condition = condition && userActivityProfile.containsProductType("DEBIT"); // Пользователь использует как минимум один продукт с типом DEBIT
        condition = condition && !userActivityProfile.containsProductType("INVEST"); // Пользователь не использует продукты с типом INVEST
        condition = condition && userActivityProfile.getDepositAmount("SAVING") > 1000; // Сумма пополнений продуктов с типом SAVING больше 1000 ₽

        if (condition) {
            allRecommendations.addRecommendation(getRecommendation());
        }


    }

    @Override
    public Recommendation getRecommendation() {
        Recommendation recommendation = new Recommendation();
        recommendation.setId("147f6a0f-3b91-413b-ab99-87f081d60d5a");
        recommendation.setName("Invest 500");
        recommendation.setText("Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!");
        return recommendation;
    }
}
