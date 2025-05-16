package ru.luga.star.services.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.luga.star.services.telegrambot.command.Command;
import ru.luga.star.services.telegrambot.command.CommandBuilder;

import java.util.List;

@Service
public class TelegramBotUpdateListener implements UpdatesListener {
    private final String INVALID_COMMAND_CALL_MESSAGE = "Ошибочный синтаксис команды";

    private final TelegramBot telegramBot;
    private final MessageSender messageSender;
    private final MessageParser messageParser;
    private final CommandBuilder commandBuilder;

    public TelegramBotUpdateListener(
        TelegramBot telegramBot,
        MessageSender messageSender,
        MessageParser messageParser,
        CommandBuilder commandBuilder
    ) {
        this.telegramBot = telegramBot;
        this.messageSender = messageSender;
        this.messageParser = messageParser;
        this.commandBuilder = commandBuilder;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            if (message == null) {
                message = update.editedMessage();
            }

            if (message == null) {
                return;
            }

            Long chatId = message.chat().id();
            String messageText = message.text();

            messageParser.parse(messageText);

            if (messageParser.getCommand().isEmpty()) {
                messageSender.send(chatId, INVALID_COMMAND_CALL_MESSAGE + ": " + messageText);
                return;
            }

            try {
                Command cmd = commandBuilder.build(messageParser.getCommand(), messageParser.getParameters());
                String result = cmd.execute();
                messageSender.send(chatId, result);
            } catch (RuntimeException e) {
                messageSender.send(chatId, e.getMessage());
            }
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
