package com.wallet.tienda.repository;

import com.wallet.tienda.model.BoughtProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBoughtProductRepository extends JpaRepository<BoughtProduct, Long> {
}
