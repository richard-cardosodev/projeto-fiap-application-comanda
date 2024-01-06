package br.fiap.projeto.comanda.usecase.port.interfaces;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.IntegracaoPedidoException;
import br.fiap.projeto.comanda.entity.Comanda;

import java.util.UUID;

public interface IAtualizarComandaUseCase {
    Comanda alterarStatus(UUID codigoPedido) throws EntradaInvalidaException, IntegracaoPedidoException;

}
