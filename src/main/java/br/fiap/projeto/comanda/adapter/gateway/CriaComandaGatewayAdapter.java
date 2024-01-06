package br.fiap.projeto.comanda.adapter.gateway;

import br.fiap.projeto.comanda.external.repository.entity.ComandaEntity;
import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.ICriarComandaRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;

public class CriaComandaGatewayAdapter implements ICriarComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public CriaComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public Comanda criar(Comanda comanda) throws EntradaInvalidaException {
        return springComandaRepository.save(new ComandaEntity(comanda)).toComanda();
    }

}
