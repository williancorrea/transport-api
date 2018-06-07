package br.com.wcorrea.transport.api.utils;

import br.com.wcorrea.transport.api.config.AutowireHelper;
import br.com.wcorrea.transport.api.config.property.ApiProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Transient;

public class Criptografia {

    final static String algorithm = "AES";

    @Autowired
    @Transient
    private ApiProperty apiProperty;

    private String encrypt(String encode) throws Exception {
        AutowireHelper.autowire(this, this.apiProperty);
        if (!apiProperty.getSecurity().isEnableCryptography()) {
            return StringUtils.isNotEmpty(encode) ? encode : null;
        }

        byte[] dataToSend = encode.getBytes();
        Cipher c = Cipher.getInstance(algorithm);
        SecretKeySpec k = new SecretKeySpec(apiProperty.getSecurity().getSecretKeyAes().getBytes(), algorithm);
        c.init(Cipher.ENCRYPT_MODE, k);
        byte[] encryptedData = c.doFinal(dataToSend);
        byte[] encryptedByteValue = new Base64().encode(encryptedData);
        return new String(encryptedByteValue);//.toString();
    }

    public String decrypt(String decode) throws Exception {
        AutowireHelper.autowire(this, this.apiProperty);
        if (!apiProperty.getSecurity().isEnableCryptography()) {
            return StringUtils.isNotEmpty(decode) ? decode : null;
        }

        byte[] encryptedData = new Base64().decode(decode.getBytes());
        Cipher c = Cipher.getInstance(algorithm);
        SecretKeySpec k = new SecretKeySpec(apiProperty.getSecurity().getSecretKeyAes().getBytes(), algorithm);
        c.init(Cipher.DECRYPT_MODE, k);
        byte[] decrypted = c.doFinal(encryptedData);
        return new String(decrypted);
    }

    /**
     * Convert byte of string to hexadecimal
     *
     * @param encode
     * @return
     */
    public String encryptToHex(String encode) throws Exception {
        AutowireHelper.autowire(this, this.apiProperty);
        if (!apiProperty.getSecurity().isEnableCryptography()) {
            return StringUtils.isNotEmpty(encode) ? encode : null;
        }

        byte[] ba = this.encrypt(encode).getBytes();

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ba.length; i++)
            str.append(String.format("%x", ba[i]));
        return str.toString();
    }

    /**
     * Convert Hexadecimal to string
     *
     * @param decode
     * @return
     */
    public String decryptFromHex(String decode) throws Exception {
        AutowireHelper.autowire(this, this.apiProperty);
        if (!apiProperty.getSecurity().isEnableCryptography()) {
            return StringUtils.isNotEmpty(decode) ? decode : null;
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < decode.length(); i += 2) {
            str.append((char) Integer.parseInt(decode.substring(i, i + 2), 16));
        }
        return this.decrypt(str.toString());
    }
}
