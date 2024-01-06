package br.fiap.projeto.comanda.adapter.gateway;

import br.fiap.projeto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;

import java.util.Optional;
import java.util.UUID;

public class BuscaPorComandaGatewayAdapter implements IBuscarPorComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public BuscaPorComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Optional<Comanda> buscar(UUID codigoPedido) {
        Optional<ComandaEntity> comandaEntity = springComandaRepository.findById(codigoPedido);
        return comandaEntity.map(t -> {
            try {
                return t.toComanda();
            } catch (EntradaInvalidaException e) {
                e.getMessage();
            }
            return null;
        });
    }

}
