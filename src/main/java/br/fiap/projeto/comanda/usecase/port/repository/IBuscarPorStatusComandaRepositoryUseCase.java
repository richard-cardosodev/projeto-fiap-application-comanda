package br.fiap.projeto.comanda.usecase.port.repository;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;

import java.util.List;

public interface IBuscarPorStatusComandaRepositoryUseCase {
    List<Comanda> buscaComandaPorStatus(StatusComanda status) throws EntradaInvalidaException, Exception;
}
