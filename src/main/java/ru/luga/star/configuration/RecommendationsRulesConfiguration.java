package ru.luga.star.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.luga.star.services.rules.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RecommendationsRulesConfiguration {

    @Bean
    List<IRecommendationRule> recommendationsRules() {
        List<IRecommendationRule> rules = new ArrayList<>();
        rules.add(new RecommendationRule1());
        rules.add(new RecommendationRule2());
        rules.add(new RecommendationRule3());
        return rules;
    }
}
