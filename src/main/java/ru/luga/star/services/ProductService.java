package ru.luga.star.services;

import org.springframework.stereotype.Service;
import ru.luga.star.model.Product;
import ru.luga.star.model.dto.rule.ProductDto;
import ru.luga.star.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto addProduct(ProductDto productDto) {
        Product product = productRepository.findFirstByProductId(productDto.getProductId());
        if (product != null) {
            throw new IllegalArgumentException("Найдено правило для продукта с ID = " + productDto.getProductId() + ". Сначала удалите это правило!");
        }
        product = new Product(productDto);
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    public void deleteProduct(String productId) {
        Product product = productRepository.findFirstByProductId(productId);
        if (product != null) {
            productRepository.deleteById(product.getId());
        }
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto(product);
            result.add(productDto);
        }
        return result;
    }
}
