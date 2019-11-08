package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus;

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
@Entity(name = "FIN_RECEBIMENTO_PARCELA_STATUS")
@Data
public class RecebimentoParcelaStatus extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(min=2, max = 2)
    private String sigla;

    @NotBlank
    @Size(min = 5, max = 100)
    private String descricao;

    public RecebimentoParcelaStatus() {
    }
}


