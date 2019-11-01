package br.com.wcorrea.transport.api.model.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_RECEBIMENTO_TIPO")
@Data
public class RecebimentoTipo extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 5, max = 100)
    private String descricao;

    private boolean inativo;

    public RecebimentoTipo() {
    }
}
