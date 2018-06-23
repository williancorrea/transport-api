package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmRepository;
import br.com.wcorrea.transport.api.service.exception.veiculo.ControleKmNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.veiculo.ControleKmSaidaInvalido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        controleKm = this.validarControleKm(controleKm);
        ControleKm objFound = controleKmRepository.save(buscarPorId(id));
        controleKm.setId(objFound.getId());
        controleKm.setControle(objFound.getControle());
        controleKm.getControle().setDataAlteracao(LocalDateTime.now());
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

        if(controleKmRepository.validarPeriodoInvalidoKmSaida(controleKm.getId(), controleKm.getKmSaida())){
            throw new ControleKmSaidaInvalido();
        }

        //Validar km, data
        //KM Maximo pela data

        //TODO: Verificar se o km que está sendo cadastrado, está em um intervalo já registrado
        return controleKm;
    }
}
