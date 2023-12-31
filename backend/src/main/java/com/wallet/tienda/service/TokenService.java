package com.wallet.tienda.service;

import com.wallet.tienda.model.Token;
import com.wallet.tienda.repository.ICustomerUserRepository;
import com.wallet.tienda.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenService implements ITokenService{

    @Autowired
    private ITokenRepository tokenRepository;
    @Autowired
    private ICustomerUserRepository userRepository;


    /**
     * Guarda en BD y devuelve un token de recuperacion de contraseña
     * @param username email de usuario
     * @return dto de token
     * @throws UsernameNotFoundException mensaje de excepcion de email no encontrado
     */
    @Override
    public Token saveToken(String username) throws UsernameNotFoundException {

        var userDB = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no se encuentra registrado"));

        String generateToken = UUID.randomUUID().toString();
        var token = new Token();
        token.setToken(generateToken);
        token.setExpirationTime(LocalDateTime.now().plusMinutes(15));

        if (userDB.getToken() == null) {
            token.setCustomerUser(userDB);
            return tokenRepository.save(token);
        }
        else {
            this.deleteById(userDB.getToken().getId());
            token.setCustomerUser(userDB);
            return tokenRepository.save(token);
        }
    }

    /**
     * Devuelve el token guardado en base de datos por token
     * @param token dto de token
     * @return dto de token
     * @throws Exception mensaje de excepcion
     */
    @Override
    public Token getToken(String token) throws Exception {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new Exception("El token no ha sido encotrado"));
    }

    /**
     * Elimina el token de BD
     * @param tokenId numero de id del token
     */
    @Override
    public void deleteById(Long tokenId) {
        tokenRepository.deleteById(tokenId);
    }
}
