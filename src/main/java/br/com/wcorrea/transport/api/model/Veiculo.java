package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.veiculo.VeiculoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "veiculo")
public class Veiculo extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 8, max = 8)
    @Getter
    private String placa;

    @Size(max = 15)
    @Getter
    @Setter
    private String frota;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String obs;

    @Getter
    @Setter
    @Column(name = "odometro_inicial")
    private int odometroInicial;

    public Veiculo() {
    }
}
