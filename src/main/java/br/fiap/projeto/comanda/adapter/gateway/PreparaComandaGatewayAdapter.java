package br.fiap.projeto.comanda.adapter.gateway;

import br.fiap.projeto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;

public class PreparaComandaGatewayAdapter implements IAtualizarComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public PreparaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda atualizar(Comanda comanda) throws EntradaInvalidaException {
        return springComandaRepository
                .save(new ComandaEntity(comanda))
                .toComanda();
    }
}
