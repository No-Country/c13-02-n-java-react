package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.ExpenseDTOReq;
import com.wallet.tienda.dto.response.ExpenseDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.service.IExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase de controlador de gastos
 * @Autor Damian Della Corte
 */
@Tag(name = "Controlador de gastos")
@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    @Autowired
    private IExpenseService expenseService;

    /**
     * Guarda un gasto en base de datos
     * @param expenseDTOReq dto de gasto
     * @return respuesta http con estado creado
     * @throws NameExistsException mensaje de excepcion de nombre ya existe en base de datos
     */
    @Operation(
            summary = "Guarda un gasto",
            description = "Guarda un gasto y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping()
    public ResponseEntity<HttpStatus> saveExpense(@Valid @RequestBody ExpenseDTOReq expenseDTOReq) throws NameExistsException {
        expenseService.saveExpense(expenseDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Busca un gasto por id de base de datos
     * @param id numero de id
     * @return respuesta http con dto de gasto
     * @throws IdNotFoundException mensaje de excepcion de id de gastop no encontrado
     */
    @Operation(
            summary = "Trae un gasto",
            description = "Busca un gasto por id y devuelve un Codigo de estado 200 y los datos del gasto"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTORes> getExpense(@PathVariable Long id) throws IdNotFoundException {

        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    /**
     * Trae todos los gastos de base de datos
     * @param pageable configuracion de paginacion
     * @return respuesta http con lista de gastos paginada
     */
    @Operation(
            summary = "Trae todos los gastos",
            description = "Trae todos los gastos de base de datos y devuelve un Codigo de estado 200 y el listado de gastos"
    )
    @GetMapping
    public ResponseEntity<Page<ExpenseDTORes>> getAllExpenses(Pageable pageable) {
        return ResponseEntity.ok(expenseService.getExpenses(pageable));
    }

    /**
     * Busca un gasto por id y la actualiza
     * @param expenseDTOReq dto de gasto
     * @return respuesta http con estado sin contenido
     * @throws Exception mensaje de excepcion
     */
    @Operation(
            summary = "Actualiza un gasto",
            description = "Busca un gasto por id y la actualiza, devuelve un Codigo de estado 204"
    )
    @PutMapping()
    public ResponseEntity<HttpStatus> updateExpense(@Valid @RequestBody ExpenseDTOReq expenseDTOReq) throws Exception {
        expenseService.updateExpense(expenseDTOReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Elimina un gasto por id
     * @param id numero de id
     * @return respuesta http con estado sin contenido
     */
    @Operation(
            summary = "Elimina un gasto",
            description = "Elimina un gasto por id, devuelve un Codigo de estado 204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
