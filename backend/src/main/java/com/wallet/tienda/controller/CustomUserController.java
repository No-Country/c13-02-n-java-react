package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.UserDTOReq;
import com.wallet.tienda.dto.response.UserDTORes;
import com.wallet.tienda.exception.EmailExistsException;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.ConfirmPasswordException;
import com.wallet.tienda.service.ICustomerUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/api/v1/users")
public class CustomUserController {

    @Autowired
    private ICustomerUserService userService;

    @Operation(
            summary = "Guarda una usuario",
            description = "Guarda un usuario en Base de datos y devuelve un Codigo de estado 201 creado"
    )
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody UserDTOReq userDTO) throws ConfirmPasswordException, RoleNotFoundException, EmailExistsException, IdNotFoundException {
        userService.saveUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Trae un usuario",
            description = "Busca un usuario por id y devuelve una respuesta http con un Codigo de estado 200 con los datos del usuario"
    )
    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTORes> getUserById(@PathVariable Long idUser) throws IdNotFoundException {
        return ResponseEntity.ok(userService.getUserById(idUser));
    }

    @Operation(
            summary = "Traer todas los usuarios",
            description = "Trae todas los usuarios de base de datos y devuelve una respuesta http con un Codigo de estado 200 y el listado de usuarios"
    )
    @GetMapping()
    public ResponseEntity<Page<UserDTORes>> getUser(Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @Operation(
            summary = "Actualiza un usuario",
            description = "Busca un usuario por id y lo actualiza, si lo actualiza devuelve una respuesta http con Codigo de estado 204"
    )
    @PutMapping()
    public ResponseEntity<UserDTOReq> updateUser(@Valid @RequestBody UserDTOReq userDTO) throws ConfirmPasswordException, RoleNotFoundException, EmailExistsException, IdNotFoundException {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Elimina una usuario",
            description = "Elimina un usuario por id, devuelve una respuesta http con Codigo de estado 204"
    )
    @DeleteMapping("/{idUser}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long idUser ) throws IdNotFoundException {
        userService.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
