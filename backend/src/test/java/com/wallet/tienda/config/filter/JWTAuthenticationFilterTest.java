package com.wallet.tienda.config.filter;

import com.wallet.tienda.util.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JWTAuthenticationFilterTest {

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    private List<String> excludedPaths = Arrays.asList("/api/v1/login","api/v1/contrasena/**" , "/api/v1/usuarios/registro","/swagger-ui/**", "/v3/api-docs/**");


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExcludedPaths() throws ServletException, IOException {
        // Configuración de usuario de prueba
        UserDetails userDetails = new User("testuser@gmail.com", "password", Arrays.asList());

        // Configuración de solicitud simulada para una URL excluida
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/v1/login");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        // Configuración de comportamiento de mocks
        when(userDetailsService.loadUserByUsername("testuser@gmail.com")).thenReturn(userDetails);
        when(jwtUtils.extractUsername(anyString())).thenReturn("testuser@gmail.com");
        when(jwtUtils.isTokenValid(anyString(), eq(userDetails))).thenReturn(true);

        // Ejecuta el filtro
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verifica que el filtro no realice ninguna acción (que la cadena de filtros continúe)
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testNullAuthorizationHeader() throws ServletException, IOException {
        // Configuración de solicitud simulada con Authorization header nulo
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/v1/resource");  // Cambia la URL según tu necesidad
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        // Ejecuta el filtro
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verifica que el filtro continúa con el filtrado (la cadena de filtros se ejecuta)
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testNoBearerToken() throws ServletException, IOException {
        // Configuración de solicitud simulada con Authorization header sin "Bearer"
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/v1/resource");
        request.addHeader("Authorization", "Token xyz");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        // Ejecuta el filtro
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verifica que el filtro continúa con el filtrado (la cadena de filtros se ejecuta)
        verify(filterChain, times(1)).doFilter(request, response);
    }

    /*@Test
    public void testValidToken() {
        // Configuración de un token válido y un UserDetails correspondiente
        String token = generateValidToken();
        when(userDetails.getUsername()).thenReturn("testuser");

        // Verificar que el token sea válido para el UserDetails proporcionado
        boolean result = jwtUtils.isTokenValid(token, userDetails);
        assertTrue(result);
    }

    @Test
    public void testExpiredToken() {
        // Configuración de un token vencido y un UserDetails correspondiente
        String token = generateExpiredToken();
        when(userDetails.getUsername()).thenReturn("testuser");

        // Verificar que el token esté vencido para el UserDetails proporcionado
        boolean result = jwtUtils.isTokenValid(token, userDetails);
        assertFalse(result);
    }*/

}