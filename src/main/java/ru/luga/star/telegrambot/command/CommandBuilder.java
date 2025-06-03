/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package ru.luga.star.telegrambot.command;

import org.springframework.stereotype.Component;
import ru.luga.star.services.RecommendationsService;

import java.util.List;

@Component
public class CommandBuilder {
    private final RecommendationsService recommendationsService;

    public CommandBuilder(
        RecommendationsService recommendationsService
    ) {
        this.recommendationsService = recommendationsService;
    }

    /**
     * @param command
     * @param parameters
     * @return
     */
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
