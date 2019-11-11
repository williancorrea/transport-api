package br.com.wcorrea.transport.api.model.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoConta.BancoConta;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento.RecebimentoLancamento;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus.RecebimentoParcelaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_PARCELA_RECEBER")
@Data
public class ParcelaReceber extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_FIN_LANCAMENTO_RECEBER", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RecebimentoLancamento recebimentoLancamento;

    @JoinColumn(name = "ID_FIN_STATUS_PARCELA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RecebimentoParcelaStatus recebimentoParcelaStatus;

    @JoinColumn(name = "ID_FIN_BANCO_CONTA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BancoConta bancoConta;

    @NotNull
    private int numeroParcela;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATA_VENCIMENTO")
    private Date dataVencimento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DESCONTO_ATE")
    private Date datadescontoAte;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal valor;

    private boolean emitiuBoleto;

    @Size(max = 100)
    private String boletoNossoNumero;

    public ParcelaReceber() {
    }
}
