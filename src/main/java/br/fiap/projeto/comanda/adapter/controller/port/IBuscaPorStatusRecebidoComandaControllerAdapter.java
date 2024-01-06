package br.fiap.projeto.comanda.adapter.controller.port;

import br.fiap.projeto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;

import java.util.List;

public interface IBuscaPorStatusRecebidoComandaControllerAdapter {
    List<ComandaDTO> buscaPorStatusRecebido() throws EntradaInvalidaException, Exception;
}
