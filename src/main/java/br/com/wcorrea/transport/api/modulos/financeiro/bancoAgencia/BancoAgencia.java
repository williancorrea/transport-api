package br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.modulos.financeiro.banco.Banco;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_BANCO_AGENCIA")
@Data
public class BancoAgencia extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_FIN_BANCO", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Banco banco;

    @JoinColumn(name = "ID_CIDADE", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Cidade cidade;

    @Size(max = 50)
    @NotBlank
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
