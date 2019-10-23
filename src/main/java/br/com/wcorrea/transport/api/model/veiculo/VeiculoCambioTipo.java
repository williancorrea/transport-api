package br.com.wcorrea.transport.api.model.veiculo;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum VeiculoCambioTipo {
    MANUAL("MANUAL"),
    AUTOMATICO("AUTOMATICO"),
    PROGRAMADO("PROGRAMADO");

    private String descricao;

    VeiculoCambioTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
