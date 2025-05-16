package ru.luga.star.model.dto.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.info.BuildProperties;

public class BuildPropertiesDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;

    public BuildPropertiesDto() {
    }

    public BuildPropertiesDto(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public BuildPropertiesDto(BuildProperties buildProperties) {
        this.name = buildProperties.getName();
        this.version = buildProperties.getVersion();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
