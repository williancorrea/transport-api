package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.service.exception.EstadoCivilNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Classe responsavel por manipular todos os atributos de um objeto do tipo Estado
 */
@ToString
@EqualsAndHashCode
@Entity(name = "estado")
public class Estado extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 150)
    @Getter
    @Setter
    private String descricao;

    @NotNull
    @Size(min = 2, max = 2)
    @Getter
    @Setter
    private String iniciais;

    @Column(name = "codigo_ibge")
    @Getter
    @Setter
    private Integer codigoIbge;

    public Estado() {
    }
}
