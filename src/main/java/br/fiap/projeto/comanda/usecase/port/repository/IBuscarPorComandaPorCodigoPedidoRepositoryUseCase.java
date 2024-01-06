package br.fiap.projeto.comanda.usecase.port.repository;

import java.util.Optional;
import java.util.UUID;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;

public interface IBuscarPorComandaPorCodigoPedidoRepositoryUseCase {

    Optional<Comanda> buscar(UUID codigoPedido) throws EntradaInvalidaException;

}
