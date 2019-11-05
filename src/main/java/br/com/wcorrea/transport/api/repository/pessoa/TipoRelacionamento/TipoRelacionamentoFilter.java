package br.com.wcorrea.transport.api.repository.pessoa.TipoRelacionamento;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class TipoRelacionamentoFilter extends QueryFiltroPadrao {

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private String codigo;
}
