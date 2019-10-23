package br.com.wcorrea.transport.api.model.veiculo;

import br.com.wcorrea.transport.api.model.Combustivel;
import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Column(name = "capacidade_oleo_motor_lts")
    private int capacidadeOleoMotorLts;

    @Column(name = "capacidade_oleo_cambio_lts")
    private int capacidadeOleoCambioLts;

    @Column(name = "capacidade_oleo_diferencial_lts")
    private int capacidadeOleoDiferencialLts;

    @JoinColumn(name = "veiculo_modelo_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    private VeiculoModelo veiculoModelo;

    @JoinColumn(name = "veiculo_marca_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    private VeiculoMarca veiculoMarca;

    @NotBlank
    @Column(name = "ano_modelo")
    private String anoModelo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_veiculo")
    private VeiculoTipo tipoVeiculo;

    @Column(name = "pode_ser_fretado")
    private boolean podeSerFretado;

    @JoinColumn(name = "combustivel_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    private Combustivel combustivel;

    @Size(max = 50)
    private String cor;

    @NotBlank
    @Size(max = 50)
    @Column(name = "renavam")
    private String renavamNumero;

    @NotBlank
    @Size(max = 50)
    @Column(name = "chassi")
    private String chassiNumero;

    @NotBlank
    @Size(max = 50)
    @Column(name = "motor")
    private String motorNumero;

    @NotBlank
    @Size(max = 100)
    @Column(name = "motor_modelo")
    private String motorModelo;

    @Column(name = "quantidade_pneus")
    private int qtdPneus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cambio_tipo")
    private VeiculoCambioTipo cambioTipo;

    @NotBlank
    @Size(max = 100)
    @Column(name = "cambio_modelo")
    private String cambioModelo;

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
