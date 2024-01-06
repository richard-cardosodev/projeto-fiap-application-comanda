package br.fiap.projeto.comanda.external.repository.postgres;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fiap.projeto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.comanda.external.repository.entity.ComandaEntity;

@Repository
public interface SpringComandaRepository extends JpaRepository<ComandaEntity, UUID> {
    List<ComandaEntity> findByStatus(StatusComanda statusComanda);

    Optional<ComandaEntity> findByCodigoPedido(UUID codigoPedido);

}
