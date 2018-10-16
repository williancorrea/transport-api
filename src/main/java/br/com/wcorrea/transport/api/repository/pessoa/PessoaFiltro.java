package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class PessoaFiltro extends QueryFiltroPadrao {

    @Getter
    @Setter
    private String nome;
}
