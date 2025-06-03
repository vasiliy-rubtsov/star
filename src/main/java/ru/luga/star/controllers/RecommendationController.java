package ru.luga.star.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.luga.star.dto.recommendation.AllRecommendations;
import ru.luga.star.services.RecommendationsService;

@Tag(name = "Контроллер рекомендаций")
@RestController
public class RecommendationController {

    RecommendationsService recommendationsService;

    public RecommendationController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    @Operation(summary = "Получение рекомендаций по банковским продуктам для выбранного пользователя", description = "Выводит список рекомендованных продуктов для указанного пользователя, полученный на основании его финансовой активности")
    @GetMapping("/recommendation/{userId}")
    public AllRecommendations recommendation(@PathVariable("userId") String userId) {
        return recommendationsService.getAllRecommendations(userId);
    }
}
