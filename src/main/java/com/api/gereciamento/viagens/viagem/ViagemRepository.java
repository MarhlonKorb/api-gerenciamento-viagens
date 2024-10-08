package com.api.gereciamento.viagens.viagem;

import com.api.gereciamento.viagens.core.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    @Modifying
    @Query("UPDATE Viagem v SET v.status = CASE WHEN v.status = 'A' THEN 'I' ELSE 'A' END WHERE v.id = :idViagem")
    void updateStatusViagem(@Param("idViagem") Long idViagem);

    Page<Viagem> findAllByStatus(Status status, Pageable pageable);
}
