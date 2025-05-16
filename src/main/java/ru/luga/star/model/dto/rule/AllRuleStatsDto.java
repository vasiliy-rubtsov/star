package ru.luga.star.model.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

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
