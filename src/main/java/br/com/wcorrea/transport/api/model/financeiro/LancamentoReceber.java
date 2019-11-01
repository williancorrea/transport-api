package br.com.wcorrea.transport.api.model.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_LANCAMENTO_RECEBER")
@Data
public class LancamentoReceber extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_FIN_PLANO_DE_CONTA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PlanoConta planoConta;

    @JoinColumn(name = "ID_FIN_DOCUMENTO_ORIGEM", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DocumentoOrigem documentoOrigem;

    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa cliente;

    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa vendedor;

    @Column(name = "QUANTIDADE_PARCELA")
    private int qtdParcela;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "VALOR_A_RECEBER")
    private BigDecimal valorReceber;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "COMISSAO_TAXA")
    private BigDecimal comissaoTaxa;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "COMISSAO_VALOR")
    private BigDecimal comissaoValor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATA_LANCAMENTO")
    private Date dataLancamento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "PRIMEIRO_VENCIMENTO")
    private Date dataPrimeiroVencimento;

    @Size(max = 50)
    private String numeroDocumento;

    private boolean inativo;

    public LancamentoReceber() {
    }
}
