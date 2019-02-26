package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.veiculo.ItinerarioNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "itinerario")
public class Itinerario extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 15)
    @Getter
    @Setter
    private String codigo;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String nome;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "valido_ate")
    private Date validoAte;

    @Getter
    @Setter
    private boolean ativo;

    public Itinerario() {
    }
}
