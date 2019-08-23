package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.FretamentoTipo;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "fretamento")
@Data
public class Fretamento extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 6855045965253378941L;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FretamentoTipo situacao;

    @JsonIgnoreProperties({"controle", "listaPessoaAuditoria"})
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @NotNull
    private Pessoa cliente;

    @Embedded
    private FretamentoContato contato;

}
