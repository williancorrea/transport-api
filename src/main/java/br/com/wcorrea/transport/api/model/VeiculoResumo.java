package br.com.wcorrea.transport.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class VeiculoResumo implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private String key;
    private String placa;
    private String frota;

    public VeiculoResumo() {
    }

    public VeiculoResumo(Veiculo veiculo) {
        this.key = veiculo.getKey();
        this.placa = veiculo.getPlaca();
        this.frota = veiculo.getFrota();
    }


}
