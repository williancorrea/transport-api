package br.com.wcorrea.transport.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class VeiculoResumo implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private String key;
    private String placa;
    private String frota;
    private int odometroInicial;
    private BigDecimal consumoReal;
    private int velocidadeMedia;

    public VeiculoResumo() {
    }

    public VeiculoResumo(Veiculo veiculo) {
        this.key = veiculo.getKey();
        this.placa = veiculo.getPlaca();
        this.frota = veiculo.getFrota();
        this.odometroInicial = veiculo.getOdometroInicial();
        this.consumoReal = veiculo.getConsumoReal();
        this.velocidadeMedia = veiculo.getVelocidadeMedia();
    }


}
