package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.useractivity.UserActivityProfile;
import java.util.List;

/**
 * Общий интерфейс для описания динамических правил
 */
public interface DynamicRule {
    /**
     * Устанавливает/сбрасывает признак negate в динамическом правиле
     * @param negate
     */
    void setNegate(boolean negate);

    /**
     * Устанавливаем аргументы, специфичные для данного динамического првила
     * @param arguments
     */
    void setArguments(List<String> arguments);

    /**
     * Определяет соответствие профиля активности пользователя динамическому правилу
     * в системе банка
     * @param userActivityProfile
     * @return
     */
    boolean apply(UserActivityProfile userActivityProfile);
}
