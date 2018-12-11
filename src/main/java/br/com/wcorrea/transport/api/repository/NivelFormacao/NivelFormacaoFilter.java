package br.com.wcorrea.transport.api.repository.NivelFormacao;

import br.com.wcorrea.transport.api.repository.filter.padrao.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class NivelFormacaoFilter extends QueryFiltroPadrao {

    @Getter @Setter
    private String nome;
    @Getter @Setter
    private String descricao;
}
