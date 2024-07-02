package com.api.gereciamento.viagens.viagem;

import org.springframework.data.domain.Page;

public interface ViagemService {
    ViagemOutput create(ViagemInput viagemInput);
    Page<ViagemOutput> getAllPageable();
    void updateStatusViagem(Long idViagem);
}
