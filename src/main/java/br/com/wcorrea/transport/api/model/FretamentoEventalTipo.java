package br.com.wcorrea.transport.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum FretamentoEventalTipo {
    ORCAMENTO("ORCAMENTO"),
    AGENDADO("AGENDADO"),
    NAO_CONTRATADO("NAO_CONTRATADO"),
    CONTRATADO("CONTRATADO");

    private String descricao;

    FretamentoEventalTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
