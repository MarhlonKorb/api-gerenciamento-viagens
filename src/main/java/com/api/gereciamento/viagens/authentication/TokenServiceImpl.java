package com.api.gereciamento.viagens.authentication;

import com.api.gereciamento.viagens.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço para geração e validação de tokens JWT.
 */
@Service
public class TokenServiceImpl implements TokenService {

    // Emissor do token JWT
    public static final String JWT_ISSUER = "gerenciador-estacionamento";
    // Chave secreta para assinar o token, injetada a partir das configurações
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT para um usuário.
     *
     * @param authPrincipal Usuário para o qual o token será gerado.
     * @return String representando o token JWT.
     * @throws RuntimeException se ocorrer um erro durante a geração do token.
     */
    @Override
    public String generateToken(Usuario authPrincipal) {
        try {
            final var usuario = authPrincipal;
            // Algoritmo de assinatura do token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // Criação do token com informações como emissor, assunto, expiração, etc.
            String token = JWT.create()
                    .withIssuer(JWT_ISSUER).
                    withSubject(usuario.getUsername())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            // Lança uma exceção personalizada em caso de erro na geração do token
            throw new RuntimeException("Erro enquanto o token estava sendo gerado", exception);
        }
    }

    /**
     * Descriptografa e executa a validação do token JWT.
     *
     * @param token Token JWT a ser validado.
     * @return String representando o assunto (subject) do token se a validação for bem-sucedida, ou uma string vazia caso contrário.
     */
    @Override
    public String validateToken(String token) {
        try {
            // Algoritmo de assinatura do token
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verificação e decodificação do token JWT
            return JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            // Retorna uma string vazia se a validação falhar
            throw new JWTVerificationException("Token com formato inválido! " + exception.getMessage());
        }
    }

    /**
     * Retorna a quantidade de horas de expiração do token a ser gerado.
     *
     * @return Instant representando o momento de expiração do token.
     */
    private Instant getExpirationDate() {
        // Retorna a data e hora atual mais 2 horas, convertido para Instant
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
