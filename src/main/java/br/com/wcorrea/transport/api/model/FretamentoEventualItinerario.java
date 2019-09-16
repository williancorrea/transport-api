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


    @ManyToOne()
    @JoinColumn(name = "partida_cidade_id", referencedColumnName = "id")
    @NotNull
    private Cidade partidaCidade;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date partida;

    @ManyToOne()
    @JoinColumn(name = "retorno_cidade_id", referencedColumnName = "id")
    @NotNull
    private Cidade retornoCidade;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date retorno;

    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    @ManyToOne
    private Veiculo veiculo;

    @Column(name = "km_percorrido_quantidade")
    private int kmPercorridoQuantidade;

    @Lob
    @Column(name = "obs_itineratio")
    private String obsItineratio;

    public FretamentoEventualItinerario() {
    }

    public Cidade getPartidaCidade() {
        return partidaCidade;
    }

    public void setPartidaCidade(Cidade partidaCidade) {
        this.partidaCidade = partidaCidade;
    }
}
