package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.OrderDTOReq;
import com.wallet.tienda.dto.response.OrderDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    void saveOrder(OrderDTOReq orderDTOReq) throws NameExistsException;
    OrderDTORes getOrderById(Long orderId) throws IdNotFoundException;
    Page<OrderDTORes> getOrders(Pageable pageable);
    void updateOrder(OrderDTOReq orderDTOReq) throws IdNotFoundException, NameExistsException;
    void deleteOrder(Long orderId);
}
