package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ProviderDTOReq;
import com.wallet.tienda.dto.response.ProviderDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import com.wallet.tienda.model.Provider;
import com.wallet.tienda.repository.IProviderRepository;
import com.wallet.tienda.util.IWordsConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProviderService implements IProviderService{

    @Autowired
    private IProviderRepository providerRepository;
    @Autowired
    private IWordsConverter wordsConverter;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Guarda un proveedor en base de datos
     * @param providerDTO dto de proveedor
     * @throws NameExistsException mensaje de excepcion de nombre de proveedor ya existe
     */
    @Override
    public void saveProvider(ProviderDTOReq providerDTO) throws NameExistsException {
        if (providerRepository.existsByName(providerDTO.getName())) {
            throw new NameExistsException("El nombre " + providerDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        providerDTO.setName(wordsConverter.capitalizeWords(providerDTO.getName()));

        providerRepository.save(modelMapper.map(providerDTO, Provider.class));
    }

    /**
     * Busca y devuelve un proveedor por id
     * @param providerId numero de id de proveedor
     * @return dto de proveedor
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Override
    public ProviderDTORes getProviderById(Long providerId) throws IdNotFoundException {
        return modelMapper.map(providerRepository.findById(providerId)
                .orElseThrow(() -> new IdNotFoundException("El id " + providerId + " no exite. Ingrese un nuevo id")), ProviderDTORes.class);
    }

    /**
     * Busca y devuelve una lista de proveedores paginada
     * @param pageable configuracion de paginacion
     * @return lista de proveedores paginada
     */
    @Override
    public Page<ProviderDTORes> getAllProviders(Pageable pageable) {
        var providersDB = providerRepository.findAll(pageable);
        var providersDTO = new ArrayList<ProviderDTORes>();
        //recorre la lista de proovedores de la BBDD, los convierte a DTO y los guarda en una listaDTO
        for (Provider provider : providersDB) {
            providersDTO.add(modelMapper.map(provider, ProviderDTORes.class));
        }
        return new PageImpl<>(providersDTO, pageable, providersDB.getTotalElements());
    }

    /**
     * Actualiza un proveedor por id
     * @param providerDTO dto de proveedor
     * @throws IdNotFoundException mensaje de excepcion de id de proveedor no encontrado
     * @throws NameExistsException mensaje de excepcion de nombre de proveedor ya existe en BD
     */
    @Override
    public void updateProvider(ProviderDTOReq providerDTO) throws IdNotFoundException, NameExistsException {
        var providerDB = providerRepository.findById(providerDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + providerDTO + " no existe. Ingrese un nuevo id"));
        //valida que el nombre del proovedor no exista y si existe que coincida con la proovedor encontrado
        if (!providerDTO.getName().equals(providerDB.getName()) && providerRepository.existsByName(providerDTO.getName())) {
            throw new NameExistsException("El nombre " + providerDTO.getName() + " ya existe. Ingrese un nuevo nombre");
        }
        //convierte la primer letra de cada palabra en mayúscula
        providerDTO.setName(wordsConverter.capitalizeWords(providerDTO.getName()));
        providerRepository.save(modelMapper.map(providerDTO, Provider.class));
    }

    /**
     * Elimina un proveedor de BD
     * @param providerID numero de id de proveedor
     */
    @Override
    public void deleteProvider(Long providerID) {
        providerRepository.deleteById(providerID);
    }
}
