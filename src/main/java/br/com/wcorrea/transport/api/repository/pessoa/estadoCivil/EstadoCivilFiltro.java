package br.com.wcorrea.transport.api.repository.estadoCivil;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class EstadoCivilFiltro extends QueryFiltroPadrao {

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String descricao;
}
