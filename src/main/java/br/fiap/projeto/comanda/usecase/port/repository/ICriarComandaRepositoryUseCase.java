package br.fiap.projeto.comanda.usecase.port.repository;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;

public interface ICriarComandaRepositoryUseCase {

    Comanda criar(Comanda comanda) throws EntradaInvalidaException;

}
