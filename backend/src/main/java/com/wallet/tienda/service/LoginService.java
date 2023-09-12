package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.AuthRequestDTOReq;
import com.wallet.tienda.dto.response.AuthResponseDTORes;
import com.wallet.tienda.repository.ICustomerUserRepository;
import com.wallet.tienda.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService{

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ICustomerUserRepository userRepository;

    @Override
    public AuthResponseDTORes authenticate(AuthRequestDTOReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = jwtUtils.generateToken(user);
        return AuthResponseDTORes.builder()
                .token(token)
                .build();
    }
}
