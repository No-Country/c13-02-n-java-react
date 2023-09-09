package com.wallet.tienda.repository;

import com.wallet.tienda.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<Sale, Long> {
}
