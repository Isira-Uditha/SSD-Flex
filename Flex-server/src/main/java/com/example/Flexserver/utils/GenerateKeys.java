package com.example.Flexserver.utils;

import com.example.Flexserver.dao.JDBCInMemory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class GenerateKeys {

    @Autowired
    private JDBCInMemory jdbcInMemory;
    @Value("${APPID}")
    private String appId;
    @Value("${ALGORITHM}")
    private String algorithm;
    private KeyPairGenerator keyGen;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private static final String PUBLICKEY_PREFIX = "-----BEGIN PUBLIC KEY-----";
    private static final String PUBLICKEY_POSTFIX = "-----END PUBLIC KEY-----";
    private static final String PRIVATEKEY_PREFIX = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String PRIVATEKEY_POSTFIX = "-----END RSA PRIVATE KEY-----";

    private void generateSecureKeys() throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance(Constants.RSA);
        this.keyGen.initialize(1024);
    }

    private void createKeys() {
        KeyPair pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    private PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    private PublicKey getPublicKey() {
        return this.publicKey;
    }

    private void saveKeysInDb(String appId, String publicKey, String privateKey) {
        this.jdbcInMemory.insertData(appId, publicKey, privateKey);
    }

    public String keyGenerateAndReturnPublicKey(String appId) {
        String publicKeyPEM = null;
        String privateKeyPEM;
        log.info("main method of generator : {}");
        try {
            this.generateSecureKeys();
            this.createKeys();

            // THIS IS PEM:
            publicKeyPEM = PUBLICKEY_PREFIX + "\n" + DatatypeConverter.printBase64Binary(this.getPublicKey().getEncoded()).replaceAll("(.{64})", "$1\n") + "\n" + PUBLICKEY_POSTFIX;
            privateKeyPEM = PRIVATEKEY_PREFIX + "\n" + DatatypeConverter.printBase64Binary(this.getPrivateKey().getEncoded()).replaceAll("(.{64})", "$1\n") + "\n" + PRIVATEKEY_POSTFIX;
            this.saveKeysInDb(appId, publicKeyPEM, privateKeyPEM);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return publicKeyPEM;
    }

    public PrivateKey readPrivateKey(String appId)
            throws IOException, GeneralSecurityException {
        PrivateKey key;
        String fileString = this.jdbcInMemory.getPrivateKeyForAppId(appId);
        fileString = fileString.replace(
                        "-----BEGIN RSA PRIVATE KEY-----\n", "")
                .replace("-----END RSA PRIVATE KEY-----", "");
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(fileString);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(Constants.RSA);
        key = kf.generatePrivate(spec);
        return key;
    }

    public String decrypt(String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException {
        //Decrypt the message
        Cipher cipher = Cipher.getInstance(algorithm);
        try {
            cipher.init(Cipher.DECRYPT_MODE, this.readPrivateKey(Constants.APP_ID));
            byte[] encodedMessage = Base64.getDecoder().decode(encryptedData);
            String dyMessage = new String(cipher.doFinal(encodedMessage), Constants.UTF8);
            return dyMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
