package com.api.gereciamento.viagens.viagem;

import com.api.gereciamento.viagens.core.enums.Status;
import com.api.gereciamento.viagens.viagemmeiostransporte.ViagemMeiosTransporte;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ViagemOutput {
    private String nome;
    private String cidade;
    private String estado;
    private String pais;
    private String descricao;
    private BigDecimal custoTotal;
    private Set<Integer> viagemMeiosTransporte = new HashSet<>();
    private String hospedagem;
    private Integer numeroPessoas;

    private Status status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(BigDecimal custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Set<Integer> getViagemMeiosTransporte() {
        return viagemMeiosTransporte;
    }

    public void setViagemMeiosTransporte(Set<ViagemMeiosTransporte> viagemMeiosTransporte) {
        this.viagemMeiosTransporte = viagemMeiosTransporte.stream().map(v -> v.getMeioTransporte().ordinal()).collect(Collectors.toSet());
    }

    public String getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(String hospedagem) {
        this.hospedagem = hospedagem;
    }

    public Integer getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(Integer numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

