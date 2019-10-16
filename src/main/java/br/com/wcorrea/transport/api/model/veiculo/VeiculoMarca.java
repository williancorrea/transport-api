package br.com.wcorrea.transport.api.model.veiculo;

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
@Entity(name = "veiculo_marca")
@Data
public class VeiculoMarca extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 100)
    private String nome;

    private boolean inativo;

    public VeiculoMarca() {
    }
}
