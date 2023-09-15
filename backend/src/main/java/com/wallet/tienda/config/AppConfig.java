package com.wallet.tienda.config;

import com.wallet.tienda.repository.ICustomerUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase de configuracion de beans necesarios para la app
 * @Autor David Ramon Thomen
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final ICustomerUserRepository userRepository;
    /**
     * Metodo que configura el bean encriptador de contraseña
     * @return encriptador de contraseña BCrypt
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Metodo que configura el bean de busqueda de informacion de usuario necesario para spring security
     * @return UserDetails
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Metodo que devuelve el proveedor de autenticacion configurado
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Metodo para inyectar el administrador de autenticacion
     * @param config configuracion de autenticacion de spring security
     * @return AuthenticationManager administrador de autenticacion
     * @throws Exception excepcion arrojada si falla en traer el authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean para mapear del modelo al DTO o del DTO al modelo
     * @return modelMapper mapeador de informacion de una clase a otra
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
