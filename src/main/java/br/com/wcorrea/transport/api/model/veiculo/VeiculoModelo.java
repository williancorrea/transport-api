package br.com.wcorrea.transport.api.model.veiculo;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "veiculo_modelo")
@Data
public class VeiculoModelo extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 100)
    private String nome;

    @JoinColumn(name = "veiculo_marca_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    private VeiculoMarca veiculoMarca;

    private boolean inativo;

    public VeiculoModelo() {
    }
}
