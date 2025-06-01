package ru.luga.star.services.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

/**
 * Отправитель сообщений в телеграм-бот
 */
@Service
public class MessageSender {
    private final TelegramBot telegramBot;

    public MessageSender(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Отправить сообщение в чат
     * @param chatId - ID чата
     * @param messageText - текст сообщения
     */
    public void send(Long chatId, String messageText) {
        SendMessage sendMessage = new SendMessage(chatId, messageText);
        sendMessage.parseMode(ParseMode.Markdown);
        telegramBot.execute(sendMessage);
    }
}
