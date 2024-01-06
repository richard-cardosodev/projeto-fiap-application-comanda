package br.fiap.projeto.comanda.adapter.controller;

import br.fiap.projeto.comanda.adapter.controller.port.IBuscaPorStatusFinalizadoComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.comanda.usecase.port.interfaces.IBuscaPorStatusFinalizadoComandaUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaPorStatusFinalizadoComandaControllerAdapter implements IBuscaPorStatusFinalizadoComandaControllerAdapter {

    private final IBuscaPorStatusFinalizadoComandaUseCase buscarPorStatusFinalizadoComandaUseCase;

    public BuscaPorStatusFinalizadoComandaControllerAdapter(
            IBuscaPorStatusFinalizadoComandaUseCase buscarPorStatusFinalizadoComandaUseCase) {
        this.buscarPorStatusFinalizadoComandaUseCase = buscarPorStatusFinalizadoComandaUseCase;
    }

    @Override
    public List<ComandaDTO> buscaPorStatusFinalizado() throws Exception {
        return buscarPorStatusFinalizadoComandaUseCase.buscaComandaPorStatusFinalizado().stream()
                .map(ComandaDTO::newInstanceFromComanda).collect(Collectors.toList());
    }

}
