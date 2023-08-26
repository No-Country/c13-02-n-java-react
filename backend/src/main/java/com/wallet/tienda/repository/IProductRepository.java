package com.wallet.tienda.repository;

import com.wallet.tienda.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}