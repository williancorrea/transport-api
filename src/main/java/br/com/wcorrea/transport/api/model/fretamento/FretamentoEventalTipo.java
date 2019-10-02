package br.com.wcorrea.transport.api.model.fretamento;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum FretamentoEventalTipo {

    ORCAMENTO_CONTATO("ORCAMENTO_CONTATO"),
    ORCAMENTO_CLIENTE("ORCAMENTO_CLIENTE"),
    NAO_CONTRATADO_CONTATO("NAO_CONTRATADO_CONTATO"),
    NAO_CONTRATADO_CLIENTE("NAO_CONTRATADO_CLIENTE"),
    CONTRATADO("CONTRATADO");

    private String descricao;

    FretamentoEventalTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
