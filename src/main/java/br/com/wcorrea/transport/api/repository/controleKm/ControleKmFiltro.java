package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class ControleKmFiltro extends QueryFiltroPadrao {

    @Getter @Setter
    private String kmSaida;

    @Getter @Setter
    private String kmChegada;
}
