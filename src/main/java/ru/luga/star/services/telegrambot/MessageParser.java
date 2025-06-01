package ru.luga.star.services.telegrambot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Парсер входящих текстовых команд из телеграм-бота
 */
@Service
public class MessageParser {
    private String command;
    private List<String> parameters = new ArrayList<>();

    public MessageParser() {
        command = "";
        parameters = new ArrayList<>();
    }

    /**
     * Вернуть текст команды
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     * Вернуть список параметров
     * @return
     */
    public List<String> getParameters() {
        return parameters;
    }

    /**
     * Парсить команду
     * @param commandText - текст команды
     */
    public void parse(String commandText) {
        commandText = commandText.trim();
        String[] elements = commandText.split("\\s+");

        boolean p = true;
        parameters.clear();
        for (String element : elements) {
            if (p) {
                command = element;
                p = false;
                continue;
            }

            parameters.add(element);
        }
    }
}
