package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmRepository;
import br.com.wcorrea.transport.api.service.exception.veiculo.*;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private ControleKm validarControleKm(ControleKm controleKm) {
        controleKm.setVeiculo(veiculoService.buscarPorId(controleKm.getVeiculo()));
        controleKm.setPessoa(pessoaService.buscarPorId(controleKm.getPessoa()));
        controleKm.setItinerario(itinerarioService.buscarPorId(controleKm.getItinerario()));

        if (Utils.convertToLocalDateTime(controleKm.getDataHoraSaida()).isAfter(Utils.convertToLocalDateTime(controleKm.getDataHoraChegada()))) {
            throw new ControleKmPeriodoEntreDatasInvalidos();
        }
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

        //TODO: testar o localDateTime novamente, depois das alteraçoes de timezono no arquivo properties

        //TODO: Adicionar restrição de km inicial do sistema - adicionar quantidade permitida de km por dia (km * dia) p/ nao cadastrar km de carro errado
        //Valida Data de Saida
//        if (controleKmRepository.validarPeriodoInvalidoDeEntradaDataSaida(controleKm)) {
//            throw new ControleKmPeriodoInvalidoDeEntradaDataSaida();
//        }
        //Valida Km Saida
//        if (controleKmRepository.validarPeriodoInvalidoDeEntradaKmSaida(controleKm)) {
//            throw new ControleKmPeriodoInvalidoDeEntradaKmSaida();
//        }
        //Valida Data de Chegada
//        if (controleKmRepository.validarPeriodoInvalidoDeEntradaDataChegada(controleKm)) {
//            throw new ControleKmPeriodoInvalidoDeEntradaDataChegada();
//        }
        //Valida Km Chegada
//        if (controleKmRepository.validarPeriodoInvalidoDeEntradaKmChegada(controleKm)) {
//            throw new ControleKmPeriodoInvalidoDeEntradaKmChegada();
//        }
        return controleKm;
    }
}
