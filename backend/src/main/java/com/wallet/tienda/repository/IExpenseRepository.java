package com.wallet.tienda.repository;

import com.wallet.tienda.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    boolean existsByName(String name);
    Optional<Expense> findByName(String name);
    List<Expense> findAllByDate(Date date);
}
