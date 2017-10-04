package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ZipCode;
import br.com.wcorrea.transport.api.repository.ZipCodeRepository;
import br.com.wcorrea.transport.api.utils.Utils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for performing the entire business rule by manipulating type of zip code information
 */
@Service
public class ZipCodeService {

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    /**
     * Find zip code by zipCode
     */
    public ZipCode findByZipCode(String zipCode) {
        ZipCode zipCodeFound = zipCodeRepository.findOne(zipCode);
        if (zipCodeFound == null) {
            zipCodeFound = searchZipCode(zipCode);
            if (zipCodeFound != null) {
                zipCodeFound = zipCodeRepository.save(zipCodeFound);
            }
        }
        return zipCodeFound;
    }

    /**
     * @param zipCode
     * @return
     */
    private ZipCode searchZipCode(String zipCode) {
        try {
            Cep json = new Gson().fromJson(new Utils().getURL("http://viacep.com.br/ws/" + zipCode + "/json"), Cep.class);
            if (json.getCep() != null) {
                ZipCode zipCodeReturn = new ZipCode();
                zipCodeReturn.setZipCode(json.getCep());
                zipCodeReturn.setAddress(json.getLogradouro());
                zipCodeReturn.setComplement(json.getComplemento());
                zipCodeReturn.setNeighborhood(json.getBairro());
                zipCodeReturn.setLocality(json.getLocalidade());
                zipCodeReturn.setUf(json.getUf());
                zipCodeReturn.setUnit(json.getUnidade());
                zipCodeReturn.setIbge(Integer.parseInt(json.getIbge()));
                zipCodeReturn.setGia(json.getGia());
                return zipCodeReturn;
            }
        } catch (Exception e) {
            //TODO: COLOCAR LOGGER
            System.out.println("Error while consulting the zipCode(" + zipCode + ") at the webservice of www.viacep.com - " + e.getMessage());

            //TODO: REMOVER DEPOIS DO CLONE DO VIA CEP - FORCANDO UM ERRO NA APLICACAO
            ZipCode z = new ZipCode();
            z.setZipCode("NULL");
            return z;
        }
        return null;
    }
}

class Cep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

    public Cep() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}