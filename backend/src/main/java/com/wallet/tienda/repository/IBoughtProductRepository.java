package com.wallet.tienda.repository;

import com.wallet.tienda.model.BoughtProduct;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBoughtProductRepository extends JpaRepository<BoughtProduct, Long> {

}
