package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ZipCode;
import br.com.wcorrea.transport.api.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.json.JSONObject;

/**
 * Class responsible for performing the entire business rule by manipulating tipo of zip code information
 */
@Service
public class ZipCodeService {

    @Autowired
    private ZipCodeRepository zipCodeRepository;
    private String URL_API = "http://www.cepaberto.com";
    private String TOKEN = "Token token=e06f809182c7e72992eebcc44376067c";
    private String AUTHORIZATION_HEADER = "Authorization";
    private String PATH = "api/v2/ceps.json";
    private Integer TIMEOUT_VALUE = 5000;

    /**
     * Find zip code by zipCode
     */
    public ZipCode findByZipCode(String zipCode) {
        ZipCode zipCodeFound = zipCodeRepository.getOne(zipCode);
        if (zipCodeFound == null) {
            zipCodeFound = searchZipCodeByCepAberto(zipCode);

            if (zipCodeFound != null) {
                if (zipCodeFound.getZipCode() != null) {
                    zipCodeFound = zipCodeRepository.save(zipCodeFound);
                } else {
                    zipCodeFound = null;
                }
            } else {
                //TODO: REMOVER ESSA GAMBIARRA
                //FEITO SOMENTE PARA PEGAR O ULTIMO ITEM CONSULTADO
                zipCodeRepository.save(new ZipCode(zipCode));
            }

        }
        return zipCodeFound;
    }

    public ZipCode searchZipCodeByCepAberto(String zipCode) {
//        try {
//            URL url = new URL(URL_API + "/" + PATH + "?cep=" + zipCode.replace("-",""));
//            URLConnection urlConnection = url.openConnection();
//            urlConnection.setConnectTimeout(TIMEOUT_VALUE);
//            urlConnection.setRequestProperty(AUTHORIZATION_HEADER, TOKEN);
//            InputStream is = urlConnection.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            StringBuilder jsonSb = new StringBuilder();
//            br.lines().forEach(l -> jsonSb.append(l.trim()));
//
//            JSONObject jsonObject = new JSONObject(jsonSb.toString());
//            if (jsonObject.has("cep")) {
//                ZipCode zipCodeReturn = new ZipCode();
//                zipCodeReturn.setZipCode(jsonObject.getString("cep").substring(0, 5) + "-" + jsonObject.getString("cep").substring(5, 8));
//                zipCodeReturn.setAddress(jsonObject.isNull("logradouro") ? null : jsonObject.getString("logradouro"));
//                zipCodeReturn.setNeighborhood(jsonObject.isNull("bairro") ? null : jsonObject.getString("bairro"));
//                zipCodeReturn.setIbge(jsonObject.isNull("ibge") ? null : jsonObject.getString("ibge"));
//                zipCodeReturn.setCity(jsonObject.isNull("cidade") ? null : jsonObject.getString("cidade"));
//                zipCodeReturn.setUf(jsonObject.isNull("estado") ? null : jsonObject.getString("estado"));
//                zipCodeReturn.setDdd(jsonObject.isNull("ddd") ? null : jsonObject.getInt("ddd"));
//                zipCodeReturn.setLongitude(jsonObject.isNull("longitude") ? null : jsonObject.getString("longitude"));
//                zipCodeReturn.setLatitude(jsonObject.isNull("latitude") ? null : jsonObject.getString("latitude"));
//                zipCodeReturn.setAltitude(jsonObject.isNull("altitude") ? null : jsonObject.getInt("altitude"));
//                return zipCodeReturn;
//            }
//        } catch (Exception e) {
//            //TODO: COLOCAR LOGGER
//            System.out.println("Error while consulting the zipCode(" + zipCode + ") at the webservice of www.viacep.com - " + e.getMessage());
//
//            //TODO: REMOVER DEPOIS DO CLONE DO VIA CEP - FORCANDO UM ERRO NA APLICACAO
//            ZipCode z = new ZipCode();
//            z.setZipCode("NULL");
//            return z;
//        }
        return null;
    }
}
