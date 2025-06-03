package ru.luga.star.telegrambot.command;

import ru.luga.star.dto.recommendation.AllRecommendations;
import ru.luga.star.dto.recommendation.UserDto;
import ru.luga.star.services.RecommendationsService;

import java.util.List;

public class RecommendCommand implements Command {
    private final String USER_NOT_FOUND_MESSAGE = "Пользователь не найден";

    private List<String> parameters;
    private RecommendationsService recommendationsService;

    public RecommendCommand(List<String> parameters, RecommendationsService recommendationsService) {
        this.parameters = parameters;
        this.recommendationsService = recommendationsService;
    }

    @Override
    public String execute() {
        String userLogin = parameters.get(0);
        UserDto userDto = recommendationsService.findUserByLogin(userLogin);
        if (userDto == null) {
            throw new RuntimeException(USER_NOT_FOUND_MESSAGE);
        }

        AllRecommendations recommendations = recommendationsService.getAllRecommendations(userDto.getUserId());

        return "Здравствуйте, " + userDto.getUserFullName() + "!\n\n"
                + "новые продукты для Вас:" + "\n\n"
                + recommendations.toString();
    }

}
