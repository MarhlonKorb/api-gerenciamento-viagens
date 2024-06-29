package com.api.gereciamento.viagens.viagem;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ViagemServiceImpl implements ViagemService {
    private final ViagemRepository viagemRepository;
    public ViagemServiceImpl(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Viagem create(ViagemInput viagemInput) {
        final var viagem = new Viagem(viagemInput.nome(), viagemInput.cidade(), viagemInput.estado(), viagemInput.pais(), viagemInput.descricao(), viagemInput.custoTotal(), viagemInput.hospedagem(), viagemInput.numeroPessoas());
        return viagemRepository.save(viagem);
    }

    @Override
    public void updateStatusViagem(Long idViagem) {
        viagemRepository.updateStatusViagem(idViagem);
    }
}
