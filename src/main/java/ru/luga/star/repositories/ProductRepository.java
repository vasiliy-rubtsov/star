package ru.luga.star.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luga.star.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByProductId(String productId);
}
