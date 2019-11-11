package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.financeiro.DocumentoOrigem;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.modulos.financeiro.planoConta.PlanoConta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_RECEBIMENTO_LANCAMENTO")
@Data
public class RecebimentoLancamento extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "ID_FIN_DOCUMENTO_ORIGEM", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, optional = false)
    @NotNull
    private DocumentoOrigem documentoOrigem;

    @JoinColumn(name = "ID_FIN_PLANO_DE_CONTA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull
    private PlanoConta planoConta;

    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull
    private Pessoa cliente;

    @JoinColumn(name = "ID_VENDEDOR", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull
    private Pessoa vendedor;

    @Column(name = "QUANTIDADE_PARCELA")
    @Min(1)
    private int qtdParcela;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("1.00")
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

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

    public RecebimentoLancamento() {
    }
}
