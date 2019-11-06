package br.com.wcorrea.transport.api.modulos.financeiro.bancoConta;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum BancoContaTipo {
    FISICA("FISICA"),
    JURIDICA("JURIDICA");

    private String descricao;

    BancoContaTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
