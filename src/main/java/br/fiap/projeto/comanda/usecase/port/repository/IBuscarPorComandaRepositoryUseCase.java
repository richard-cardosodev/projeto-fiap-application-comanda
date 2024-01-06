package br.fiap.projeto.comanda.usecase.port.repository;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;

import java.util.Optional;
import java.util.UUID;

public interface IBuscarPorComandaRepositoryUseCase {

    Optional<Comanda> buscar(UUID codigoPedido) throws EntradaInvalidaException;

}
