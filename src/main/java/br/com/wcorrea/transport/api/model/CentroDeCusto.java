package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.CentroDeCustoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Entity(name = "centro_de_custo")
public class CentroDeCusto extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 150)
    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private boolean inativo;

    @NotNull
    @JoinColumn(name = "classe_despesa_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    private ClasseDespesa classeDespesa;

    public CentroDeCusto() {
    }
}
