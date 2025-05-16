package ru.luga.star.model;

import jakarta.persistence.*;
import ru.luga.star.model.dto.rule.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rule> rules;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_text", length = 5000)
    private String productText;

    public Product() {
        rules = new ArrayList<>();
    }

    public Product(ProductDto productDto) {
        productName = productDto.getProductName();
        productText = productDto.getProductText();
        productId =  productDto.getProductId();
        rules = new ArrayList<>();

        productDto.getRules().forEach(ruleDto -> {
            Rule rule = new Rule(ruleDto);
            addRule(rule);
        });
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        rule.setProduct(this);
        rules.add(rule);
    }
}
