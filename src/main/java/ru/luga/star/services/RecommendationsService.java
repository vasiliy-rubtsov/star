package ru.luga.star.services;

import org.springframework.stereotype.Service;
import ru.luga.star.model.dto.AllRecommendations;
import ru.luga.star.model.dto.UserActivityProfile;
import ru.luga.star.repositories.RecommendationsRepository;
import ru.luga.star.services.rules.IRecommendationRule;

import java.util.List;

@Service
public class RecommendationsService {
    RecommendationsRepository recommendationsRepository;
    List<IRecommendationRule> rules;

    public RecommendationsService(RecommendationsRepository recommendationsRepository, List<IRecommendationRule> rules) {
        this.recommendationsRepository = recommendationsRepository;
        this.rules = rules;
    }

    public AllRecommendations getAllRecommendations(String userId) {
        AllRecommendations allRecommendations = new AllRecommendations();
        UserActivityProfile userActivityProfile = recommendationsRepository.getUserActivityProfile(userId);
        allRecommendations.setUserId(userId);
        rules.stream().forEach(rule -> {
            rule.apply(userActivityProfile, allRecommendations);
        });
        return allRecommendations;
    }


}
