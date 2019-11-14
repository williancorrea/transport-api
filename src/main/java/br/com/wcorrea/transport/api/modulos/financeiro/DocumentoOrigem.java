package br.com.wcorrea.transport.api.modulos.financeiro;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventual;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity(name = "FIN_DOCUMENTO_ORIGEM")
@Data
public class DocumentoOrigem extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DocumentoOrigemTipo tipo;

    @Size(min = 5, max = 250)
    private String descricao;

    @JoinColumn(name = "ID_FRETAMENTO_EVENTUAL", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private FretamentoEventual fretamentoEventual;

    public DocumentoOrigem() {
    }
}
