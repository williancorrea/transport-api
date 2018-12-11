package br.com.wcorrea.transport.api.repository.veiculo;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Getter;
import lombok.Setter;

public class VeiculoFiltro extends QueryFiltroPadrao {

    @Getter @Setter
    private String placa;

    @Getter @Setter
    private String frota;
}
