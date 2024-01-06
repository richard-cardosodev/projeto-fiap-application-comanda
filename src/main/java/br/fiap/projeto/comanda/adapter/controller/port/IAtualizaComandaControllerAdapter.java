package br.fiap.projeto.comanda.adapter.controller.port;

import br.fiap.projeto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.IntegracaoPedidoException;

import java.util.UUID;

public interface IAtualizaComandaControllerAdapter {

    ComandaDTO atualizaStatusComanda(UUID codigoPedido) throws IntegracaoPedidoException, EntradaInvalidaException;
}
