package br.fiap.projeto.comanda.adapter.controller.port;

import br.fiap.projeto.comanda.adapter.controller.rest.dto.ComandaDTO;
import br.fiap.projeto.comanda.adapter.controller.rest.dto.CriarComandaDTO;
import br.fiap.projeto.comanda.usecase.exception.ComandaDuplicadaException;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;

public interface ICriarComandaControllerAdapter {

    ComandaDTO criaComanda(CriarComandaDTO criarComandaDTO) throws EntradaInvalidaException, ComandaDuplicadaException;
}
