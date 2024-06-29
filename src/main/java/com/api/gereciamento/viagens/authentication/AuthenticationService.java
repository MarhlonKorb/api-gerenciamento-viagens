package com.api.gereciamento.viagens.authentication;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface de autenticação
 */
public interface AuthenticationService {

    UserDetails findByEmail(String email);
}
