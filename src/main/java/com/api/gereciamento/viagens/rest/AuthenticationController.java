package com.api.gereciamento.viagens.rest;

import com.api.gereciamento.viagens.authentication.AuthenticationInput;
import com.api.gereciamento.viagens.authentication.TokenOutput;
import com.api.gereciamento.viagens.authentication.TokenServiceImpl;
import com.api.gereciamento.viagens.rest.exception.ApiErrors;
import com.api.gereciamento.viagens.usuario.Usuario;
import com.api.gereciamento.viagens.usuario.UsuarioInput;
import com.api.gereciamento.viagens.usuario.UsuarioService;
import com.api.gereciamento.viagens.usuario.exception.UsuarioException;
import com.api.gereciamento.viagens.usuario.validador.usuario.IUsuarioValidador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pela autenticação do usuário.
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenServiceImpl tokenServiceImpl;
    private final IUsuarioValidador iUsuarioValidador;
    private final UsuarioService usuarioService;

    /**
     * Construtor da classe, injetando as dependências necessárias.
     *
     * @param authenticationManager Gerenciador de autenticação do Spring Security.
     * @param tokenServiceImpl          Serviço para geração e validação de tokens JWT.
     * @param iUsuarioValidador     Validador de usuário para garantir consistência nos dados.
     */
    public AuthenticationController(AuthenticationManager authenticationManager, TokenServiceImpl tokenServiceImpl, IUsuarioValidador iUsuarioValidador, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenServiceImpl = tokenServiceImpl;
        this.iUsuarioValidador = iUsuarioValidador;
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para autenticar um usuário e gerar um token JWT.
     *
     * @param authenticationInput Dados de autenticação fornecidos pelo usuário (email e senha).
     * @return Resposta contendo o token JWT se a autenticação for bem-sucedida.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationInput authenticationInput) {
        try {
            // Valida se o usuário existe antes da autenticação
            iUsuarioValidador.validaUsuarioIsNotCadastrado(authenticationInput.email());
            // Autentica o usuário
            var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationInput.email(), authenticationInput.password());
            var auth = authenticationManager.authenticate(usernamePassword);
            // Gera o token JWT para o usuário autenticado
            var token = tokenServiceImpl.generateToken((Usuario) auth.getPrincipal());
            return ResponseEntity.accepted().body(new TokenOutput(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiErrors(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }

    /**
     * Endpoint para registrar um novo usuário e gerar um token JWT para o mesmo.
     *
     * @param usuarioInput Dados do novo usuário a ser registrado.
     * @return Resposta contendo o token JWT gerado para o novo usuário.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioInput usuarioInput) {
        try {
            iUsuarioValidador.validaUsuarioIsCadastrado(usuarioInput.email());
            Usuario usuarioCriado = usuarioService.create(usuarioInput);
            // Gera um token JWT para o novo usuário registrado
            String token = tokenServiceImpl.generateToken(usuarioCriado);
            return ResponseEntity.ok().body(new TokenOutput(token));
        } catch (UsuarioException ex) {
            return ResponseEntity.badRequest().body(new ApiErrors(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }
}
