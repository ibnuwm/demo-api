package com.domain.utils;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

public class StringAttributeConverter implements AttributeConverter<String, String> {

    private static final String AES = "AES";
    // encryption key must 16 characters
    private static final byte[] encryptionKey = "contoh-test-keys".getBytes();

    private final Cipher encryptionCipher;
    private final Cipher decryptCipher;;

    public StringAttributeConverter() throws Exception {
        Key key = new SecretKeySpec(encryptionKey, AES);
        encryptionCipher = Cipher.getInstance(AES);
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher = Cipher.getInstance(AES);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);

    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return Base64.getEncoder().encodeToString(encryptionCipher.doFinal(attribute.getBytes()));
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
