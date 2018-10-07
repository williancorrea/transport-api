package br.com.wcorrea.transport.api.repository.classeDespesa;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;


public class ClasseDespesaFiltro extends QueryFiltroPadrao{

    @Getter
    @Setter
    private String descricao;
}
