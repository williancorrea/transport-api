package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "banco")
@Data
public class Banco extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 10)
    private String codigo;

    @NotBlank
    @Size(min = 5, max = 150)
    private String nome;

    @Size(max = 250)
    private String url;

    private boolean inativo;

    public Banco() {
    }
}
