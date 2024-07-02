package com.api.gereciamento.viagens.viagemmeiostransporte;

import com.api.gereciamento.viagens.viagem.Viagem;
import com.api.gereciamento.viagens.viagem.enums.MeioTransporte;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "viagem_meios_transporte")
@IdClass(ViagemMeiosTransporteId.class)
public class ViagemMeiosTransporte {
    @Id
    @ManyToOne
    @JoinColumn(name = "viagem_id", nullable = false)
    @JsonBackReference
    private Viagem viagem;
    @Id
    @Enumerated
    @Column(name = "id_meio_transporte")
    private MeioTransporte meioTransporte;

    public ViagemMeiosTransporte() {
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public MeioTransporte getMeioTransporte() {
        return meioTransporte;
    }

    public void setMeioTransporte(MeioTransporte meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    public ViagemMeiosTransporte(MeioTransporte meioTransporte) {
        this.meioTransporte = meioTransporte;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViagemMeiosTransporte that = (ViagemMeiosTransporte) o;
        return viagem.equals(that.viagem) && meioTransporte == that.meioTransporte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(viagem, meioTransporte);
    }
}


