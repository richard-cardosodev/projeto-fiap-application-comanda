package br.fiap.projeto.comanda.usecase.exception;

public class ComandaDuplicadaException extends Exception {
    public ComandaDuplicadaException(String mensagem) {
        super(mensagem);
    }
}
