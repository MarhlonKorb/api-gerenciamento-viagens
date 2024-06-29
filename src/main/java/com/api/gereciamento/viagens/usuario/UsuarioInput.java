package com.api.gereciamento.viagens.usuario;

import com.api.gereciamento.viagens.core.enums.Role;
import com.api.gereciamento.viagens.core.enums.Status;

public record UsuarioInput(String email, String password, String nome, Role role, Status status) {
}
