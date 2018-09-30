package br.com.wcorrea.transport.api.repository.classe;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.utils.Criptografia;
import lombok.Getter;
import lombok.Setter;


public class ClasseFiltro extends QueryFiltroPadrao{

    @Getter
    @Setter
    private String descricao;
}
