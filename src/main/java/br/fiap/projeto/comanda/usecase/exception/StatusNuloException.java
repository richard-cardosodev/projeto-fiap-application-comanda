package br.fiap.projeto.comanda.usecase.exception;

public class StatusNuloException extends NullPointerException {
    public StatusNuloException(String mensagem) {
        super(mensagem);
    }
}
