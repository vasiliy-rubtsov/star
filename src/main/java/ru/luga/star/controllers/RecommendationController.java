package ru.luga.star.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.luga.star.model.dto.AllRecommendations;
import ru.luga.star.model.dto.UserActivityProfile;
import ru.luga.star.repositories.RecommendationsRepository;
import ru.luga.star.services.RecommendationsService;

import java.util.Map;

@RestController
public class RecommendationController {

    RecommendationsService recommendationsService;

    public RecommendationController(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }

    @GetMapping("/recommendation/{userId}")
    public ResponseEntity<AllRecommendations> recommendation(@PathVariable("userId") String userId) {
        AllRecommendations response = recommendationsService.getAllRecommendations(userId);

        return ResponseEntity.ok(response);
    }
}
