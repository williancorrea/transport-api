package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "tanque_combustivel")
@Data
public class TanqueCombustivel extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 3, max = 150)
    private String nome;

    @Column(name = "quantidade_lts")
    @Min(value = 0)
    private int quantidadeLts;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "combustivel_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"inativo", "controle"})
    private Combustivel combustivel;

    private boolean inativo;

    public TanqueCombustivel() {
    }
}
