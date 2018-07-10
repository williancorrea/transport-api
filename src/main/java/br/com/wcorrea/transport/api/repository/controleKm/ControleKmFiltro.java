package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.utils.Criptografia;
import lombok.Getter;
import lombok.Setter;


public class ControleKmFiltro extends QueryFiltroPadrao {

    @Getter
    @Setter
    private Long kmSaida;

    @Getter
    @Setter
    private Long kmChegada;

    @Getter
    @Setter
    private String dataSaida;

    @Getter
    @Setter
    private String dataChegada;

    @Getter
    private Long itinerarioId;

    @Getter
    private Long pessoaId;

    @Getter
    private Long veiculoId;

    public void setItinerarioId(String itinerarioId) {
        this.itinerarioId = new Criptografia().getKey(itinerarioId);
    }

    public void setPessoaId(String pessoaId) {
        this.pessoaId = new Criptografia().getKey(pessoaId);
    }

    public void setVeiculoId(String veiculoId) {
        this.veiculoId = new Criptografia().getKey(veiculoId);
    }
}
