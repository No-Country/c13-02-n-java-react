package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ExpenseDTOReq;
import com.wallet.tienda.dto.response.ExpenseDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IExpenseService {

    void saveExpense(ExpenseDTOReq expenseDTOReq) throws NameExistsException;
    ExpenseDTORes getExpenseById(Long expenseId) throws IdNotFoundException;
    Page<ExpenseDTORes> getExpenses(Pageable pageable);
    void updateExpense(ExpenseDTOReq expenseDTOReq) throws IdNotFoundException;
    void deleteExpense(Long expenseId);
}
