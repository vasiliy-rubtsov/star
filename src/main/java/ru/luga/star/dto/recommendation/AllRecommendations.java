package ru.luga.star.dto.recommendation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Выходной DTO с описанием всех рекомендация данному пользоввателю
 */
@Schema(description = "Список рекомендация")
@JsonPropertyOrder({"user_id", "recommendations"})
public class AllRecommendations {

    @Schema(description = "ID пользователя в банковской программе", example = "1f9b149c-6577-448a-bc94-16bea229b71a")
    @JsonProperty("user_id")
    private String userId;  // ID пользователя в банковской программе

    @Schema(description = "Список рекомендаций")
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
