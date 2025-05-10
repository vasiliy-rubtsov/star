package ru.luga.star.services.rules;

import ru.luga.star.model.dto.AllRecommendations;
import ru.luga.star.model.dto.Recommendation;
import ru.luga.star.model.useractivity.UserActivityProfile;

public class RecommendationRule3 implements IRecommendationRule {

    /**
     * Пользователь не использует продукты с типом CREDIT.
     * Сумма пополнений по всем продуктам типа DEBIT больше, чем сумма трат по всем продуктам типа DEBIT.
     * Сумма трат по всем продуктам типа DEBIT больше, чем 100 000 ₽.
     */
    @Override
    public void apply(UserActivityProfile userActivityProfile, AllRecommendations allRecommendations) {
        boolean condition = true;
        condition = condition && !userActivityProfile.containsProductType("CREDIT"); // Пользователь не использует продукты с типом CREDIT

        // Сумма пополнений по всем продуктам типа DEBIT больше, чем сумма трат по всем продуктам типа DEBIT.
        condition = condition && userActivityProfile.getDepositAmount("DEBIT")  > userActivityProfile.getWithDrawAmount("DEBIT");

        // Сумма трат по всем продуктам типа DEBIT больше, чем 100 000 ₽
        condition = condition && userActivityProfile.getWithDrawAmount("DEBIT") > 100000;
        if (condition) {
            allRecommendations.addRecommendation(getRecommendation());
        }
    }

    @Override
    public Recommendation getRecommendation() {
        Recommendation recommendation = new Recommendation();
        recommendation.setId("ab138afb-f3ba-4a93-b74f-0fcee86d447f");
        recommendation.setName("Простой кредит");
        recommendation.setText("Откройте мир выгодных кредитов с нами!\n" +
                "\n" +
                "Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту.\n" +
                "\n" +
                "Почему выбирают нас:\n" +
                "\n" +
                "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.\n" +
                "\n" +
                "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.\n" +
                "\n" +
                "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.\n" +
                "\n" +
                "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!");
        return recommendation;
    }
}
