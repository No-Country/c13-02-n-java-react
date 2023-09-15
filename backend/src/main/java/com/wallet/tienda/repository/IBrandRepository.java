package com.wallet.tienda.repository;

import com.wallet.tienda.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
}
