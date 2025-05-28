package ru.luga.star.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.luga.star.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByProductId(String productId);

    @Cacheable(value = "Product")
    @NotNull
    @Override
    List<Product> findAll();
}
