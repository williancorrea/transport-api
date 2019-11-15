package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecebimentoParcelaRepositoryFiltro extends QueryFiltroPadrao {
    private String inativo;
}
