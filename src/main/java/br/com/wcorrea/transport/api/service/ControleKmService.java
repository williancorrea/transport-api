package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmRepository;
import br.com.wcorrea.transport.api.service.exception.veiculo.*;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Classe responsavel por manipular toda a regra de negocio de um ControleKm
 */
@Service
public class ControleKmService {

    @Autowired
    private ControleKmRepository controleKmRepository;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ItinerarioService itinerarioService;

    @Autowired
    private PessoaService pessoaService;


    /**
     * Atualizar
     *
     * @param id
     * @param controleKm
     * @return
     */
    public ControleKm atualizar(Long id, ControleKm controleKm) {
        ControleKm objFound = controleKmRepository.save(buscarPorId(id));
        controleKm.setId(objFound.getId());
        controleKm.setControle(objFound.getControle());
        controleKm.getControle().setDataAlteracao(new Date());

        controleKm = this.validarControleKm(controleKm);
        return controleKmRepository.save(controleKm);
    }

    public ControleKm salvar(ControleKm controleKm) {
        controleKm = this.validarControleKm(controleKm);
        return controleKmRepository.saveAndFlush(controleKm);
    }

    /**
     * Buscar por ID
     */
    public ControleKm buscarPorId(Long id) {
        ControleKm controleKm = controleKmRepository.findOne(id);
        if (controleKm == null) {
            throw new ControleKmNaoEncontrado();
        }
        return controleKm;
    }

    /**
     * Busca o Km de saida minimo a ser informado através do periodo
     *
     * @param dataSaida
     * @param veiculoId
     * @return
     */
    public Long buscarKmMinimoPeloPeriodo(Date dataSaida, Long veiculoId) {
        return controleKmRepository.recuperarKmSaidaMinimo(dataSaida, veiculoId);
    }

    public Long buscarKmMaximoPeloPeriodo(Date dataSaidaChegada, Long veiculoId) {
        return controleKmRepository.recuperarKmChegadaMaximo(dataSaidaChegada, veiculoId);
    }


    private ControleKm validarControleKm(ControleKm controleKm) {
        controleKm.setVeiculo(veiculoService.buscarPorId(controleKm.getVeiculo()));
        controleKm.setPessoa(pessoaService.buscarPorId(controleKm.getPessoa()));
        controleKm.setItinerario(itinerarioService.buscarPorId(controleKm.getItinerario()));

        // Data de saída de data de chegada identicas
        if (Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isEqual(Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()))) {
            throw new ControleKmDataSaidaDataChegadaIdenticas();
        }

        //Data de saída maior que a data de chegada
        if (Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isAfter(Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()))) {
            throw new ControleKmPeriodoEntreDatasInvalidos();
        }

        // Km de saída informado é menor que o odometro inical do veículo
        if (Long.parseLong(controleKm.getKmSaida()) < controleKm.getVeiculo().getOdometroInicial()) {
            throw new ControleKmKmSaidaMenorOdometroInicialVeiculo();
        }

        // Km de Saída e Km de Chegada identicos
        if (Long.parseLong(controleKm.getKmSaida()) == Long.parseLong(controleKm.getKmChegada())) {
            throw new ControleKmKmSaidaKmChegadaIdenticos();
        }

        //Km de saída maior que o km de chegada
        if (Long.parseLong(controleKm.getKmSaida()) > Long.parseLong(controleKm.getKmChegada())) {
            throw new ControleKmKmSaidaNaoPodeSerMaiorKmChegada();
        }

        //Verifica se o Km de Saida está sendo utilizado em outro apontamento
        if (controleKmRepository.validarPeriodoInvalidoKmSaida(controleKm.getId(), controleKm.getVeiculo().getId(), controleKm.getKmSaida())) {
            throw new ControleKmSaidaInvalido();
        }

        //Verifica se o Km de Chegada está sendo utilizado em outro apontamento
        if (controleKmRepository.validarPeriodoInvalidoKmChegada(controleKm.getId(), controleKm.getVeiculo().getId(), controleKm.getKmChegada())) {
            throw new ControleKmChegadaInvalido();
        }

        //TODO: Adicionar restrição de km inicial do sistema - adicionar quantidade permitida de km por dia (km * dia) p/ nao cadastrar km de carro errado

        // Valida Data de Saida
        if (controleKmRepository.validarPeriodoInvalidoDeEntradaDataSaida(controleKm)) {
            throw new ControleKmPeriodoInvalidoDeEntradaDataSaida();
        }

        //TODO: NAO PERMITIR O CADASTRAMENTO DE UM APONTAMENTO FUTURO (DATA MAIOR QUE AGORA)

        // Valida Data de Chegada
//        if (controleKmRepository.validarPeriodoInvalidoDeEntradaDataChegada(controleKm)) {
//            throw new ControleKmPeriodoInvalidoDeEntradaDataChegada();
//        }

//        if (true) {
//            throw new ControleKmNaoEncontrado();
//        }

        return controleKm;
    }
}
