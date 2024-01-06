package br.fiap.projeto.comanda.adapter.gateway;

import br.fiap.projeto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;

public class FinalizaComandaGatewayAdapter implements IAtualizarComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public FinalizaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda atualizar(Comanda comanda) throws EntradaInvalidaException {
        return springComandaRepository.save(new ComandaEntity(comanda)).toComanda();
    }

}
