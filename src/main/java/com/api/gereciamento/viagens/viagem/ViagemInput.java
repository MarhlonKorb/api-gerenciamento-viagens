package com.api.gereciamento.viagens.viagem;

import com.api.gereciamento.viagens.core.enums.Status;
import java.math.BigDecimal;
import java.util.Set;

public record ViagemInput(Long id, String nome, String cidade, String estado, String pais, String descricao,
                          BigDecimal custoTotal, Set<Long> idsMeiosTransporte, String hospedagem, Integer numeroPessoas,
                          String status) {

    /**
     * Converte um viagemInput para um objeto Viagem
     * @return
     */
    public Viagem toEntity() {
        return new Viagem(nome(), cidade(), estado(), pais(), descricao(), custoTotal(), hospedagem(), numeroPessoas(), Status.valueOf(status()));
    }
}
