package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.ProviderDTOReq;
import com.wallet.tienda.dto.response.ProviderDTORes;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.NameExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProviderService {

    void saveProvider(ProviderDTOReq providerDTO) throws NameExistsException;
    ProviderDTORes getProviderById(Long providerId) throws IdNotFoundException;

    Page<ProviderDTORes> getAllProviders(Pageable pageable);
    void updateProvider(ProviderDTOReq providerDTO) throws IdNotFoundException, NameExistsException;
    void deleteProvider(Long providerID);

}
