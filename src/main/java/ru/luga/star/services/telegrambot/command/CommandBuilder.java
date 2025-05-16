package ru.luga.star.services.telegrambot.command;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Component;
import ru.luga.star.services.RecommendationsService;
import ru.luga.star.services.telegrambot.MessageSender;
import java.util.List;

@Component
public class CommandBuilder {
    private final RecommendationsService recommendationsService;

    public CommandBuilder(
        RecommendationsService recommendationsService
    ) {
        this.recommendationsService = recommendationsService;
    }

    public Command build(
            String command,
            List<String> parameters
    ) {
        if (command.equals("/recommend")) {
            if (parameters.size() != 1) {
                // Для этой команды кол-во параметров должно быть 1
                throw new RuntimeException("Для команды /recommend должен быть указан 1 параметр");
            }
            return new RecommendCommand(parameters, recommendationsService);
        }

        throw new RuntimeException("Неизвестная команда");
    }
}
