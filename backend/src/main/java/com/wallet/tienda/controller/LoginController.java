package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.AuthRequestDTOReq;
import com.wallet.tienda.dto.response.AuthResponseDTORes;
import com.wallet.tienda.service.ILoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name= "Clase de controladora de login")
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Operation(
            summary = "Login de usuario",
            description = "")
    @PostMapping("api/v1/login")
    public ResponseEntity<AuthResponseDTORes> login(@RequestBody AuthRequestDTOReq authDTO){
        return ResponseEntity.ok(loginService.authenticate(authDTO));
    }
}
