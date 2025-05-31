package ru.luga.star.services.telegrambot.command;

import ru.luga.star.services.telegrambot.MessageSender;

import java.util.List;

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
