package ru.luga.star.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.luga.star.model.dto.rule.AllRuleStatsDto;
import ru.luga.star.model.dto.rule.ProductDto;
import ru.luga.star.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/rule")
public class RuleController {

    private final ProductService productService;

    public RuleController(ProductService productService) {
        this.productService = productService;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addRule(@RequestBody ProductDto productDto) {
        productDto.validate();
        return ResponseEntity.ok(productService.addProduct(productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteRule(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getRules() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/stats")
    public ResponseEntity<AllRuleStatsDto> getAllRuleStats() {
        AllRuleStatsDto allRuleStatsDto = productService.getAllRuleStats();
        return ResponseEntity.ok(allRuleStatsDto);
    }
}
