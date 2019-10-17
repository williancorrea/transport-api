package br.com.wcorrea.transport.api.model.veiculo;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "veiculo")
@Data
public class Veiculo extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 8)
    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Size(max = 10)
    private String frota;

    @Size(max = 512)
    @Lob
    private String obs;

    @Column(name = "odometro_inicial")
    private int odometroInicial;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "consumo_real")
    private BigDecimal consumoReal;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    @Column(name = "consumo_atual")
    private BigDecimal consumoAtual;

    @Column(name = "velocidade_media")
    private int velocidadeMedia;

    @Column(name = "quantidade_lugares")
    private int qtdLugares;

    @Column(name = "capacidade_tanque_combustivel_lts")
    private int capacidadeTanqueCombustivelLts;

    @JoinColumn(name = "veiculo_modelo_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    private VeiculoModelo veiculoModelo;

    @JoinColumn(name = "veiculo_marca_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    private VeiculoMarca veiculoMarca;

    private boolean inativo;

    public Veiculo() {
    }

    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public void setFrota(String frota) {
        this.frota = StringUtils.isNotBlank(frota) ? frota.toUpperCase().trim() : "";
    }
}
