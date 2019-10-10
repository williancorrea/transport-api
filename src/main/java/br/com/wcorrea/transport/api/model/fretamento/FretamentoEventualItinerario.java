package br.com.wcorrea.transport.api.model.fretamento;

import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "previsao_chegada_partida")
    private Date previsaoChegadaPartida;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "previsao_chegada_retorno")
    private Date previsaoChegadaRetorno;

    @Size(max = 250)
    @Column(name = "local_saida")
    private String localSaida;

    @Size(max = 250)
    @Column(name = "local_retorno")
    private String localRetorno;

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
