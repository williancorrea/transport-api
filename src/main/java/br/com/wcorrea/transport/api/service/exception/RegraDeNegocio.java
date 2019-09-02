package br.com.wcorrea.transport.api.service.exception;

public class RegraDeNegocio extends RuntimeException {
    private static final long serialVersionUID = 8053686215231656545L;

    public RegraDeNegocio(String message) {
        super(message);
    }
}
