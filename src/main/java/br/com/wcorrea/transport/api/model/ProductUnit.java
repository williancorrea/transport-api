package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.UnidadeMedidaNaoEncontrada;
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

@ToString
@EqualsAndHashCode
@Entity(name = "product_unit")
public class ProductUnit extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @NotNull
    @Size(min = 1, max = 10)
    private String initials;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String name;

    @Column(name = "can_fraction")
    @Getter
    @Setter
    private Boolean canFraction;

    public ProductUnit() {
    }
}
