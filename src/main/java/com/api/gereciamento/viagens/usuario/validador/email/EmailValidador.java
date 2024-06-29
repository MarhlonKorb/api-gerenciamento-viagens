package com.api.gereciamento.viagens.usuario.validador.email;

import com.api.gereciamento.viagens.usuario.UsuarioRepository;
import com.api.gereciamento.viagens.usuario.UsuarioService;
import com.api.gereciamento.viagens.usuario.validador.email.exception.FormatoEmailInvalidoException;
import com.api.gereciamento.viagens.utils.MessageUtil;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que executa a validação de email
 */
@Component
public class EmailValidador implements IEmailValidador {

    /* Regex para comparar a formatação do email recebido */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL_OBRIGATORIO_KEY = "campo.email.obrigatorio";
    private static final String EMAIL_INVALIDO_KEY = "campo.email.invalido";
    private final MessageUtil messageUtil;
    private final UsuarioService usuarioService;

    public EmailValidador(MessageUtil messageUtil, UsuarioService usuarioService) {
        this.messageUtil = messageUtil;
        this.usuarioService = usuarioService;
    }

    @Override
    public void validar(String email) {
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        try {
            if (email == null || email.isEmpty()) {
                throw new FormatoEmailInvalidoException(messageUtil.getMessage(EMAIL_OBRIGATORIO_KEY));
            }
            final Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new FormatoEmailInvalidoException(messageUtil.getMessage(EMAIL_INVALIDO_KEY));
            }
        } catch (RuntimeException e) {
            throw new FormatoEmailInvalidoException(e.getMessage());
        }
    }

    @Override
    public boolean isEmailCadastrado(String email) {
        final var usuarioEncontrado = usuarioService.findByEmail(email);
        return usuarioEncontrado.isPresent();
    }

}
