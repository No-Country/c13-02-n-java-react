package com.wallet.tienda.controller;

import com.wallet.tienda.dto.request.UserDTOReq;
import com.wallet.tienda.dto.response.UserDTORes;
import com.wallet.tienda.exception.EmailExistsException;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.ConfirmPasswordException;
import com.wallet.tienda.service.ICustomerUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/api/v1/users")
public class CustomUserController {

    @Autowired
    private ICustomerUserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody UserDTOReq userDTO) throws ConfirmPasswordException, RoleNotFoundException, EmailExistsException, IdNotFoundException {
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
    public ResponseEntity<UserDTOReq> updateUser(@Valid @RequestBody UserDTOReq userDTO) throws ConfirmPasswordException, RoleNotFoundException, EmailExistsException, IdNotFoundException {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long idUser ) throws IdNotFoundException {
        userService.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
