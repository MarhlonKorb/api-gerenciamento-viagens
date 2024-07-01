package com.api.gereciamento.viagens.authentication;

import com.api.gereciamento.viagens.usuario.Usuario;

public interface TokenService {

    String validateToken(String token);

    String generateToken(Usuario authPrincipal);
}
