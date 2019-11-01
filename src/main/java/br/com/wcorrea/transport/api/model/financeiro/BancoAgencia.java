package br.com.wcorrea.transport.api.model.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_BANCO_AGENCIA")
@Data
public class BancoAgencia extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_BANCO", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Banco banco;

    @JoinColumn(name = "ID_CIDADE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidade cidade;

    @Size(max = 50)
    private String codigo;

    @Size(max = 1)
    private String digito;

    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;

    @Size(min = 5, max = 100)
    private String logradouro;

    @Size(min = 9, max = 9)
    private String cep;

    @Size(max = 100)
    private String bairro;

    @Size(max = 20)
    private String telefoneAgencia;

    @Size(max = 100)
    private String gerente;

    @Size(max = 20)
    private String telefoneGerente;

    @Lob
    private String obs;

    public BancoAgencia() {
    }
}
