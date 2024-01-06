package br.fiap.projeto.comanda.adapter.gateway;

import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorStatusComandaRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaPorStatusComandaGatewayAdapter implements IBuscarPorStatusComandaRepositoryUseCase {

    private final SpringComandaRepository springComandaRepository;

    public BuscaPorStatusComandaGatewayAdapter(SpringComandaRepository springComandaRepository) {
        this.springComandaRepository = springComandaRepository;
    }

    @Override
    public List<Comanda> buscaComandaPorStatus(StatusComanda status) {
        return springComandaRepository.findByStatus(status).stream().map(t -> {
            try {
                return t.toComanda();
            } catch (EntradaInvalidaException e) {
                e.getMessage();
            }
            return null;
        })
                .collect(Collectors.toList());
    }

}
