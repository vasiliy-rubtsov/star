package ru.luga.star.dto.recommendation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Выходной DTO c описанием рекомендации пользователю
 */
@Schema(description = "Рекомендация пользователю")
@JsonPropertyOrder({"name", "id", "text"})
public class Recommendation {
    @Schema(description = "Наименование продукта", example = "Простой кредит")
    private String name;    // Наименование продукта

    @Schema(description = "ID продукта в банковской программе", example = "ab138afb-f3ba-4a93-b74f-0fcee86d447f")
    private String id;      // ID продукта в банковской программе

    @Schema(description = "Описание продукта", example = "Откройте мир выгодных кредитов с нами!")
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
