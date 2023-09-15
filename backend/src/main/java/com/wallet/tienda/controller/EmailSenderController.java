package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.RecoverPasswordDTOReq;
import com.wallet.tienda.dto.request.ResetPasswordDTOReq;
import com.wallet.tienda.service.ICustomerUserService;
import com.wallet.tienda.service.IEmailService;
import com.wallet.tienda.service.ITokenService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/password")
public class EmailSenderController {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ICustomerUserService userService;

    @Autowired
    private ModelMapper modelMapper;

//    @Operation(summary = "Enviar mail con token",
//            description = "Genera un token para la recuperacion de contraseña y la envia por correo al usuario, devuelve un codigo de estado creado ")
    @PostMapping("/recovery")
    public ResponseEntity<Map<String, Object>> sendEmail(@Valid @RequestBody RecoverPasswordDTOReq user)
            throws UsernameNotFoundException, MessagingException {

        //guardar token en BD
        var token = tokenService.saveToken(user.getEmail());

        //Map para devolver la respuesta al cliente
        Map<String, Object> response = new HashMap<>();
        response.put("token" , token.getToken());
        response.put("expiration", token.getExpirationTime());

        //método de envio de email junto con el token
        emailService.sendEmail(token);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @Operation(summary = "Cambiar la contraseña",
//            description = "Actualiza la contraseña del usuario en base de datos y devuelve un codigo de estado 200 ok"
//    )

    @PostMapping("/change")
    public ResponseEntity<HttpStatus> resetPassword(@Valid @RequestBody ResetPasswordDTOReq resetPassword) throws Exception {
        emailService.resetPassword(resetPassword);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
