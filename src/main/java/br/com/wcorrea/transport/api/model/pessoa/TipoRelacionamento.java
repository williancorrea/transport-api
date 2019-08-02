package br.com.wcorrea.transport.api.model.pessoa;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.TipoRelacionamentoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "tipo_relacionamento")
@Data
public class TipoRelacionamento extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 3)
    private String codigo;

    @NotNull
    @Size(min = 5, max = 150)
    private String nome;

    @Size(max = 512)
    @Lob
    private String descricao;

    public TipoRelacionamento() {
    }
}
