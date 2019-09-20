package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.FretamentoEventalTipo;
import br.com.wcorrea.transport.api.model.FretamentoEventual;
import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.model.pessoa.PessoaTipo;
import br.com.wcorrea.transport.api.repository.fretamentoEventual.FretamentoEventualEventualRepository;
import br.com.wcorrea.transport.api.repository.fretamentoEventual.FretamentoEventualFiltro;
import br.com.wcorrea.transport.api.service.exception.FretamentoEventualNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.RegraDeNegocio;
import br.com.wcorrea.transport.api.service.exception.veiculo.VeiculoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FretamentoEventualService {

    @Autowired
    private FretamentoEventualEventualRepository fretamentoEventualRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EstadoCidadeService estadoCidadeService;

    @Autowired
    private VeiculoService veiculoService;

    public Page<FretamentoEventual> listarTodos(FretamentoEventualFiltro filtro, Pageable paginacao){
        return fretamentoEventualRepository.findAll(filtro, paginacao);
    }

    public FretamentoEventual findOne(Long id) {
        if (id != null && id > 0) {
            Optional<FretamentoEventual> obj = fretamentoEventualRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new FretamentoEventualNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new FretamentoEventualNaoEncontrado();
        }
    }

    @Transactional
    public FretamentoEventual salvar(FretamentoEventual fretamentoEventual) {
        fretamentoEventual = this.prepararFretamentoParaPersistencia(fretamentoEventual);
        return fretamentoEventualRepository.save(fretamentoEventual);
    }

    @Transactional
    public FretamentoEventual atualizar(Long id, FretamentoEventual fretamentoEventual) {
        fretamentoEventual.setId(findOne(id).getId());
        fretamentoEventual = this.prepararFretamentoParaPersistencia(fretamentoEventual);
        return fretamentoEventualRepository.saveAndFlush(fretamentoEventual);
    }

    private FretamentoEventual prepararFretamentoParaPersistencia(FretamentoEventual fretamentoEventual){
        if (fretamentoEventual.getSituacao().equals(FretamentoEventalTipo.ORCAMENTO)) {
            fretamentoEventual.setCliente(null);
        } else {

            /**
             * FAZ A ATUALIZACAO DO CLIENTE SOMENTE NOS CAMPOS ALTERADOS NO FRETAMENTO
             */
            if(fretamentoEventual.getCliente() != null && fretamentoEventual.getCliente().isEditando()){
                Pessoa pEncontrada = pessoaService.buscarPorId(fretamentoEventual.getCliente().getId());

                if(pEncontrada.getTipo() == PessoaTipo.FISICA){
                    pEncontrada.getPessoaFisica().setRg(fretamentoEventual.getCliente().getPessoaFisica().getRg());
                }

                if(pEncontrada.getTipo() == PessoaTipo.JURIDICA){
                    pEncontrada.getPessoaJuridica().setInscricaoEstadual(fretamentoEventual.getCliente().getPessoaJuridica().getInscricaoEstadual());
                }

                pEncontrada.setImagem(fretamentoEventual.getCliente().getImagem());
                pEncontrada.setFantasia(fretamentoEventual.getCliente().getFantasia());
                pEncontrada.setNome(fretamentoEventual.getCliente().getNome());
                pEncontrada.setEmail(fretamentoEventual.getCliente().getEmail());
                pEncontrada.setTelefone1(fretamentoEventual.getCliente().getTelefone1());
                pEncontrada.setTelefone1Obs(fretamentoEventual.getCliente().getTelefone1Obs());
                pEncontrada.setTelefone2(fretamentoEventual.getCliente().getTelefone2());
                pEncontrada.setTelefone2Obs(fretamentoEventual.getCliente().getTelefone2Obs());
                pEncontrada.setCidade(fretamentoEventual.getCliente().getCidade());
                pEncontrada.setBairro(fretamentoEventual.getCliente().getBairro());
                pEncontrada.setCep(fretamentoEventual.getCliente().getCep());
                pEncontrada.setEndereco(fretamentoEventual.getCliente().getEndereco());
                pEncontrada.setObs(fretamentoEventual.getCliente().getObs());

                fretamentoEventual.setCliente(pEncontrada);
            }

            fretamentoEventual.setCliente(pessoaService.validarPessoa(fretamentoEventual.getCliente()));
        }

        //VERIFICA SE O PERIODO ENTRE AS DATA É VALIDO
        if (fretamentoEventual.getItinerario().getPartida().after(fretamentoEventual.getItinerario().getRetorno())) {
            throw new RegraDeNegocio("Data de partida n\u00e3o pode ser maior que a data de retorno");
        }

        // VERIFICA SE A CIDADE DE PARTIDA EXISTE
        try {
            Cidade c1 = estadoCidadeService.buscarPorId(fretamentoEventual.getItinerario().getPartidaCidade().getId());
            if (c1 == null) {
                throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
            }
            fretamentoEventual.getItinerario().setPartidaCidade(c1);
        } catch (Exception e) {
            throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
        }

        //VERIFICA SE A CIDADE DE RETORNO EXISTE
        try {
            Cidade c2 = estadoCidadeService.buscarPorId(fretamentoEventual.getItinerario().getRetornoCidade().getId());
            if (c2 == null) {
                throw new RegraDeNegocio("Cidade de retorno n\u00e3o encontrada.");
            }
            fretamentoEventual.getItinerario().setRetornoCidade(c2);
        } catch (Exception e) {
            throw new RegraDeNegocio("Cidade de retorno n\u00e3o encontrada.");
        }

        // VERIFICA SE O MOTORISTA 1 SELECIONADO EXISTE
        try {
            Pessoa m1 = pessoaService.buscarPorId(fretamentoEventual.getCusto().getMotorista1().getId());
            if (m1 == null) {
                throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
            }
            fretamentoEventual.getCusto().setMotorista1(m1);
        } catch (Exception e) {
            throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
        }

        // VERIFICA SE O MOTORISTA 2 SELECIONADO EXISTE
        try {
            Pessoa m2 = pessoaService.buscarPorId(fretamentoEventual.getCusto().getMotorista2().getId());
            if (m2 == null) {
                throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
            }
            fretamentoEventual.getCusto().setMotorista2(m2);
        } catch (Exception e) {
            throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
        }

        // VERIFICA SE O VEICULO SELECIONADO EXISTE
        try {
            Veiculo v = veiculoService.buscarPorId(fretamentoEventual.getItinerario().getVeiculo().getId());
            if (v == null) {
                throw new VeiculoNaoEncontrado();
            }
            fretamentoEventual.getItinerario().setVeiculo(v);
        } catch (Exception e) {
            throw new VeiculoNaoEncontrado();
        }

        return fretamentoEventual;
    }
}
