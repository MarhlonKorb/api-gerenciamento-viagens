package com.api.gereciamento.viagens.authentication;

import com.api.gereciamento.viagens.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Service que implementa interface AuthenticationService
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    public AuthenticationServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
