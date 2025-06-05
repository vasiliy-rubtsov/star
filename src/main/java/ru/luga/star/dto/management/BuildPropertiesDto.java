package ru.luga.star.dto.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.info.BuildProperties;

/**
 * Выходной DTO с описанием сведений о программе
 */
@Schema(description = "Сведения о программе")
public class BuildPropertiesDto {
    @Schema(description = "Наименование приложения", example = "star")
    @JsonProperty("name")
    private String name;        // Наименование приложения

    @Schema(description = "версия", example = "0.0.1-SNAPSHOT")
    @JsonProperty("version")    // Версия
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
