package br.com.wcorrea.transport.api.repository.classeDespeza;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;


public class ClasseDespezaFiltro extends QueryFiltroPadrao{

    @Getter
    @Setter
    private String descricao;
}
