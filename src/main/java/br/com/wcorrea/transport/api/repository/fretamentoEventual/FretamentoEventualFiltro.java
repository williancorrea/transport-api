package br.com.wcorrea.transport.api.repository.fretamentoEventual;

import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class FretamentoEventualFiltro extends QueryFiltroPadrao {

//    private String codigo;
//    private String inativo;

    private Long veiculoId;
    private String veiculoKey;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataPartida;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataRetorno;

    private boolean situacaoNaoContratado;
    private boolean situacaoOrcamento;
    private boolean situacaoContratado;
}
