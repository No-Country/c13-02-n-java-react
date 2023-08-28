package com.wallet.tienda.repository;

import com.wallet.tienda.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBrand extends JpaRepository<Brand , Long> {
}
