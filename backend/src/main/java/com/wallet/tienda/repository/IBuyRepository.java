package com.wallet.tienda.repository;

import com.wallet.tienda.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
public interface IBuyRepository extends JpaRepository<Buy, Long> {
    List<Buy> findAllByPurchaseDateBetween(LocalDateTime normalStartDay, LocalDateTime normalEndDay);
}
