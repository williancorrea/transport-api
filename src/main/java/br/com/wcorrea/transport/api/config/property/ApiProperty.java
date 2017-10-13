package br.com.wcorrea.transport.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("custom-property")
public class ApiProperty {

    private final Security security = new Security();
    private String originAllowed = "http://localhost:8000";

    public Security getSecurity() {
        return security;
    }

    public String getOriginAllowed() {
        return originAllowed;
    }

    public void setOriginAllowed(String originAllowed) {
        this.originAllowed = originAllowed;
    }

    public static class Security {

        private boolean enableHttps;
        private String secretKeyAes;
        private boolean enableCryptography;

        public boolean isEnableCryptography() {
            return enableCryptography;
        }

        public void setEnableCryptography(boolean enableCryptography) {
            this.enableCryptography = enableCryptography;
        }

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }

        public String getSecretKeyAes() {
            return secretKeyAes;
        }

        public void setSecretKeyAes(String secretKeyAes) {
            this.secretKeyAes = secretKeyAes;
        }
    }

}
