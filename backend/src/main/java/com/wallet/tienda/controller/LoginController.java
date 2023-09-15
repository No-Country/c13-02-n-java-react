package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.AuthRequestDTOReq;
import com.wallet.tienda.dto.response.AuthResponseDTORes;
import com.wallet.tienda.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("api/v1/login")
    public ResponseEntity<AuthResponseDTORes> login(@RequestBody AuthRequestDTOReq authDTO){
        return ResponseEntity.ok(loginService.authenticate(authDTO));
    }
}
