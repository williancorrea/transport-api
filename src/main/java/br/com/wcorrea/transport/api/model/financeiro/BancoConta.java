package br.com.wcorrea.transport.api.model.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_BANCO_CONTA")
@Data
public class BancoConta extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_BANCO_AGENCIA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BancoAgencia bancoAgencia;

    @Size(max = 20)
    private String contaNumero;

    @Size(max = 1)
    private String contaDigito;

    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;

    @Size(max = 250)
    private String url;

    @Lob
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BancoContaTipo tipo;

    private boolean inativo;

    public BancoConta() {
    }
}
