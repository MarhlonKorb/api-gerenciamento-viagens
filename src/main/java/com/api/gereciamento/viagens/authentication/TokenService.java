package com.api.gereciamento.viagens.authentication;

import com.api.gereciamento.viagens.usuario.Usuario;
import com.auth0.jwt.exceptions.JWTVerificationException;

public interface TokenService {

    String validateToken(String token) throws JWTVerificationException;

    String generateToken(Usuario authPrincipal);
}
