package com.api.gereciamento.viagens.authentication;

/**
 * Record de input de autenticação
 * @param email
 * @param password
 */
public record AuthenticationInput(String email, String password) {
}
