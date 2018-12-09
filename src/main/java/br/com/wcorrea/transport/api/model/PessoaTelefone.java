package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.service.exception.PessoaTelefoneNaoEncontrado;
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
@Entity(name = "pessoa_telefone")
public class PessoaTelefone extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 6624819545097126083L;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "telefone_tipo")
    @Enumerated
    private TelefoneTipo telefoneTipo;

    @Getter
    @Setter
    @Size(max = 14)
    private String numero;

    @Getter
    @Setter
    @Size(max = 250)
    private String observacao;

    @Getter
    @Setter
    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;

    public PessoaTelefone() {
    }
}
