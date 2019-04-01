package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.veiculo.ItinerarioNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "itinerario")
@Data
public class Itinerario extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 15)
    private String codigo;

    @NotNull
    @Size(min = 5, max = 150)
    private String nome;

    @Size(max = 512)
    @Lob
    private String descricao;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "valido_ate")
    private Date validoAte;

    private boolean ativo;

    public Itinerario() {
    }
}
