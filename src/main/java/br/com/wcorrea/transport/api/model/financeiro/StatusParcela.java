package br.com.wcorrea.transport.api.model.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_STATUS_PARCELA")
@Data
public class StatusParcela extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 5, max = 100)
    private String descricao;

    @Size(max = 2)
    private String situacao;

    @Lob
    private String procedimento;

    private boolean inativo;

    public StatusParcela() {
    }
}


