package ru.luga.star.services.rules;

import ru.luga.star.model.dto.AllRecommendations;
import ru.luga.star.model.dto.Recommendation;
import ru.luga.star.model.useractivity.UserActivityProfile;

public interface IRecommendationRule {
    void apply(UserActivityProfile userActivityProfile, AllRecommendations allRecommendations);
    Recommendation getRecommendation();
}
