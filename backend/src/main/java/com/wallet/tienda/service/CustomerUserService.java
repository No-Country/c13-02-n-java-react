package com.wallet.tienda.service;

import com.wallet.tienda.dto.request.UserDTOReq;
import com.wallet.tienda.dto.response.UserDTORes;
import com.wallet.tienda.exception.EmailExistsException;
import com.wallet.tienda.exception.IdNotFoundException;
import com.wallet.tienda.exception.ConfirmPasswordException;
import com.wallet.tienda.model.CustomerUser;
import com.wallet.tienda.repository.ICustomerUserRepository;
import com.wallet.tienda.repository.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;

@Service
public class CustomerUserService implements ICustomerUserService {

    @Autowired
    private ICustomerUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleRepository roleRepository;


    //CREAR USUARIO
    @Override
    public void saveUser(UserDTOReq userDTO) throws EmailExistsException, ConfirmPasswordException, RoleNotFoundException {
        this.validateDataBeforeSavingUser(userDTO);
        var saveUser = modelMapper.map(userDTO, CustomerUser.class);
        saveUser.setEnable(true);
        saveUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        saveUser.setRole(roleRepository.findByRole("USER")
                .orElseThrow(() -> new RoleNotFoundException("Debe crear un rol USER antes de crear un usuario")));
        userRepository.save(saveUser);
    }

    //MOSTRAR UN USUARIO POR ID
    @Override
    public UserDTORes getUserById(Long userId) throws IdNotFoundException {
        return modelMapper.map(userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("El id " + userId + " no se encuentra registrado")), UserDTORes.class);
    }

    //LISTAR USUARIOS
    @Override
    public Page<UserDTORes> getAllUsers(Pageable pageable) {
        var usersBD = userRepository.findAll(pageable);
        var usersDTO = new ArrayList<UserDTORes>();

        for (CustomerUser user : usersBD) {
            if(user.isEnable()) usersDTO.add(modelMapper.map(user, UserDTORes.class));
        }
        return new PageImpl<>(usersDTO, pageable, usersDTO.size());
    }

    //MODIFICAR USUARIO
    @Override
    public void updateUser(UserDTOReq userDTO) throws EmailExistsException, IdNotFoundException, ConfirmPasswordException {
        var userBD = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IdNotFoundException("El id " + userDTO.getId() + " no se encuentra registrado"));

        this.validateDataBeforeUpdatingUser(userDTO, userBD.getUsername());
        userDTO.setPassword(userBD.getPassword());
        var saveUser = modelMapper.map(userDTO, CustomerUser.class);
        saveUser.setEnable(true);
        userRepository.save(saveUser);
    }

    //ELIMINADO LÓGICO DE USUARIO
    @Override
    public void deleteUser(Long userId) throws IdNotFoundException {
        var userBD = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("El id" + userId + " no se encuentra registrado"));
        userBD.setEnable(false);
        userRepository.save(userBD);
    }


    //VALIDA DATOS ANTES DE GUARDAR UN USUARIO
    public void validateDataBeforeSavingUser(UserDTOReq userDTO) throws EmailExistsException, ConfirmPasswordException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new EmailExistsException("El Email " + userDTO.getUsername() + " ya se encuentra registrado." +
                    " Ingrese un nuevo Email");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new ConfirmPasswordException("Los campos contraseña y confirmar contraseña deben coincidir");
        }
    }

    //VALIDA DATOS ANTES DE MODIFICAR UN USUARIO
    public void validateDataBeforeUpdatingUser(UserDTOReq userDTO, String usernameDB) throws EmailExistsException, ConfirmPasswordException {

        if (!userDTO.getUsername().equals(usernameDB) && userRepository.existsByUsername(userDTO.getUsername())) {
            throw new EmailExistsException("El Email " + userDTO.getUsername() + " ya se encuentra registrado");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new ConfirmPasswordException("Los campos contraseña y confirmar contraseña deben coincidir");
        }

    }


}
