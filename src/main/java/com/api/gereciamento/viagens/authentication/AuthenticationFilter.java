package com.api.gereciamento.viagens.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Interceptor de autenticação de usuário
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final AuthenticationService authenticationService;

    public AuthenticationFilter(TokenService tokenService, AuthenticationService authenticationService) {
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var email = tokenService.validateToken(token);
            UserDetails userDetails = authenticationService.findByEmail(email);
            // Insere todas as informações necessárias para o Spring Security validar o acesso do usuário
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // Salva no contexto da autenticação as informações de acesso do usuário autenticado
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        // Remove do token a palavra Bearer que vem por default, por espaço vazio para pegar apenas o valor do token
        return authHeader.replace("Bearer ", "");
    }
}
