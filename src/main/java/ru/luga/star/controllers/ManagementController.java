package ru.luga.star.controllers;

import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luga.star.model.dto.management.BuildPropertiesDto;
import ru.luga.star.services.ManagementService;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final CacheManager cacheManager;
    private final ManagementService managementService;

    public ManagementController(CacheManager cacheManager, ManagementService managementService) {
        this.cacheManager = cacheManager;
        this.managementService = managementService;
    }

    @PostMapping("/clear-cache")
    public ResponseEntity<Void> clearCache() {
        cacheManager.getCache("UserActivityProfile").clear();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public ResponseEntity<BuildPropertiesDto> info() {
        BuildPropertiesDto result = managementService.getBuildPropertiesDto();
        return ResponseEntity.ok(result);
    }
}
