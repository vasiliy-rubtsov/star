package ru.luga.star.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.luga.star.model.Product;
import ru.luga.star.model.Rule;
import ru.luga.star.dto.InputDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Входной DTO с описанием продукта
 */
@Schema(description = "")
public class ProductDto implements InputDto {

    @Schema(description = "Внутренний ID продукта в приложении", example = "2")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Наименование продукта", example = "Простой кредит")
    @JsonProperty("product_name")
    private String productName;

    @Schema(description = "ID продукта в банковской системе", example = "ab138afb-f3ba-4a93-b74f-0fcee86d447f")
    @JsonProperty("product_id")
    private String productId;

    @Schema(description = "Описание продукта", example = "Откройте мир выгодных кредитов с нами!")
    @JsonProperty("product_text")
    private String productText;

    @Schema(description = "Список правил, выполнение которых необходимо для рекомендации этого продукта")
    @JsonProperty("rule")
    private List<RuleDto> rules;

    public ProductDto() {
        rules = new ArrayList<>();
    }

    public ProductDto(Product product) {
        id = product.getId();
        productName = product.getProductName();
        productId = product.getProductId();
        productText = product.getProductText();
        rules = new ArrayList<>();
        for (Rule rule : product.getRules()) {
            rules.add(new RuleDto(rule));
        }
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    public List<RuleDto> getRules() {
        return rules;
    }

    public void addRule(RuleDto rule) {
        rules.add(rule);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void validate() {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Не указано название рекомендуемого продукта");
        }

        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Не указан ID рекомендуемого продукта");
        }

        if (productText == null || productText.isEmpty()) {
            throw new IllegalArgumentException("Не указано описание рекомендуемого продукта");
        }

        if (!rules.isEmpty()) {
            rules.forEach(RuleDto::validate);
        }
    }
}
