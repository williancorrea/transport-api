package br.com.wcorrea.transport.api.model.pessoa;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "pessoa_auditoria")
@Data
public class PessoaAuditoria extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = -458706723465766394L;

    @Lob
    @NotBlank
    private String objetoAlterado;

    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;

    public PessoaAuditoria() {
    }
}
