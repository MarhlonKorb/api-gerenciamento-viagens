package com.api.gereciamento.viagens.usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> findByEmail(String email);
    Usuario create(UsuarioInput usuarioInput);
}
