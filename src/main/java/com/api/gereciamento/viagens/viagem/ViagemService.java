package com.api.gereciamento.viagens.viagem;

import org.springframework.data.repository.query.Param;

public interface ViagemService {
    Viagem create(ViagemInput viagemInput);
    void updateStatusViagem(@Param("idViagem") Long idViagem);
}
