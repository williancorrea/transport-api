package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
    @Size(min = 8, max = 8)
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
