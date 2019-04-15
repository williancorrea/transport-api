package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "unidade_medida")
@Data
public class UnidadeMedida extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 5, max = 150)
    private String nome;

    @NotNull
    @Size(min = 1, max = 10)
    private String sigla;

    @Column(name = "pode_fracionar")
    private Boolean podeFracionar;

    private Boolean inativo;

    public UnidadeMedida() {
    }
}
