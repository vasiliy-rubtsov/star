package ru.luga.star.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.luga.star.dto.rule.AllRuleStatsDto;
import ru.luga.star.dto.rule.ProductDto;
import ru.luga.star.services.ProductService;

import java.util.List;

/**
 * Контроллер методов управления правилами
 */
@Tag(name = "Контроллер методов управления продуктами и правилами")
@RestController
@RequestMapping("/rule")
public class RuleController {

    private final ProductService productService;

    public RuleController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Добавление нового продукта", description = "Позволяет добавить новые продукт с привязанным к нему списком динамических правил")
    @PostMapping
    public ProductDto addRule(@RequestBody ProductDto productDto) {
        productDto.validate();
        return productService.addProduct(productDto);
    }

    @Operation(summary = "Удаление продукта", description = "Позволяет удалить продукт с привязанным к нему списком динамических правил")
    @DeleteMapping("/{productId}")
    public void deleteRule(@Parameter(description = "ID продукта в банковской системе") @PathVariable String productId) {
        productService.deleteProduct(productId);
    }

    @Operation(summary = "Список продуктов", description = "Выводит список всех продуктов с привязаннымы к ним списками динамических правил")
    @GetMapping
    public List<ProductDto> getRules() {
        return productService.getProducts();
    }

    @Operation(summary = "Получение статистики по динамическим правилам", description = "Выводит количество успешных применений динамических правил")
    @GetMapping("/stats")
    public AllRuleStatsDto getAllRuleStats() {
        return productService.getAllRuleStats();
    }
}
