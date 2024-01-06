package br.fiap.projeto.comanda.adapter.controller;

import br.fiap.projeto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.IntegracaoPedidoException;
import br.fiap.projeto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;

import java.util.UUID;

public class FinalizaComandaControllerAdapter implements IAtualizaComandaControllerAdapter {

    private final IAtualizarComandaUseCase finalizarComandaUseCase;

    public FinalizaComandaControllerAdapter(IAtualizarComandaUseCase finalizarComandaUseCase) {
        this.finalizarComandaUseCase = finalizarComandaUseCase;
    }

    @Override
    public ComandaDTO atualizaStatusComanda(UUID codigoPedido)
            throws EntradaInvalidaException, IntegracaoPedidoException {
        return ComandaDTO.newInstanceFromComanda(
                finalizarComandaUseCase.alterarStatus(codigoPedido));
    }

}
