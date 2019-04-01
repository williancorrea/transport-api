package br.com.wcorrea.transport.api.model;

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
@Entity(name = "pessoa_endereco")
@Data
public class PessoaEndereco extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 8059136906512768883L;

    @Size(max = 150)
    private String logradouro;

    @Size(max = 15)
    private String numero;

    @Size(max = 80)
    private String complemento;

    @Size(max = 100)
    private String bairro;

    @Size(max = 8)
    private String cep;

    private Boolean principal;

    private Boolean entrega;

    private Boolean cobranca;

    private Boolean correspondencia;

    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    @ManyToOne()
    private Cidade cidade;

    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;

    public PessoaEndereco() {
    }
}
