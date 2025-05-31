package ru.luga.star.model.dto.recommendation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Выходной DTO с описанием всех рекомендация данному пользоввателю
 */
@JsonPropertyOrder({"user_id", "recommendations"})
public class AllRecommendations {

    @JsonProperty("user_id")
    private String userId;  // ID пользователя в банковской программе
    @JsonPropertyOrder
    private List<Recommendation> recommendations; // Список рекомендаций для данного пользователя

    public AllRecommendations() {
        recommendations = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void addRecommendation(Recommendation recommendation) {
        recommendations.add(recommendation);
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Recommendation recommendation : recommendations) {
            result.append(recommendation.toString()).append("\n\n");
        }
        return result.toString();
    }
}
