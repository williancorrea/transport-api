package br.com.wcorrea.transport.api.repository.veiculo.combustivel;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CombustivelFiltro extends QueryFiltroPadrao {

    private String nome;
}
