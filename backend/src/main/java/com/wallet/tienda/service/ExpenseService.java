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
public class ExpenseService implements IExpenseService {

    @Autowired
    private IExpenseRepository expenseRepository;
    @Autowired
    private IWordsConverter wordsConverter;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guardar un gasto en base de datos
     * @param expenseDTO dto de gasto
     * @throws NameExistsException mensaje de exccepcion de nombre ya existe en BD
     */
    @Override
    public void saveExpense(ExpenseDTOReq expenseDTO) throws NameExistsException {
        if (expenseRepository.existsByName(expenseDTO.getName())) {
            throw new NameExistsException("El nombre " + expenseDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        expenseDTO.setName(wordsConverter.capitalizeWords(expenseDTO.getName()));

        expenseRepository.save(modelMapper.map(expenseDTO, Expense.class));
    }

    /**
     * Devuelve un gasto por id
     * @param id numero de id de gasto
     * @return dto de gasto
     * @throws IdNotFoundException mensaje de excepcion de id de gasto no encontrado
     */
    @Override
    public ExpenseDTORes getExpenseById(Long id) throws IdNotFoundException {
        return modelMapper.map(expenseRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("El id " + id + " no existe")), ExpenseDTORes.class);
    }

    /**
     * Devuelve todos los gastos
     * @param pageable configuracion de paginacion
     * @return lista de gastos paginados
     */
    @Override
    public Page<ExpenseDTORes> getExpenses(Pageable pageable) {
        var expenses = expenseRepository.findAll(pageable);
        var expensesDTO = new ArrayList<ExpenseDTORes>();

        for (Expense expense : expenses) {
            expensesDTO.add(modelMapper.map(expense, ExpenseDTORes.class));
        }
        return new PageImpl<>(expensesDTO, pageable, expenses.getTotalElements());
    }

    /**
     * Actualiza un gasto por id
     * @param expenseDTO dto de gasto
     * @throws IdNotFoundException mensaje de excepcion de id de gasto no encontrado en BD
     * @throws NameExistsException mensaje de excepcion de nombre ya existe en base de datos
     */
    @Override
    public void updateExpense(ExpenseDTOReq expenseDTO) throws IdNotFoundException, NameExistsException {
        var productDB = expenseRepository.findById(expenseDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + expenseDTO + " no existe. Ingrese un nuevo id"));
        //valida que el nombre del gasto no exista y si existe que coincida con el gasto encontrado
        if (!expenseDTO.getName().equals(productDB.getName()) && expenseRepository.existsByName(expenseDTO.getName())) {
            throw new NameExistsException("El nombre " + expenseDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        expenseDTO.setName(wordsConverter.capitalizeWords(expenseDTO.getName()));
        expenseRepository.save(modelMapper.map(expenseDTO, Expense.class));
    }

    /**
     * Elimina un gasto de base de datos
     * @param id numero de id de gasto
     */
    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

}