package br.com.wcorrea.transport.api.repository.CentroDeCusto;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;


public class CentroDeCustoFiltro extends QueryFiltroPadrao {

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private Long classeId;
}
