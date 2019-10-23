package br.com.wcorrea.transport.api.model.veiculo;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum VeiculoTipo {
    PARTICULAR("PARTICULAR"),
    CARRO("CARRO"),
    CAMINHAO("CAMINHAO"),
    VAN("VAN"),
    MICRO_ONIBUS("MICRO_ONIBUS"),
    ONIBUS("ONIBUS");

    private String descricao;

    VeiculoTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
