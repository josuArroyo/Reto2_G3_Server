/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Diego
 */
public class Hash {
    private static final Logger LOGGER = Logger.getLogger(Hash.class.getName());
    public String cifrarTexto(String texto){
        String hash="";
        try {
            //
            LOGGER.log(Level.INFO, "Cifrando:{0}", texto);
            MessageDigest messageDigest = null;
            messageDigest = messageDigest.getInstance("SHA-256");
            messageDigest.update(texto.getBytes());
            byte[] digest = messageDigest.digest();
            LOGGER.log(Level.INFO, "Digest hash{0}", new String(digest));
            hash = new String(digest);
            //Convierte datos en formato hexadecimal en String y minusculas
            hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
            LOGGER.info("Hash hash" + hash);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }

}
