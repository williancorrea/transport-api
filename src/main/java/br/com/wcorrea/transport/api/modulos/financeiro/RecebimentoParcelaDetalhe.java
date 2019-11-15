package br.com.wcorrea.transport.api.modulos.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoConta.BancoConta;
import br.com.wcorrea.transport.api.modulos.financeiro.chequeRecebido.ChequeRecebido;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela.RecebimentoParcela;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoTipo.RecebimentoTipo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_RECEBIMENTO_PARCELA_DETALHE")
@Data
public class RecebimentoParcelaDetalhe extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_FIN_RECEBIMENTO_TIPO", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RecebimentoTipo recebimentoTipo;

    @JoinColumn(name = "ID_FIN_CHEQUE_RECEBIDO", referencedColumnName = "id")
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ChequeRecebido chequeRecebido;

    @JoinColumn(name = "ID_FIN_BANCO_CONTA", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private BancoConta bancoConta;

    @JoinColumn(name = "ID_FIN_PARCELA_RECEBER", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull
    private RecebimentoParcela recebimentoParcela;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATA_RECEBIMENTO")
    private Date dataRecebimento;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal taxaJuros;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal valorJuro;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal taxaMulta;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal valorMulta;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal taxaDesconto;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal valorDesconto;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal valorRecebido;

    public RecebimentoParcelaDetalhe() {
    }
}
