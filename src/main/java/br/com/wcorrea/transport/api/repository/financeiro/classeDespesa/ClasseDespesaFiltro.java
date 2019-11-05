package br.com.wcorrea.transport.api.repository.financeiro.classeDespesa;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;


public class ClasseDespesaFiltro extends QueryFiltroPadrao{

    @Getter
    @Setter
    private String descricao;
}
