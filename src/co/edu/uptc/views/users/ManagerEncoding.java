package co.edu.uptc.views.users;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.security.MessageDigest;
import java.util.Arrays;

public class ManagerEncoding {
    private static ManagerEncoding instance;
    private final String secretKey = "llaveSecreta";//TODO ocultar llave
    public static ManagerEncoding getInstance(){
        if (instance==null){
            instance = new ManagerEncoding();
        }
        return instance;
    }
    public String encode(String value) {
        String encripted = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keyPassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] BytesKey = Arrays.copyOf(keyPassword, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher encoder = Cipher.getInstance("DESede");
            encoder.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = value.getBytes("utf-8");
            byte[] buf = encoder.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            encripted = new String(base64Bytes);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error técnico");
        }
        return encripted;
    }

    public String decode(String value) {
        String decripted = "";
        try {
            byte[] message = Base64.decodeBase64(value.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decoder = Cipher.getInstance("DESede");
            decoder.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decoder.doFinal(message);
            decripted = new String(plainText, "UTF-8");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error técnico");
        }
        return decripted;
    }
}
