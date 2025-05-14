package ru.luga.star.services.staticrules;

import ru.luga.star.model.useractivity.UserActivityProfile;

import java.util.HashMap;
import java.util.Map;

public interface StaticRule {
    boolean apply(UserActivityProfile userActivityProfile);
}
