package br.com.wcorrea.transport.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TelefoneTipo {
    RESIDENCIAL("RESIDENCIAL"),
    COMERCIAL("COMERCIAL"),
    PARTICULAR("PARTICULAR");

    private String descricao;

    TelefoneTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
