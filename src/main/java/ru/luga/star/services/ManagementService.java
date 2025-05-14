package ru.luga.star.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import ru.luga.star.model.dto.management.BuildPropertiesDto;

@Service
public class ManagementService {

    private final BuildProperties buildProperties;

    public ManagementService(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public BuildPropertiesDto getBuildPropertiesDto() {
        return new BuildPropertiesDto(buildProperties);
    }
}
