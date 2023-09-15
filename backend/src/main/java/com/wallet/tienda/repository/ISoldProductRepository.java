package com.wallet.tienda.repository;

import com.wallet.tienda.model.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISoldProductRepository extends JpaRepository<SoldProduct, Long> {

}
