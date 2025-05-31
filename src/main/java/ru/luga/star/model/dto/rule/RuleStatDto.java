package ru.luga.star.model.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Выходной DTO с описанием отчета по отдельному статическому правилу
 */
@Schema(description = "Статистика по динамическим правилам")
public class RuleStatDto {
    @Schema(description = "Внутренний ID правила в приложении", example = "2")
    @JsonProperty("rule_id")
    private Long ruleId;

    @Schema(description = "Количество успешных применений правила", example = "10")
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
