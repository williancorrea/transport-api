package br.com.wcorrea.transport.api.repository.veiculo.controleKm;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.service.exception.PessoaNaoEncontrada;
import br.com.wcorrea.transport.api.service.exception.veiculo.ItinerarioNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.veiculo.VeiculoNaoEncontrado;
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
        try {
            this.itinerarioId = new Criptografia().getKey(itinerarioId);
        } catch (Exception e) {
            throw new ItinerarioNaoEncontrado();
        }
    }

    public void setPessoaId(String pessoaId) {
        try {
            this.pessoaId = new Criptografia().getKey(pessoaId);
        } catch (Exception e) {
            throw new PessoaNaoEncontrada();
        }
    }

    public void setVeiculoId(String veiculoId) {
        try {
            this.veiculoId = new Criptografia().getKey(veiculoId);
        } catch (Exception e) {
            throw new VeiculoNaoEncontrado();
        }
    }
}
