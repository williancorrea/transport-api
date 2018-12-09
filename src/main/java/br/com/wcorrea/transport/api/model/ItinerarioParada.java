package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "Itinerario_parada")
public class ItinerarioParada extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String nome;

    @Size(max = 15)
    @Getter
    @Setter
    private String codigo;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String descricao;

    @JoinColumn(name = "itinerario_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    private Itinerario itinerario;

    public ItinerarioParada() {
    }
}
