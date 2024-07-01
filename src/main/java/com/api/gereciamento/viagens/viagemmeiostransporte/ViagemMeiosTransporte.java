package com.api.gereciamento.viagens.viagemmeiostransporte;

import com.api.gereciamento.viagens.viagem.Viagem;
import com.api.gereciamento.viagens.viagem.enums.MeioTransporte;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "viagem_meios_transporte")
@IdClass(ViagemMeiosTransporteId.class)
public class ViagemMeiosTransporte {
    @Id
    @ManyToOne
    @JoinColumn(name = "viagem_id", nullable = false)
    private Viagem viagem;
    @Id
    @Enumerated
    private MeioTransporte idMeioTransporte;

    public ViagemMeiosTransporte() {
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public MeioTransporte getIdMeioTransporte() {
        return idMeioTransporte;
    }

    public void setIdMeioTransporte(MeioTransporte idMeioTransporte) {
        this.idMeioTransporte = idMeioTransporte;
    }

    public ViagemMeiosTransporte(MeioTransporte idMeioTransporte) {
        this.idMeioTransporte = idMeioTransporte;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViagemMeiosTransporte that = (ViagemMeiosTransporte) o;
        return viagem.equals(that.viagem) && idMeioTransporte == that.idMeioTransporte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(viagem, idMeioTransporte);
    }
}


