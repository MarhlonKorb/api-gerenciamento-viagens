package com.api.gereciamento.viagens.viagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}
