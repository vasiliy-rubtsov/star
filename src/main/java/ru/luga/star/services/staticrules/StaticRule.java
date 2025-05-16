package ru.luga.star.services.staticrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

public interface StaticRule {
    boolean apply(UserActivityProfile userActivityProfile);
}
