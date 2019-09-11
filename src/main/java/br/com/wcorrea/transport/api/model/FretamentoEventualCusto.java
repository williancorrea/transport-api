package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
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

    //TODO: Depois que criar o cadastro de motorista, este campo deve ser notnull
//    @NotNull
    private Pessoa motorista1;

    @JoinColumn(name = "pessoa_motorista2_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa motorista2;

    @NotNull
    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal motorista1Diaria;

    @Digits(integer = 20, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal motorista2Diaria;

    @Lob
    @Column(name = "obs_custo")
    private String obs;

    public FretamentoEventualCusto() {
    }
}
