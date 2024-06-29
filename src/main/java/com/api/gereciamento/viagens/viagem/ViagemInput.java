package com.api.gereciamento.viagens.viagem;

import java.math.BigDecimal;
import java.util.List;

public record ViagemInput(String nome, String cidade, String estado, String pais, String descricao, BigDecimal custoTotal, List<Integer> meiosTransporte, String hospedagem, Integer numeroPessoas) {
}
