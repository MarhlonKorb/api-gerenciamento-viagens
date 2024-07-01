package com.api.gereciamento.viagens.viagem;

import java.math.BigDecimal;
import java.util.Set;

public record ViagemInput(Long id, String nome, String cidade, String estado, String pais, String descricao, BigDecimal custoTotal, Set<Long> idsMeiosTransporte, String hospedagem, Integer numeroPessoas, String status) {

}
