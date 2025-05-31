package ru.luga.star.model.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Выходной DTO с описанием отчета по отдельному статическому правилу
 */
public class RuleStatDto {
    @JsonProperty("rule_id")
    private Long ruleId;

    @JsonProperty("count")
    private Long count;

    public RuleStatDto() {
    }

    public RuleStatDto(Long ruleId, Long count) {
        this.ruleId = ruleId;
        this.count = count;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
