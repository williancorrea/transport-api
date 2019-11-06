package br.com.wcorrea.transport.api.modulos.financeiro.bancoConta;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BancoContaRepositoryFiltro extends QueryFiltroPadrao {

    private String inativo;
}
