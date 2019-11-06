package br.com.wcorrea.transport.api.modulos.financeiro.bancoConta;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia.BancoAgencia;
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
    @ManyToOne
    @NotNull
    private BancoAgencia bancoAgencia;

    @Size(max = 20)
    @NotBlank
    private String contaNumero;

    @Size(max = 1)
    @NotBlank
    private String contaDigito;

    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;

    @Lob
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BancoContaTipo tipo;

    private boolean inativo;

    public BancoConta() {
    }
}
