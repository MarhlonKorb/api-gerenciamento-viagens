package com.api.gereciamento.viagens.viagem;

import com.api.gereciamento.viagens.core.abstractentities.entidadeauditada.EntidadeAuditada;
import com.api.gereciamento.viagens.core.enums.Status;
import com.api.gereciamento.viagens.viagemmeiostransporte.ViagemMeiosTransporte;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa a classe de modelo da tabela viagem
 */
@Entity
@Table
public class Viagem  extends EntidadeAuditada {
    private String nome;
    private String cidade;
    private String estado;
    private String pais;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_fim")
    private LocalDate dataFim;
    @Column(name = "custo_total")
    private BigDecimal custoTotal;
    @JsonManagedReference
    @OneToMany(mappedBy = "viagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ViagemMeiosTransporte> viagemMeiosTransporte = new HashSet<>();
    private String hospedagem;
    @Column(name = "numero_pessoas")
    private Integer numeroPessoas;
    @Column
    private BigDecimal avaliacao;

    public Viagem() {
    }

    public Viagem(String nome, String cidade, String estado, String pais, String descricao, BigDecimal custoTotal, String hospedagem, Integer numeroPessoas, Status status) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.descricao = descricao;
        this.custoTotal = custoTotal;
        this.hospedagem = hospedagem;
        this.numeroPessoas = numeroPessoas;
        super.setStatus(status);
    }

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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(BigDecimal custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Set<ViagemMeiosTransporte> getViagemMeiosTransporte() {
        return viagemMeiosTransporte;
    }

    public void setViagemMeiosTransporte(Set<ViagemMeiosTransporte> viagemMeiosTransporte) {
        this.viagemMeiosTransporte = viagemMeiosTransporte;
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

    public BigDecimal getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(BigDecimal avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void addMeioTransporte(ViagemMeiosTransporte meioTransporte) {
        viagemMeiosTransporte.add(meioTransporte);
        meioTransporte.setViagem(this);
    }
}
