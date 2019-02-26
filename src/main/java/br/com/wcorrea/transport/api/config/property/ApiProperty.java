package br.com.wcorrea.transport.api.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("custom-property")
public class ApiProperty {

    @Getter @Setter
    private Security security = new Security();

    @Getter @Setter
    private String originAllowed = "http://localhost:8000";

    public static class Security {
        @Getter @Setter
        private boolean enableHttps;
        @Getter @Setter
        private String secretKeyAes = "";
        @Getter @Setter
        private boolean enableCryptography;
        @Getter @Setter
        private boolean enableCors;
    }

}
