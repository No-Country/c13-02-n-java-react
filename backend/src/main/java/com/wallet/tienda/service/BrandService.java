package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BrandDTOReq;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.model.Brand;
import com.wallet.tienda.repository.IBrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Clase de servicio de marcas
 * @Autor Maria Paez
 */
@Service
public class BrandService implements IBrandService{

    @Autowired
    private IBrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Metodo para traer de base de datos todos las marcas paginadas
     * @param pageable configuracion de paginado
     * @return paginado de marcas
     */
    @Override
    public Page<BrandDTORes> listAllBrands(Pageable pageable) {
        var brandRep = brandRepository.findAll(pageable);
        var brandDTORes = brandRep.stream().map((user) -> modelMapper.map(user, BrandDTORes.class)).toList();
        return new PageImpl<>(brandDTORes, pageable, brandRep.getTotalElements());
    }

    /**
     * Metodo para guardar una marca
     * @param brandDTOReq dto de marca
     * @return Respuesta de estado http creado
     */
    @Override
    public void save(BrandDTOReq brandDTOReq) {
        brandRepository.save(modelMapper.map(brandDTOReq, Brand.class));
    }

    /**
     * Busca una marca por id
     * @param id numero de id
     * @return dto de marca
     */
    @Override
    public BrandDTORes searchById(Long id) {
        return modelMapper.map(brandRepository.findById(id).get(), BrandDTORes.class);
    }

    /**
     * actualiza una marca
     * @param brandDTOReq dto de marca
     */
    @Override
    public void update(BrandDTOReq brandDTOReq) {
        brandRepository.save(modelMapper.map(brandDTOReq, Brand.class));
    }

    /**
     * elimina una marca
     * @param id numero de id de marca
     */
    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
