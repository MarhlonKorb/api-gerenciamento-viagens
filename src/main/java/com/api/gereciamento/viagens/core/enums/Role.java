package com.api.gereciamento.viagens.core.enums;

public enum Role {
    // AE = Admin Sistema
    // UC = Usuario Comum
    AS("Admin Sistema"), UC("Usuário comum");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}