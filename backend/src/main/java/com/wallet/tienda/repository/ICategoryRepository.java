package com.wallet.tienda.repository;

import com.wallet.tienda.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
