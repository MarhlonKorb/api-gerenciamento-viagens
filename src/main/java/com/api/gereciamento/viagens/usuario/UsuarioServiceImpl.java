package com.api.gereciamento.viagens.usuario;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Implementação da interface UsuarioService.
 * Este serviço gerencia operações relacionadas aos usuários.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Construtor da classe UsuarioServiceImpl.
     *
     * @param usuarioRepository O repositório de usuários.
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Busca um usuário pelo email.
     *
     * @param email O email do usuário a ser buscado.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    @Override
    public Optional<Usuario> findByEmail(String email) {
        return Optional.ofNullable((Usuario) usuarioRepository.findByEmail(email));
    }

    /**
     * Cria um novo usuário.
     *
     * @param usuarioInput Os dados de entrada para criar o usuário.
     * @return O usuário criado.
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Usuario create(UsuarioInput usuarioInput) {
        Usuario usuario = new Usuario(
                usuarioInput.nome(),
                usuarioInput.email(),
                getPasswordCriptografado(usuarioInput.password()),
                usuarioInput.role(),
                usuarioInput.status()
        );
        return usuarioRepository.save(usuario);
    }

    /**
     * Criptografa a senha do usuário.
     *
     * @param password A senha em texto plano.
     * @return A senha criptografada.
     */
    private String getPasswordCriptografado(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
