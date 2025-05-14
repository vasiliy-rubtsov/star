package ru.luga.star.model.dto.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.luga.star.model.Product;
import ru.luga.star.model.Rule;
import ru.luga.star.model.dto.InputDto;

import java.util.ArrayList;
import java.util.List;

public class ProductDto implements InputDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_text")
    private String productText;

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
