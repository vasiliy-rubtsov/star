package ru.luga.star.services.staticrules;

import ru.luga.star.model.Product;

import java.util.HashMap;
import java.util.Map;

public class StaticRuleBuilder {
    private final static Map<Long, StaticRule> staticRules = new HashMap<>();

    public static StaticRule build(Product product) {
        String productName = product.getProductName();
        Long key = product.getId();
        StaticRule staticRule;
        if (staticRules.containsKey(key)) {
            staticRule = staticRules.get(key);
        } else {
            staticRule = switch (productName) {
                // Здесь создается экземпляр статического правила в зависимости от названия продукта
                // "<название продукта>" -> new <Класс правила, определенного для данного продукта>
                default -> new TopSavingRule();
            };
            staticRules.put(key, staticRule);
        }
        return staticRule;
    }
}
