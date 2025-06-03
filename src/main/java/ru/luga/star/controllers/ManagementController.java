package ru.luga.star.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luga.star.dto.management.BuildPropertiesDto;
import ru.luga.star.services.ManagementService;

import java.util.Objects;

@Tag(name = "Контроллер управления приложением")
@RestController
@RequestMapping("/management")
public class ManagementController {

    private final CacheManager cacheManager;
    private final ManagementService managementService;

    public ManagementController(CacheManager cacheManager, ManagementService managementService) {
        this.cacheManager = cacheManager;
        this.managementService = managementService;
    }

    @Operation(summary = "Очистка кэша")
    @PostMapping("/clear-cache")
    public void clearCache() {
        Objects.requireNonNull(cacheManager.getCache("UserActivityProfile")).clear();
        Objects.requireNonNull(cacheManager.getCache("User")).clear();
        Objects.requireNonNull(cacheManager.getCache("Product")).clear();
    }

    @Operation(summary = "Получение информации о программе", description = "Выводит имя и текущую версию приложения")
    @GetMapping("/info")
    public BuildPropertiesDto info() {
        return managementService.getBuildPropertiesDto();
    }
}
