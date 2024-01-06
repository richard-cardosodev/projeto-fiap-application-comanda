package br.fiap.projeto.comanda;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;

public class ComandaValidacoesTest {

    @Test
    public void codigoComandaNulo() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Comanda(null, UUID.randomUUID(), StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }

    @Test
    public void codigoPedidoNulo() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Comanda(UUID.randomUUID(), null, StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }

    @Test
    public void statusComandaNulo() {

        assertThrows(
                NullPointerException.class,
                () -> new Comanda(UUID.randomUUID(), UUID.randomUUID(), null),
                "Mensagem de erro");
    }

    @Test
    public void comandaOk() {

        assertDoesNotThrow(
                () -> new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }
}
