package br.fiap.projeto.comanda.external.api.exception;

public class ComandaResponseException {
    private Integer codigo;
    private String mensagem;
    private String causa;

    public ComandaResponseException(Integer codigo, String mensagem, Throwable causa) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.causa = causa != null ? causa.getMessage() : null;
    }

    public ComandaResponseException(Exception e) {
        this.codigo = 500;
        this.mensagem = e.getMessage();
        this.causa = e.getCause() != null ? e.getCause().getMessage() : null;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCausa() {
        return causa;
    }
}
