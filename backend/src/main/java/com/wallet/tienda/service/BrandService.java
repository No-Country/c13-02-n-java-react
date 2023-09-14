package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.BrandDTOReq;
import com.wallet.tienda.dto.response.BrandDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
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
 * @Autor Thomen David
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
     */
    @Override
    public void save(BrandDTOReq brandDTOReq) throws NameExistsException {
        if(brandRepository.existsByName(brandDTOReq.getName())) {
            throw new NameExistsException("La marca con nombre " + brandDTOReq.getName() +" ya exise en base de datos");
        }
        brandRepository.save(modelMapper.map(brandDTOReq, Brand.class));
    }

    /**
     * Busca una marca por id
     * @param id numero de id
     * @return dto de marca
     */
    @Override
    public BrandDTORes searchById(Long id) throws IdNotFoundException {
        return modelMapper.map(brandRepository.findById(id).orElseThrow(() -> new IdNotFoundException("La marca con id " + id + " no existe en base de datos")), BrandDTORes.class);
    }

    /**
     * actualiza una marca en BD
     * @param brandDTOReq dto de marca
     */
    @Override
    public void update(BrandDTOReq brandDTOReq) throws NameExistsException, IdNotFoundException {
        var brandDB = brandRepository.findById(brandDTOReq.getId()).orElseThrow(() -> new IdNotFoundException("La marca con id " + brandDTOReq.getId() + " no existe en base de datos"));
        //valida que el nombre de la marca no exista y si existe que coincida con la marca encontrada
        if (!brandDTOReq.getName().equals(brandDB.getName()) && brandRepository.existsByName(brandDTOReq.getName())) {
            throw new NameExistsException("El nombre " + brandDTOReq.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        brandRepository.save(modelMapper.map(brandDTOReq, Brand.class));
    }

    /**
     * elimina una marca en BD
     * @param id numero de id de marca
     */
    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
