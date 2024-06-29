package com.api.gereciamento.viagens.usuario.validador.usuario;

import com.api.gereciamento.viagens.usuario.Usuario;
import com.api.gereciamento.viagens.usuario.exception.UsuarioException;

/**
 * Interface que define contrato para as validações de usuário
 */
public interface IUsuarioValidador {
    public void validaUsuarioIsCadastrado(String email);
    public void validaUsuarioIsNotCadastrado(String email);
    public void contemPassword(String password) throws UsuarioException;
    public void validar(Usuario usuario);
}
