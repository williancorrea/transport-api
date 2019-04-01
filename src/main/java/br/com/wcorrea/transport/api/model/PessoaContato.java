package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.service.exception.PessoaContatoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode()
@Entity(name = "pessoa_contato")
@Data
public class PessoaContato extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 4246579222887013491L;

    @Size(max = 150)
    private String nome;

    @Size(max = 250)
    private String email;

    @Size(max = 14)
    @Column(name = "fone_comercial")
    private String foneComercial;

    @Size(max = 14)
    @Column(name = "fone_residencial")
    private String foneResidencial;

    @Size(max = 14)
    @Column(name = "fone_celular")
    private String foneCelular;

    private boolean inativo;

    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;

    public PessoaContato() {
    }
}
