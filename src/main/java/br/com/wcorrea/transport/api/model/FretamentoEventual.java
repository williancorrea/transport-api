package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "fretamento_eventual")
@Data
public class FretamentoEventual extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 6855045965253378941L;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FretamentoEventalTipo situacao;

    @JoinColumn(name = "pessoa_cliente_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, optional = false)
    private Pessoa cliente;

    @Embedded
    @Valid
    private FretamentoEventualContato contato;

    @NotNull
    @Embedded
    @Valid
    private FretamentoEventualItinerario itinerario;

    @Embedded
    @Valid
    private FretamentoEventualCusto custo;
}
