package ru.luga.star.services;

import org.springframework.stereotype.Service;
import ru.luga.star.model.Product;
import ru.luga.star.model.Rule;
import ru.luga.star.model.dto.recommendation.AllRecommendations;
import ru.luga.star.model.dto.recommendation.Recommendation;
import ru.luga.star.model.useractivity.UserActivityProfile;
import ru.luga.star.repositories.ProductRepository;
import ru.luga.star.repositories.RecommendationsRepository;
import ru.luga.star.services.dynamicrules.DynamicRule;
import ru.luga.star.services.rules.IRecommendationRule;

import java.util.List;

@Service
public class RecommendationsService {
    private final RecommendationsRepository recommendationsRepository;
    private final List<IRecommendationRule> rules;
    private final ProductRepository productRepository;

    private List<Product> allRecommendedProducts;

    public RecommendationsService(
            RecommendationsRepository recommendationsRepository,
            ProductRepository productRepository,
            List<IRecommendationRule> rules
    ) {
        this.recommendationsRepository = recommendationsRepository;
        this.productRepository = productRepository;
        this.rules = rules;
    }

    public AllRecommendations getAllRecommendations(String userId) {
        AllRecommendations allRecommendations = new AllRecommendations();
        UserActivityProfile userActivityProfile = recommendationsRepository.getUserActivityProfile(userId);
        allRecommendations.setUserId(userId);

        rules.forEach(rule -> {
            rule.apply(userActivityProfile, allRecommendations);
        });

        return allRecommendations;
    }

    public AllRecommendations getAllRecommendationsOnDynamicRules(String userId) {
        AllRecommendations allRecommendations = new AllRecommendations();
        UserActivityProfile userActivityProfile = recommendationsRepository.getUserActivityProfile(userId);
        allRecommendations.setUserId(userId);

        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            List<Rule> rules = product.getRules();
            boolean criteria = true;
            for (Rule rule : rules) {
                criteria = criteria && DynamicRule.build(rule).apply(userActivityProfile);
            }
            if (criteria) {
                Recommendation recommendation = new Recommendation();
                recommendation.setName(product.getProductName());
                recommendation.setId(product.getProductId());
                recommendation.setText(product.getProductText());
                allRecommendations.addRecommendation(recommendation);
            }
        });

        return allRecommendations;
    }

}
