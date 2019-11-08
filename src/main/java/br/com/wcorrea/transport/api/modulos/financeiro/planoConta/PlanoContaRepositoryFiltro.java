package br.com.wcorrea.transport.api.modulos.financeiro.planoConta;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlanoContaRepositoryFiltro extends QueryFiltroPadrao {
    private String inativo;
}
