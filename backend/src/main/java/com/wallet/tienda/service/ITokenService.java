package com.wallet.tienda.service;

import com.wallet.tienda.model.Token;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ITokenService {
    Token saveToken(String token) throws UsernameNotFoundException;
    Token getToken(String token) throws Exception;
    void deleteById(Long tokenId);
}
