package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.service.exception.ClasseDespesaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Entity(name = "configuracao_sistema")
public class ConfiguracaoSistema extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max = 250)
    @Getter
    @Setter
    private String nome;

    @NotBlank
    @Size(max = 250)
    @Getter
    @Setter
    private String descricao;

    @NotBlank
    @Size(max = 250)
    @Getter
    @Setter
    private String valor;

    public ConfiguracaoSistema() {
    }
}
