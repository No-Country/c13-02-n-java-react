package com.wallet.tienda.config;

import com.wallet.tienda.config.filter.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de configuracion de autenticacion y autorizacion de paths para roles de usuarios
 * @Autor David Ramon Thomen
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class CustomSecurityFilterChain {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;


    /**
     * Metodo para configurar la seguridad de la aplicacion web, autorizaciones, tipo de sesion, proveedor de autenticacion y filtros
     * @param httpSecurity configuracion de seguridad http
     * @param authenticationManager administrador de autenticacion
     * @return securityFilterChain configuracion de seguridad http
     * @throws Exception mensaje de excepcion si la configuracion falla
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/password/**", "/api/v1/login", "/swagger-ui/**", "/v3/api-docs/**")
                                .permitAll())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(
                                "/api/v1/products",
                                "/api/v1/sales",
                                "/api/v1/sold-products",
                                "/api/v1/brands",
                                "/api/v1/buys",
                                "/api/v1/bought-products",
                                "/api/v1/providers",
                                "/api/v1/categories",
                                "/api/v1/expenses").hasAnyRole("USER", "ADMIN")

                )
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/users", "/api/v1/reports").hasRole("ADMIN")
                )
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated()
                )
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
