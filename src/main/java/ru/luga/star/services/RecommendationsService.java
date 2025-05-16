package ru.luga.star.services;

import jakarta.persistence.Cacheable;
import org.springframework.stereotype.Service;
import ru.luga.star.model.Product;
import ru.luga.star.model.Rule;
import ru.luga.star.model.RuleStat;
import ru.luga.star.model.dto.recommendation.AllRecommendations;
import ru.luga.star.model.dto.recommendation.Recommendation;
import ru.luga.star.model.dto.recommendation.UserDto;
import ru.luga.star.model.useractivity.UserActivityProfile;
import ru.luga.star.repositories.ProductRepository;
import ru.luga.star.repositories.RecommendationsRepository;
import ru.luga.star.repositories.RuleStatRepository;
import ru.luga.star.services.dynamicrules.DynamicRuleBuilder;
import ru.luga.star.services.staticrules.StaticRuleBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationsService {
    private final RecommendationsRepository recommendationsRepository;
    private final ProductRepository productRepository;
    private final RuleStatRepository ruleStatRepository;

    private List<Product> allRecommendedProducts;

    public RecommendationsService(
            RecommendationsRepository recommendationsRepository,
            ProductRepository productRepository,
            RuleStatRepository ruleStatRepository
    ) {
        this.recommendationsRepository = recommendationsRepository;
        this.productRepository = productRepository;
        this.ruleStatRepository = ruleStatRepository;
    }

    public UserDto findUserByLogin(String userLogin) {
        return recommendationsRepository.findUserByLogin(userLogin);
    }

    public AllRecommendations getAllRecommendations(String userId) {
        AllRecommendations allRecommendations = new AllRecommendations();
        UserActivityProfile userActivityProfile = recommendationsRepository.getUserActivityProfile(userId);
        allRecommendations.setUserId(userId);

        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            boolean criteria;
            List<Rule> rules = product.getRules();
            if (!rules.isEmpty()) {
                // Используем динамические правила, описанные в БД
                criteria = true;
                for (Rule rule : rules) {
                    Long ruleId = rule.getId();
                    boolean ruleCriteria = DynamicRuleBuilder.build(rule).apply(userActivityProfile);

                    // Для динамических правил ведем статистику их срабатываний согласно ТЗ
                    if (ruleCriteria) {
                        Optional<RuleStat> ruleStatOptional = ruleStatRepository.findById(ruleId);
                        RuleStat ruleStat = ruleStatOptional.orElseGet(() -> new RuleStat(ruleId, 0L));
                        ruleStat.setCount(ruleStat.getCount() + 1);
                        ruleStatRepository.save(ruleStat);
                    }
                    criteria = criteria && ruleCriteria;
                }
            } else {
                // Используем статическое правило, поведение которого задано в соотв. классе
                criteria = StaticRuleBuilder.build(product).apply(userActivityProfile);
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
