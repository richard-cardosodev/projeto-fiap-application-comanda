package br.fiap.projeto.comanda.usecase.exception;

public class ComandaNaoEncontradaException extends Exception {
    public ComandaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
