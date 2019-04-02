package br.com.wcorrea.transport.api.repository.itinerario;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItinerarioFiltro extends QueryFiltroPadrao {

    private String nome;
    private String codigo;
    private String descricao;
}
