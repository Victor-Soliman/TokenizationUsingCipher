package com.nasr_soliman.Tokenizetion.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

@Service
@Log4j2
public class TokenizationService {

    private final HashMap<String, String> tokenStore = new HashMap<>();
    private final SecretKey secretKey;

    public TokenizationService() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Poți schimba dimensiunea cheii
        secretKey = keyGen.generateKey();
    }

    public String tokenize(String sensitiveData) throws Exception {
        String token = generateToken();
        String encryptedData = encrypt(sensitiveData);
        tokenStore.put(token, encryptedData);
        log.info("Generated token: {} for data: {}", token, sensitiveData);
        log.info("tokenStore tokenize : {}", tokenStore);
        return token;
    }

    public String detokenize(String token) throws Exception {
        log.info("Token received: " + token);
        String encryptedData = this.tokenStore.get(token);
        if (encryptedData != null) {
            log.info("Found encrypted data for token: " + encryptedData);
            return decrypt(encryptedData);
        } else {
            log.warn("Token not found in tokenStore");
            return null;
        }
    }

    private String generateToken() {
        return String.valueOf(new SecureRandom().nextInt(1000000));
    }

    private String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}