package br.com.wcorrea.transport.api.model.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_BANCO_EXTRATO_ABREVIACAO")
@Data
public class BancoExtratoAbreviacao extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_BANCO", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Banco banco;

    @Size(max = 250)
    private String nomeReduzido;

    @Size(max = 250)
    private String nomeCompleto;


    public BancoExtratoAbreviacao() {
    }
}
