package com.api.gereciamento.viagens.core.abstractentities.entidadeauditada;

import com.api.gereciamento.viagens.core.abstractentities.entidadecomid.EntidadeComId;
import com.api.gereciamento.viagens.core.enums.Status;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

/**
 * Classe abstrata que representa uma entidade auditada
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntidadeAuditada extends EntidadeComId {

    @Column
    @CreatedBy
    protected Long criadoPor;
    @Column
    @LastModifiedBy
    private Long alteradoPor;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataCriacao;

    @Column
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataAlteracao;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Long criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Long getAlteradoPor() {
        return alteradoPor;
    }

    public void setAlteradoPor(Long alteradoPor) {
        this.alteradoPor = alteradoPor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
