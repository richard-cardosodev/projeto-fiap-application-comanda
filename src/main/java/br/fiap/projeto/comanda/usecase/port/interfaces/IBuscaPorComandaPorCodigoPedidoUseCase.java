package br.fiap.projeto.comanda.usecase.port.interfaces;

import java.util.UUID;

import br.fiap.projeto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.StatusNuloException;
import br.fiap.projeto.comanda.entity.Comanda;

public interface IBuscaPorComandaPorCodigoPedidoUseCase {
    Comanda buscaComandaPorCodigoPedido(UUID codigoPedido)
            throws EntradaInvalidaException, ComandaNaoEncontradaException, StatusNuloException;
}
