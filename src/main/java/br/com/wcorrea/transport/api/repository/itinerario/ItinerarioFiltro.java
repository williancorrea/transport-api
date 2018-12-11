package br.com.wcorrea.transport.api.repository.itinerario;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class ItinerarioFiltro extends QueryFiltroPadrao {

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String codigo;

    @Getter @Setter
    private String descricao;
}
