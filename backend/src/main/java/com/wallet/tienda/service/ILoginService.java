package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.AuthRequestDTOReq;
import com.wallet.tienda.dto.response.AuthResponseDTORes;

public interface ILoginService {
    AuthResponseDTORes authenticate(AuthRequestDTOReq request);
}
