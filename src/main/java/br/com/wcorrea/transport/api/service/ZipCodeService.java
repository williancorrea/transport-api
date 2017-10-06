package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ZipCode;
import br.com.wcorrea.transport.api.repository.ZipCodeRepository;
import br.com.wcorrea.transport.api.utils.Utils;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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
            zipCodeFound = searchZipCodeByCepAberto(zipCode);
            if (zipCodeFound != null) {
                zipCodeFound = zipCodeRepository.save(zipCodeFound);
            }
        }
        return zipCodeFound;
    }

    private  String URL_API = "http://www.cepaberto.com";
    private  String TOKEN = "Token token=e06f809182c7e72992eebcc44376067c";
    private  String AUTHORIZATION_HEADER = "Authorization";
    private String PATH = "api/v2/ceps.json";
    private Integer TIMEOUT_VALUE = 5000;

    public ZipCode searchZipCodeByCepAberto(String zipCode) {
        try {
            URL url = new URL(URL_API + "/" + PATH + "?cep="+zipCode);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(TIMEOUT_VALUE);
            urlConnection.setRequestProperty(AUTHORIZATION_HEADER, TOKEN);
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();
            br.lines().forEach(l -> jsonSb.append(l.trim()));

            JSONObject jsonObject = new JSONObject(jsonSb.toString());
            if (jsonObject.has("cep")) {
                ZipCode zipCodeReturn = new ZipCode();
                zipCodeReturn.setZipCode(jsonObject.getString("cep").substring(0, 5) + "-" + jsonObject.getString("cep").substring(5, 8));
                zipCodeReturn.setAddress(jsonObject.getString("logradouro"));
                zipCodeReturn.setNeighborhood(jsonObject.getString("bairro"));
                zipCodeReturn.setIbge(jsonObject.getString("ibge"));
                zipCodeReturn.setCity(jsonObject.getString("cidade"));
                zipCodeReturn.setUf(jsonObject.getString("estado"));
                zipCodeReturn.setDdd(jsonObject.getInt("ddd"));
                zipCodeReturn.setLongitude(jsonObject.getString("longitude"));
                zipCodeReturn.setLatitude(jsonObject.getString("latitude"));
                zipCodeReturn.setAltitude(jsonObject.getInt("altitude"));
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