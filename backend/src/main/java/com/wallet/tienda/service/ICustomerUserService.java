package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.UserDTOReq;
import com.wallet.tienda.dto.response.UserDTORes;
import com.wallet.tienda.exception.EmailExistsException;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.ConfirmPasswordException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.management.relation.RoleNotFoundException;

public interface ICustomerUserService {

    void saveUser(UserDTOReq user) throws EmailExistsException,
            IdNotFoundException, RoleNotFoundException, ConfirmPasswordException;
    UserDTORes getUserById(Long userId) throws IdNotFoundException;
    Page<UserDTORes> getAllUsers(Pageable pageable);
    void updateUser(UserDTOReq user) throws ConfirmPasswordException, RoleNotFoundException,
            EmailExistsException, IdNotFoundException;
    void deleteUser(Long userId) throws IdNotFoundException;

}
