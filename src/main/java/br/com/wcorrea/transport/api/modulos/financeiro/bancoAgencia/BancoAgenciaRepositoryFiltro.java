package br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BancoAgenciaRepositoryFiltro extends QueryFiltroPadrao {

    private String inativo;
}
