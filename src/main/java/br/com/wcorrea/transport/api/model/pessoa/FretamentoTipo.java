package br.com.wcorrea.transport.api.model.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum FretamentoTipo {
    ORCAMENTO("ORCAMENTO"),
    AGENDADO("AGENDADO"),
    NAO_CONTRATADO("NAO_CONTRATADO"),
    CONTRATADO("CONTRATADO");

    private String descricao;

    FretamentoTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
