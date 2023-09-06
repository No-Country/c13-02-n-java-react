package com.wallet.tienda.service;


import com.wallet.tienda.dto.request.SaleDTOReq;
import com.wallet.tienda.dto.response.SaleDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.model.Sale;
import com.wallet.tienda.repository.ISaleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService{

    private final ISaleRepository saleRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(SaleDTOReq saleDTOReq) throws Exception {
        Sale sale = modelMapper.map(saleDTOReq, Sale.class);
        //Double price = saleDTOReq.getSoldProducts().stream().mapToDouble(soldProduct -> soldProduct.getPrice() * soldProduct.getQuantity()).sum();
        //sale.setPrice(price);
        saleRepository.save(sale);
    }

    @Override
    public SaleDTORes getById(Long id) throws IdNotFoundException {
        return modelMapper.map(saleRepository.findById(id).orElseThrow(() -> new IdNotFoundException("La venta con el id " + id + "no se encuentra en base de datos")), SaleDTORes.class);
    }

    @Override
    public Page<SaleDTORes> getAll(Pageable pageable) {
        var sales =  saleRepository.findAll(pageable);
        var salesDtoRes = sales.stream().map(sale -> modelMapper.map(sale, SaleDTORes.class)).toList();
        return new PageImpl<>(salesDtoRes, pageable , salesDtoRes.size());
    }

    @Override
    public void update(SaleDTOReq saleDTOReq) throws IdNotFoundException {
        if (!saleRepository.existsById(saleDTOReq.getId())) {
            throw new IdNotFoundException("La venta con id " + saleDTOReq.getId() + " no existe en base de datos");
        }
        saleRepository.save(modelMapper.map(saleDTOReq, Sale.class));
    }

    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }
}
