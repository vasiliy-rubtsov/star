package ru.luga.star.services.dynamicrules;

import ru.luga.star.model.useractivity.UserActivityProfile;
import java.util.List;

public interface DynamicRule {
    void setNegate(boolean negate);
    void setArguments(List<String> arguments);
    boolean apply(UserActivityProfile userActivityProfile);
}
