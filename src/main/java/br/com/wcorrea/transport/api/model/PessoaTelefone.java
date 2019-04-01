package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "pessoa_telefone")
@Data
public class PessoaTelefone extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 6624819545097126083L;

    @NotBlank
    @Column(name = "telefone_tipo")
    @Enumerated
    private TelefoneTipo telefoneTipo;

    @Size(max = 14)
    private String numero;

    @Size(max = 250)
    private String observacao;

    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;

    public PessoaTelefone() {
    }
}
