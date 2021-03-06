package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "centro_de_custo")
@Data
public class CentroDeCusto extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 150)
    private String descricao;

    private boolean inativo;

    @NotNull
    @JoinColumn(name = "classe_despesa_id", referencedColumnName = "id")
    @ManyToOne()
    private ClasseDespesa classeDespesa;

    public CentroDeCusto() {
    }
}
