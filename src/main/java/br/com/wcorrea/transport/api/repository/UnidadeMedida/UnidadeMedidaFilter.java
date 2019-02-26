package br.com.wcorrea.transport.api.repository.UnidadeMedida;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class UnidadeMedidaFilter extends QueryFiltroPadrao {

    @Getter
    @Setter
    private String sigla;

    @Getter
    @Setter
    private String nome;

}
