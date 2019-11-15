package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaDetalhe.RecebimentoParcelaDetalhe;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoConta.BancoConta;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento.RecebimentoLancamento;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus.RecebimentoParcelaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_RECEBIMENTO_PARCELA")
@Data
public class RecebimentoParcela extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_FIN_RECEBIMENTO_LANCAMENTO", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private RecebimentoLancamento recebimentoLancamento;

    @JoinColumn(name = "ID_FIN_RECEBIMENTO_PARCELA_STATUS", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private RecebimentoParcelaStatus recebimentoParcelaStatus;

    @JoinColumn(name = "ID_FIN_BANCO_CONTA", referencedColumnName = "id")
    @ManyToOne
    private BancoConta bancoConta;

    @JsonIgnoreProperties({"recebimentoParcela"})
    @OneToMany(mappedBy = "recebimentoParcela", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval= true)
    private List<RecebimentoParcelaDetalhe> recebimentoParcelaDetalheList;

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
    @Column(name = "DESCONTO_ATE")
    private Date dataDescontoAte;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal valor;

    private boolean emitiuBoleto;

    @Size(max = 100)
    private String boletoNossoNumero;

    public RecebimentoParcela() {
    }
}
