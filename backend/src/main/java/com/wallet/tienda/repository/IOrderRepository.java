package com.wallet.tienda.repository;

import com.wallet.tienda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    boolean existsByName(String name);
}
