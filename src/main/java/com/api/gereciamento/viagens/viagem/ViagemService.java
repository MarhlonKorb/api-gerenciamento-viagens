package com.api.gereciamento.viagens.viagem;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface ViagemService {
    ViagemOutput create(ViagemInput viagemInput);
    PagedModel<EntityModel<ViagemOutput>> getAllPageable();
    void updateStatusViagem(Long idViagem);
}
