package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Entity(name = "banco")
public class Banco extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 10)
    @Getter
    @Setter
    private String codigo;

    @NotBlank
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String nome;

    @Size(max = 250)
    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private boolean inativo;

    public Banco() {
    }
}
