package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ExpenseDTOReq;
import com.wallet.tienda.dto.response.ExpenseDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.model.Expense;
import com.wallet.tienda.repository.IExpenseRepository;
import com.wallet.tienda.util.IWordsConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ExpenseService implements IExpenseService {

    @Autowired
    private final IExpenseRepository expenseRepository;
    @Autowired
    private static IWordsConverter wordsConverter;
    @Autowired
    private final ModelMapper modelMapper;

    //CREAR UN GASTO
    @Override
    public void saveExpense(ExpenseDTOReq expenseDTO) throws NameExistsException {
        if (expenseRepository.existsByName(expenseDTO.getName())) {
            throw new NameExistsException("El nombre " + expenseDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        expenseDTO.setName(wordsConverter.capitalizeWords(expenseDTO.getName()));

        expenseRepository.save(modelMapper.map(expenseDTO, Expense.class));
    }

    //MUESTRA UN GASTO POR ID
    @Override
    public ExpenseDTORes getExpenseById(Long id) throws IdNotFoundException {
        return modelMapper.map(expenseRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("El id " + id + " no existe")), ExpenseDTORes.class);
    }

    //LISTA GASTOS PAGINADOS
    @Override
    public Page<ExpenseDTORes> getExpenses(Pageable pageable) {
        var expenses = expenseRepository.findAll(pageable);
        var expensesDTO = new ArrayList<ExpenseDTORes>();

        for (Expense expense : expenses) {
            expensesDTO.add(modelMapper.map(expense, ExpenseDTORes.class));
        }
        return new PageImpl<>(expensesDTO, pageable, expensesDTO.size());
    }

    //MODIFICA UN GASTO POR ID
    @Override
    public void updateExpense(ExpenseDTOReq expenseDTOReq) throws IdNotFoundException {
        if (!expenseRepository.existsById(expenseDTOReq.getId())) {
            throw new IdNotFoundException("El id " + expenseDTOReq.getId() + " no existe");
        }
        var expenseUpdate = modelMapper.map(expenseDTOReq, Expense.class);
        expenseRepository.save(expenseUpdate);
    }

    //ELIMINA UN GASTO POR ID
    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

}