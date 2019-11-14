package br.com.wcorrea.transport.api.modulos.financeiro;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum DocumentoOrigemTipo {
    FRETAMENTO_EVENTUAL("FRETAMENTO_EVENTUAL");

    private String descricao;

    DocumentoOrigemTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
