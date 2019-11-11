package br.com.wcorrea.transport.api.modulos.financeiro.chequeRecebido;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
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
@Entity(name = "FIN_CHEQUE_RECEBIDO")
@Data
public class ChequeRecebido extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 14, max = 18)
    @Column(name = "CPF_CNPJ")
    private String cpf_cnpj;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    @Size(max = 10)
    @Column(name = "CODIGO_BANCO")
    private String codigoBanco;

    @NotBlank
    @Size(max = 10)
    @Column(name = "CODIGO_AGENCIA")
    private String codigoAgencia;

    @NotBlank
    @Size(max = 20)
    private String conta;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal valor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "BOM_PARA")
    private Date bomPara;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_COMPENSACAO")
    private Date dataCompensacao;

    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
    @ManyToOne
    @NotNull
    private Pessoa cliente;

    public ChequeRecebido() {
    }
}
