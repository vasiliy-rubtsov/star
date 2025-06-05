package ru.luga.star.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

/**
 * Выходной DTO с описанием массива отчетов по статическим правилам
 */
@Schema(description = "")
public class AllRuleStatsDto {
    @JsonProperty("stats")
    private List<RuleStatDto> stats;

    public AllRuleStatsDto() {
        stats = new ArrayList<>();
    }

    public List<RuleStatDto> getStats() {
        return stats;
    }

    public void addRuleStatDto(RuleStatDto ruleStatDto) {
        stats.add(ruleStatDto);
    }
}
