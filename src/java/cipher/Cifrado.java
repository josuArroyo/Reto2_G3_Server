/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Diego
 */
public class Cifrado {
    private static final Logger LOGGER = Logger.getLogger(Cifrado.class.getName());
    private static byte[] salt = "Esto es el salt".getBytes();
    private byte[] iv;
    private static String clave ="abcd*1234";
    
    public String generatePassword(){
        String message = generateRandomKey();
        return message;
    }
    
    public String cipherPassword(String message){
        String hash = hashMessage(message);
        return hash;
    }
    
    private static String hashMessage(String message){
        Hash hash = new Hash();
        String hasheado = hash.cifrarTexto(message);
        return hasheado;
    }
    
    public static String desencriptar(String plaintext) {
        
        byte[] passwordContent = hexStringToByteArray(plaintext);
        byte[] bs = null;
        //PublicKey key;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
                       
            cipher.init(Cipher.DECRYPT_MODE, readPrivateKey());
            bs = cipher.doFinal(passwordContent);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeySpecException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(bs);

    }
    
    public static PrivateKey readPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
         PrivateKey pvKey = null;
        try {
            // Obtener los bytes del archivo donde este guardado la llave publica
            byte[] pvKeyBytes = hexStringToByteArray("");
            //
            PKCS8EncodedKeySpec encPvKeySpec = new PKCS8EncodedKeySpec(pvKeyBytes);
            //
            pvKey = KeyFactory.getInstance("RSA").generatePrivate(encPvKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pvKey;
    }
    
    public static String byteArrayToHexString(byte[] bytes) {
         BigInteger bi = new BigInteger(1, bytes);
         return String.format("%0" + (bytes.length << 1) + "X", bi);
    }
    
    public static PublicKey readPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(fileReader(filename));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicSpec);
    }
    
    private static byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            Logger.getLogger(Cifrado.class.getName()).log(Level.SEVERE, null, e);
        }
        return ret;
    }
    
    private byte[] concatArrays(byte[] array1, byte[] array2) {
        byte[] ret = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, ret, 0, array1.length);
        System.arraycopy(array2, 0, ret, array1.length, array2.length);
        return ret;
    }
    
    private void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public byte[] descifrarTexto(byte[] data) {
    byte[] decodedMessage = null;
        // Fichero leíd
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            // Creamos un SecretKey usando la clave + salt
            keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // Creamos un Cipher con el algoritmos que vamos a usar
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(data, 0, 16)); // La IV est  aqu 
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            decodedMessage = cipher.doFinal(Arrays.copyOfRange(data, 16, data.length));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedMessage;    
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
    
    public static String generateRandomKey() {
        
        LOGGER.info("Generando contraseña");
        
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        
            sb.append("*");
            for (int i = 0; i < 8; i++) {
                int randomIndex = random.nextInt(chars.length());
                sb.append(chars.charAt(randomIndex));
            }
        return sb.toString();
    }
}
