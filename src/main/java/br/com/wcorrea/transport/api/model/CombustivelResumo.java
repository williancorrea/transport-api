package br.com.wcorrea.transport.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
public class CombustivelResumo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnoreProperties({"controle", "inativo"})
    private Combustivel combustivel;

    public CombustivelResumo() {
    }

    public CombustivelResumo(Combustivel combustivel) {
        this.combustivel = combustivel;
    }
}
