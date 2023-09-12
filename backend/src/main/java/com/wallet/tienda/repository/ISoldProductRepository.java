package com.wallet.tienda.repository;

import com.wallet.tienda.model.SoldProduct;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ISoldProductRepository extends JpaRepository<SoldProduct, Long> {

    List<SoldProduct> findAllByLocalDate(LocalDate date);
}
