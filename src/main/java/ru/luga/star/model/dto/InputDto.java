package ru.luga.star.model.dto;

/**
 * Общий интерфейс для всех входящих DTO
 */
public interface InputDto {
    /**
     * Проверить корректность заполнения DTO данными
     */
    void validate();
}
