package br.fiap.projeto.comanda.adapter.controller;

import br.fiap.projeto.comanda.adapter.controller.port.IBuscaPorStatusPreparacaoComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.comanda.usecase.port.interfaces.IBuscaPorStatusPreparacaoComandaUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class BuscaPorStatusPreparacaoComandaControllerAdapter implements IBuscaPorStatusPreparacaoComandaControllerAdapter {

    private final IBuscaPorStatusPreparacaoComandaUseCase buscarPorStatusPreparacaoComandaUseCase;

    public BuscaPorStatusPreparacaoComandaControllerAdapter(
            IBuscaPorStatusPreparacaoComandaUseCase buscarPorStatusPreparacaoComandaUseCase) {
        this.buscarPorStatusPreparacaoComandaUseCase = buscarPorStatusPreparacaoComandaUseCase;
    }

    @Override
    public List<ComandaDTO> buscaPorStatusPreparacao() throws Exception {
        return buscarPorStatusPreparacaoComandaUseCase.buscaComandaPorStatusPreparacao().stream()
                .map(ComandaDTO::newInstanceFromComanda).collect(Collectors.toList());
    }

}
