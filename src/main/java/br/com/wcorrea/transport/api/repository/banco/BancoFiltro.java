package br.com.wcorrea.transport.api.repository.banco;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class BancoFiltro extends QueryFiltroPadrao {

    @Getter
    @Setter
    private String codigo;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String inativo;
}
