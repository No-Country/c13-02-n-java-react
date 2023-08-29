package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.UserDTOReq;
import com.wallet.tienda.dto.response.UserDTORes;
import com.wallet.tienda.exception.EmailExistsException;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.PasswordException;
import com.wallet.tienda.service.ICustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/api/v1/usuarios")
public class CustomUserController {

    @Autowired
    private ICustomerUserService userService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDTOReq userDTO) throws PasswordException, RoleNotFoundException, EmailExistsException, IdNotFoundException {
        userService.saveUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserDTORes> getUserById(@PathVariable Long idUser) throws IdNotFoundException {
        return ResponseEntity.ok(userService.getUserById(idUser));
    }

    @GetMapping()
    public ResponseEntity<Page<UserDTORes>> getUser(Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @PutMapping()
    public ResponseEntity<UserDTOReq> updateUser(@RequestBody UserDTOReq userDTO) throws PasswordException, RoleNotFoundException, EmailExistsException, IdNotFoundException {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long idUser ) throws IdNotFoundException {
        userService.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
