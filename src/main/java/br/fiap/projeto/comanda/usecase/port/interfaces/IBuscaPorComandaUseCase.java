package br.fiap.projeto.comanda.usecase.port.interfaces;

import br.fiap.projeto.comanda.usecase.exception.ComandaNaoEncontradaException;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.StatusNuloException;
import br.fiap.projeto.comanda.entity.Comanda;

import java.util.UUID;

public interface IBuscaPorComandaUseCase {
    Comanda buscaComandaPorStatus(UUID codigoPedido)
            throws EntradaInvalidaException, ComandaNaoEncontradaException, StatusNuloException;
}
