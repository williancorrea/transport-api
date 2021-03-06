package br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.modulos.financeiro.banco.Banco;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_BANCO_EXTRATO_ABREVIACAO")
@Data
public class BancoExtratoAbreviacao extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_FIN_BANCO", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Banco banco;

    @Size(max = 250)
    @NotBlank
    private String nomeReduzido;

    @Size(max = 250)
    @NotBlank
    private String nomeCompleto;

    public BancoExtratoAbreviacao() {
    }
}
