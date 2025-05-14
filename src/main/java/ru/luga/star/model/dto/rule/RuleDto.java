package ru.luga.star.model.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.luga.star.model.Argument;
import ru.luga.star.model.Rule;
import ru.luga.star.model.dto.InputDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RuleDto implements InputDto {

    @JsonProperty("query")
    private String query;

    @JsonProperty("arguments")
    private List<String> arguments;

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
