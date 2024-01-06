package br.fiap.projeto.comanda.usecase;

import br.fiap.projeto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.StatusNuloException;
import br.fiap.projeto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;

import java.util.Optional;
import java.util.UUID;

public class BuscaComandaUseCase implements IBuscaPorComandaUseCase {

    private final IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase;

    public BuscaComandaUseCase(IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase) {
        this.buscarComandaRepositoryUseCase = buscarComandaRepositoryUseCase;
    }

    @Override
    public Comanda buscaComandaPorStatus(UUID codigoPedido)
            throws EntradaInvalidaException, ComandaNaoEncontradaException, StatusNuloException {
        Optional<Comanda> comanda = buscarComandaRepositoryUseCase.buscar(codigoPedido);
        comanda.orElseThrow(() -> new ComandaNaoEncontradaException("Comanda não encontrada!"));
        comanda.get().validarDados();
        return comanda.get();
    }

}
