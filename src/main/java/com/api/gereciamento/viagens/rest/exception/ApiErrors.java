package com.api.gereciamento.viagens.rest.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiErrors {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiErrors(HttpStatus status, String message, Throwable ex) {
        super();
        this.status = status;
        this.message = message;
        this.errors = Collections.singletonList(ex.getMessage());
    }

    public ApiErrors(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ApiErrors(String mensagemErro) {
        this.message = mensagemErro;
    }

    public ApiErrors(List<String> messages) {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
