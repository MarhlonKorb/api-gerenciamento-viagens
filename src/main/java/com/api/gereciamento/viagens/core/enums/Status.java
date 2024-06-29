package com.api.gereciamento.viagens.core.enums;

public enum Status {
    A("Ativo"),
    I("Inativo");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
