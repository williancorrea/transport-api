package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
@Data
public class FretamentoEventualCusto implements Serializable {
    private static final long serialVersionUID = -3982075058435584113L;

    @JoinColumn(name = "pessoa_motorista1_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Pessoa motorista1;

    @JoinColumn(name = "pessoa_motorista2_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa motorista2;

    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    @ManyToOne
    private Veiculo veiculo;

    @NotNull
    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_motorista1_diaria")
    private BigDecimal valorMotorista1Diaria;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
//    @Column(columnDefinition="Decimal(10,2) default '0.00'")
//    @Column(name = "valor_motorista2_diaria", nullable = false, columnDefinition = "big_decimal default 0.00", precision = 20, scale = 2)
    @Column(name = "valor_motorista2_diaria")
    private BigDecimal valorMotorista2Diaria;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nota_fiscal_tipo")
    private NotaFiscalTipo notaFiscalTipo;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "nota_fiscal_imposto")
    private BigDecimal notaFiscalImposto;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_estacionamento")
    private BigDecimal valorEstacionamento;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_agua")
    private BigDecimal valorAgua;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_gelo")
    private BigDecimal valorGelo;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_despesas_adicionais")
    private BigDecimal valorDespesasAdicionais;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_pedagio")
    private BigDecimal valorPedagio;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_dinheiro_reserva")
    private BigDecimal valorDinheiroReserva;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_km")
    private BigDecimal valorKm;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_combustivel")
    private BigDecimal valorCombustivel;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_hospedagem")
    private BigDecimal valorHospedagem;

    @Column(name = "cobranca_automatica")
    private boolean cobrancaAutomatica;

    @Column(name = "km_percorrido_quantidade")
    private int kmPercorridoQuantidade;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "valor_total_despesas")
    private BigDecimal valorTotalDespesas;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "viagem_preco_sugerido")
    private BigDecimal viagemPrecoSugerido;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "viagem_preco_final")
    private BigDecimal viagemPrecoFinal;

    @Lob
    @Column(name = "obs_custo")
    private String obsCusto;

    public FretamentoEventualCusto() {
    }
}
