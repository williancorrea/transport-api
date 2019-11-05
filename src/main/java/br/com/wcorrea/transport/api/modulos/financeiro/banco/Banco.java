package br.com.wcorrea.transport.api.modulos.financeiro.banco;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_BANCO")
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

    @Lob
    @Column(name = "LOGO_BASE_64")
    private String logo;

    private boolean inativo;

    public Banco() {
    }
}
