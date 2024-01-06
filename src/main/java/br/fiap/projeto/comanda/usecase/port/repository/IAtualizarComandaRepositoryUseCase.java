package br.fiap.projeto.comanda.usecase.port.repository;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;

public interface IAtualizarComandaRepositoryUseCase {

    Comanda atualizar(Comanda comanda) throws EntradaInvalidaException;

}
