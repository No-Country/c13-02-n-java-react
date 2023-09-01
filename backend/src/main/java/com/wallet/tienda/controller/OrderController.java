package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.OrderDTOReq;
import com.wallet.tienda.dto.response.OrderDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Controlador de orden")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    
    private final IOrderService orderService;

    //@Operation(
            //summary = "Guarda una orden",
            //description = "Guarda una orden y devuelve un Codigo de estado 201 creado"
    //)
    @PostMapping()
    public ResponseEntity<HttpStatus> saveOrder(@Valid @RequestBody OrderDTOReq orderDTOReq) throws NameExistsException {
        orderService.saveOrder(orderDTOReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@Operation(
            //summary = "Trae una orden",
            //description = "Busca una orden por id y devuelve un Codigo de estado 200 y los datos de la orden"
    //)
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTORes> getOrder(@PathVariable Long id) throws IdNotFoundException {

        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    //@Operation(
            //summary = "Trae todas las ordenes",
            //description = "Trae todas las ordenes de base de datos y devuelve un Codigo de estado 200 y el listado de ordenes"
    //)
    @GetMapping
    public ResponseEntity<Page<OrderDTORes>> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrders(pageable));
    }

    //@Operation(
            //summary = "Actualiza una orden",
            //description = "Busca una orden por id y la actualiza, devuelve un Codigo de estado 204"
    //)
    @PutMapping()
    public ResponseEntity<HttpStatus> updateOrder(@Valid @RequestBody OrderDTOReq orderDTOReq) throws Exception {
        orderService.updateOrder(orderDTOReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@Operation(
            //summary = "Elimina una orden",
            //description = "Elimina de forma logica una orden por id, devuelve un Codigo de estado 204"
    //)
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
