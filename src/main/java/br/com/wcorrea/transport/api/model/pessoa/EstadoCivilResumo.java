package br.com.wcorrea.transport.api.model.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
public class EstadoCivilResumo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnoreProperties({"descricao", "controle", "inativo"})
    private EstadoCivil estadoCivil;

    public EstadoCivilResumo() {
    }

    public EstadoCivilResumo(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}
