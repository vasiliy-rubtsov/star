package ru.luga.star.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.luga.star.model.Argument;
import ru.luga.star.model.Rule;
import ru.luga.star.dto.InputDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Входной DTO с описанием динамического правила
 */
@Schema(description = "Динамическое правило")
public class RuleDto implements InputDto {

    @Schema(description = "Тип запроса", example = "TRANSACTION_SUM_COMPARE")
    @JsonProperty("query")
    private String query;

    @Schema(description = "Список с аргументами запроса")
    @JsonProperty("arguments")
    private List<String> arguments;

    @Schema(description = "Признак инверсии условия запроса", example = "false")
    @JsonProperty("negate")
    private boolean negate;

    public RuleDto() {
        negate = false;
        arguments = new ArrayList<>();
    }

    public RuleDto(Rule rule) {
        query = rule.getQuery();
        arguments = new ArrayList<>();
        negate = rule.isNegate();

        for (Argument argument : rule.getArguments()) {
            arguments.add(argument.getValue());
        }
    }

    @Override
    public void validate() {
        HashMap<String, Integer> validQueryTypes = new HashMap<>();
        validQueryTypes.put("USER_OF", 1);
        validQueryTypes.put("ACTIVE_USER_OF", 1);
        validQueryTypes.put("TRANSACTION_SUM_COMPARE", 4);
        validQueryTypes.put("TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW", 2);

        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Не указан тип запроса");
        }

        if (!validQueryTypes.containsKey(query)) {
            throw new IllegalArgumentException("Указан неизвстный тип запроса " + query);
        }

        if (arguments.size() != validQueryTypes.get(query)) {
            throw new IllegalArgumentException("Для запроса " + query + " указано неверное количество аргументов");
        }

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void addArgument(String argument) {
        arguments.add(argument);
    }

    public boolean isNegate() {
        return negate;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }


}
