package br.fiap.projeto.comanda.usecase.port.interfaces;

import java.util.UUID;

import br.fiap.projeto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;

public interface ICriarComandaUseCase {

    Comanda criarComanda(UUID codigoPedido)
            throws EntradaInvalidaException, ComandaDuplicadaException;

}
