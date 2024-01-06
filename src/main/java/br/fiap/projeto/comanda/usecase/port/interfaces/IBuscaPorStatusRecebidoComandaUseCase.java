package br.fiap.projeto.comanda.usecase.port.interfaces;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;

import java.util.List;

public interface IBuscaPorStatusRecebidoComandaUseCase {
    List<Comanda> buscaComandaPorStatusRecebido() throws EntradaInvalidaException, Exception;
}
