package br.com.wcorrea.transport.api.model.pessoa;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Classe responsavel por manipular todos os atributos de um objeto do tipo Estado
 */
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "estado")
@Data
public class Estado extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 3, max = 150)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 2)
    private String iniciais;

    @Column(name = "codigo_ibge")
    private Integer codigoIbge;

    public Estado() {
    }
}
