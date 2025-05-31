package ru.luga.star.model.dto.recommendation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Выходной DTO c описанием рекомендации пользователю
 */
@JsonPropertyOrder({"name", "id", "text"})
public class Recommendation {
    private String name;    // Наименование продукта
    private String id;      // ID продукта в банковской программе
    private String text;    // Описание продукта

    public Recommendation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "*" + name + "*" + "\n" + text;
    }
}
