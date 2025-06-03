package ru.luga.star.telegrambot.command;

/**
 * Общий интерфейс для объекатов "Команда"
 */
public interface Command {
    /**
     * Выполнеть команду
     * @return - текстовый результат для передачи в чат
     */
    String execute();
}
