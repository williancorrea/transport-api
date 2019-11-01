package br.com.wcorrea.transport.api.model.financeiro;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum BancoContaTipo {
    FISICA("FISICA"),
    JURIDICA("JURIDICA"),
    CONTA_SALARIO("CONTA_SALARIO");

    private String descricao;

    BancoContaTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
