package br.com.wcorrea.transport.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum NotaFiscalTipo {
    SEM_NOTA("SEM_NOTA"),
    NOTA_SERVICO("NOTA_SERVICO"),
    CTE_OS("CTE_OS");

    private String descricao;

    NotaFiscalTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
