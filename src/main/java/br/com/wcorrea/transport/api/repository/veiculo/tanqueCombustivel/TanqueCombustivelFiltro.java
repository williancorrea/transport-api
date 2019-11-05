package br.com.wcorrea.transport.api.repository.veiculo.tanqueCombustivel;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TanqueCombustivelFiltro extends QueryFiltroPadrao {

    private String nome;
    private String combustivelId;
}
