package com.api.gereciamento.viagens.viagem;

import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ViagemService {
    ViagemOutput create(ViagemInput viagemInput);
    List<ViagemOutput> getAll();
    void updateStatusViagem(Long idViagem);
}
