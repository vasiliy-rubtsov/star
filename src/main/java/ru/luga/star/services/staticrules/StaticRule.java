package ru.luga.star.services.staticrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

/**
 * Общий интерфейс для описания статических правил
 */
public interface StaticRule {
    /**
     * Определяет соответствие профиля активности пользователя статическому правилу
     * в системе банка
     * @param userActivityProfile
     * @return
     */
    boolean apply(UserActivityProfile userActivityProfile);
}
