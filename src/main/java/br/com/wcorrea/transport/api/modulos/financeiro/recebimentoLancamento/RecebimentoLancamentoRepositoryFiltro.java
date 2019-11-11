package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecebimentoLancamentoRepositoryFiltro extends QueryFiltroPadrao {
    private String inativo;
}
