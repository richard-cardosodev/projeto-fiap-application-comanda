package br.fiap.projeto.comanda.adapter.controller;

import br.fiap.projeto.comanda.adapter.controller.port.IBuscaPorComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.controller.rest.dto.BuscaPorComandaDTO;
import br.fiap.projeto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.StatusNuloException;
import br.fiap.projeto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;

public class BuscaPorComandaControllerAdapter implements IBuscaPorComandaControllerAdapter {

    private final IBuscaPorComandaUseCase buscaPorComandaUseCase;

    public BuscaPorComandaControllerAdapter(IBuscaPorComandaUseCase buscaPorComandaUseCase) {
        this.buscaPorComandaUseCase = buscaPorComandaUseCase;
    }

    @Override
    public ComandaDTO buscaPorComanda(BuscaPorComandaDTO buscaPorComandaDTO)
            throws EntradaInvalidaException, ComandaNaoEncontradaException, StatusNuloException {
        return ComandaDTO.newInstanceFromComanda(
                buscaPorComandaUseCase.buscaComandaPorStatus(buscaPorComandaDTO.getCodigoPedido()));
    }

}
