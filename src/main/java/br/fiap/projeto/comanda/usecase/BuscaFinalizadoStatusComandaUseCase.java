package br.fiap.projeto.comanda.usecase;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.interfaces.IBuscaPorStatusFinalizadoComandaUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorStatusComandaRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaFinalizadoStatusComandaUseCase implements IBuscaPorStatusFinalizadoComandaUseCase {

    private final IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase;

    public BuscaFinalizadoStatusComandaUseCase(
            IBuscarPorStatusComandaRepositoryUseCase buscarPorStatusComandaRepositoryUseCase) {
        this.buscarPorStatusComandaRepositoryUseCase = buscarPorStatusComandaRepositoryUseCase;
    }

    @Override
    public List<Comanda> buscaComandaPorStatusFinalizado() throws EntradaInvalidaException, Exception {
        return buscarPorStatusComandaRepositoryUseCase
                .buscaComandaPorStatus(StatusComanda.FINALIZADO)
                .stream()
                .collect(Collectors.toList());

    }

}
