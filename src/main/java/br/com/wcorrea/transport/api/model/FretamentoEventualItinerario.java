package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class FretamentoEventualItinerario implements Serializable {
    private static final long serialVersionUID = 3994755249799993687L;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "partida_cidade_id", referencedColumnName = "id")
    private Cidade partidaCidade;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date partida;

    @Lob
    @Column(name = "partida_obs")
    private String partidaObs;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "retorno_cidade_id", referencedColumnName = "id")
    private Cidade retornoCidade;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date retorno;

    @Lob
    @Column(name = "retorno_obs")
    private String retornoOs;

    public FretamentoEventualItinerario() {
    }
}
