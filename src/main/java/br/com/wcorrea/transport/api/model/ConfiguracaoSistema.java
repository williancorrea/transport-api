package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "configuracao_sistema")
@Data
public class ConfiguracaoSistema extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 250)
    private String nome;

    @NotBlank
    @Size(max = 250)
    private String descricao;

    @NotBlank
    @Size(max = 250)
    private String valor;

    public ConfiguracaoSistema() {
    }
}
