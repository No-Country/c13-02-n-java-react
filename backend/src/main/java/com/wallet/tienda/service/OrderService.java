package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.OrderDTOReq;
import com.wallet.tienda.dto.response.OrderDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.model.Order;
import com.wallet.tienda.repository.IOrderRepository;
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
public class OrderService implements IOrderService{
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IWordsConverter wordsConverter;
    @Autowired
    private ModelMapper modelMapper;

    //CREAR UNA ORDEN
    @Override
    public void saveOrder(OrderDTOReq orderDTO) throws NameExistsException {
        if (orderRepository.existsByName(orderDTO.getName())) {
            throw new NameExistsException("El nombre " + orderDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        orderDTO.setName(wordsConverter.capitalizeWords(orderDTO.getName()));

        orderRepository.save(modelMapper.map(orderDTO, Order.class));
    }

    //MUESTRA UNA ORDEN POR ID
    @Override
    public OrderDTORes getOrderById(Long id) throws IdNotFoundException {
        return modelMapper.map(orderRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("El id " + id + " no existe")), OrderDTORes.class);
    }

    //LISTA ORDENES PAGINADAS
    @Override
    public Page<OrderDTORes> getOrders(Pageable pageable) {
        var orders = orderRepository.findAll(pageable);
        var ordersDTO = new ArrayList<OrderDTORes>();

        for (Order order : orders) {
            ordersDTO.add(modelMapper.map(order, OrderDTORes.class));
        }
        return new PageImpl<>(ordersDTO, pageable, ordersDTO.size());
    }

    //MODIFICA UNA ORDEN POR ID
    @Override
    public void updateOrder(OrderDTOReq orderDTO) throws IdNotFoundException, NameExistsException {
        var orderDB = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + orderDTO + " no existe. Ingrese un nuevo id"));
        //valida que el nombre de la orden no exista y si existe que coincida con la orden encontrada
        if (!orderDTO.getName().equals(orderDB.getName()) && orderRepository.existsByName(orderDTO.getName())) {
            throw new NameExistsException("El nombre " + orderDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        orderDTO.setName(wordsConverter.capitalizeWords(orderDTO.getName()));
        orderRepository.save(modelMapper.map(orderDTO, Order.class));
    }

    //ELIMINA UNA ORDEN POR ID
    @Override
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
