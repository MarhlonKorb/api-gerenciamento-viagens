package com.api.gereciamento.viagens.viagemmeiostransporte;

import com.api.gereciamento.viagens.viagem.enums.MeioTransporte;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa a chave composta para a entidade ViagemMeiosTransporte.
 * Esta classe é utilizada para identificar de forma única uma associação entre
 * uma viagem e um meio de transporte.
 */
public class ViagemMeiosTransporteId implements Serializable {

    private Long viagem;
    private MeioTransporte idMeioTransporte;

    /**
     * Construtor padrão.
     */
    public ViagemMeiosTransporteId() {}

    /**
     * Construtor com parâmetros.
     * @param viagem ID da viagem.
     * @param idMeioTransporte ID do meio de transporte.
     */
    public ViagemMeiosTransporteId(Long viagem, MeioTransporte idMeioTransporte) {
        this.viagem = viagem;
        this.idMeioTransporte = idMeioTransporte;
    }

    public ViagemMeiosTransporteId(Long idMeioTransporte) {
    }

    /**
     * Obtém o ID da viagem.
     * @return ID da viagem.
     */
    public Long getViagem() {
        return viagem;
    }

    /**
     * Define o ID da viagem.
     * @param viagem ID da viagem.
     */
    public void setViagem(Long viagem) {
        this.viagem = viagem;
    }

    /**
     * Obtém o ID do meio de transporte.
     * @return ID do meio de transporte.
     */
    public MeioTransporte getIdMeioTransporte() {
        return idMeioTransporte;
    }

    /**
     * Define o ID do meio de transporte.
     * @param idMeioTransporte ID do meio de transporte.
     */
    public void setIdMeioTransporte(MeioTransporte idMeioTransporte) {
        this.idMeioTransporte = idMeioTransporte;
    }

    /**
     * Sobrescreve o método equals para comparar objetos ViagemMeiosTransporteId.
     * @param o Objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViagemMeiosTransporteId that = (ViagemMeiosTransporteId) o;
        return Objects.equals(viagem, that.viagem) && Objects.equals(idMeioTransporte, that.idMeioTransporte);
    }

    /**
     * Sobrescreve o método hashCode para gerar o código hash do objeto ViagemMeiosTransporteId.
     * @return Código hash do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(viagem, idMeioTransporte);
    }
}
