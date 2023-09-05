package com.wallet.tienda.repository;

import com.wallet.tienda.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    boolean existsByName(String name);
    List<Expense> findAllByPurchaseDateBetween(LocalDateTime normalStartDay, LocalDateTime normalEndDay);
}
