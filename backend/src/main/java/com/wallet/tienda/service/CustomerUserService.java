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


    /**
     * Guarda un usuario
     * @param userDTO dto de usuario
     * @throws EmailExistsException mensaje de excepcion de email ya existe
     * @throws ConfirmPasswordException mensaje de excepcion de coincidencia de password incorrecto
     * @throws RoleNotFoundException mensaje de excepcion de id de role no existe
     */
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

    /**
     * Busca en base de datos y devuelve un usuario por id
     * @param userId numero de id de usuario
     * @return dto de usuario
     * @throws IdNotFoundException mensaje de excepcion de id de usuario no encontrado
     */
    @Override
    public UserDTORes getUserById(Long userId) throws IdNotFoundException {
        return modelMapper.map(userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("El id " + userId + " no se encuentra registrado")), UserDTORes.class);
    }

    /**
     * Devuelve lista de usuarios paginados
     * @param pageable configuracion de paginado
     * @return lista de usuarios paginados
     */
    @Override
    public Page<UserDTORes> getAllUsers(Pageable pageable) {
        var usersDB = userRepository.findAll(pageable);
        var usersDTO = new ArrayList<UserDTORes>();

        for (CustomerUser user : usersDB) {
            if(user.isEnable()) usersDTO.add(modelMapper.map(user, UserDTORes.class));
        }
        return new PageImpl<>(usersDTO, pageable, usersDB.getTotalElements());
    }

    /**
     * Actualiza un usuario en base de datos
     * @param userDTO dto de usuario
     * @throws EmailExistsException mensaje de excepcion de email ya existe en BD
     * @throws IdNotFoundException mensaje de excepcion de id de usuario no encontrado
     * @throws ConfirmPasswordException mensaje de excepcion de password no coincide
     */
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

    /**
     * Elimina un usuario de BD
     * @param userId numero de id de usuario
     * @throws IdNotFoundException mensaje de excepcion de id no encontrado
     */
    @Override
    public void deleteUser(Long userId) throws IdNotFoundException {
        var userBD = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException("El id" + userId + " no se encuentra registrado"));
        userBD.setEnable(false);
        userRepository.save(userBD);
    }


    /**
     * Valida los datos de usuario eantes de guardar en BD
     * @param userDTO dto de usuario
     * @throws EmailExistsException mensaje de excepcion email ya existe
     * @throws ConfirmPasswordException mensaje de excepcion de error de coincidencia de password
     */
    public void validateDataBeforeSavingUser(UserDTOReq userDTO) throws EmailExistsException, ConfirmPasswordException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new EmailExistsException("El Email " + userDTO.getUsername() + " ya se encuentra registrado." +
                    " Ingrese un nuevo Email");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new ConfirmPasswordException("Los campos contraseña y confirmar contraseña deben coincidir");
        }
    }

    /**
     * Valida datos de usuario antes de actualizar usuario
     * @param userDTO dto de uduario
     * @param usernameDB email
     * @throws EmailExistsException mensaje de excepcion email ya existe en base de datos
     * @throws ConfirmPasswordException mensaje de excepcio de error de coincidencia de password
     */
    public void validateDataBeforeUpdatingUser(UserDTOReq userDTO, String usernameDB) throws EmailExistsException, ConfirmPasswordException {

        if (!userDTO.getUsername().equals(usernameDB) && userRepository.existsByUsername(userDTO.getUsername())) {
            throw new EmailExistsException("El Email " + userDTO.getUsername() + " ya se encuentra registrado");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new ConfirmPasswordException("Los campos contraseña y confirmar contraseña deben coincidir");
        }

    }


}
