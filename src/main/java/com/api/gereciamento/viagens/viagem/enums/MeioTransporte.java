package com.api.gereciamento.viagens.viagem.enums;

/**
 * Enum dos meios de transporte
 */
public enum MeioTransporte {
    AVIAO("Avião"),
    CARRO("Carro"),
    TREM("Trem"),
    ONIBUS("Ônibus"),
    NAVIO("Navio");

    private final String descricao;

    MeioTransporte(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

