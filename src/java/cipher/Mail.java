package cipher;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Diego
 */
public class Mail {

    //SendMail es la función principal que envia el correo, tiene un caracter string como parámetro, que es la cuenta de gmail emisora
    public static void sendMail(String body) throws Exception{
        System.out.println("Preparandome para enviar correo");
        //Archivos de propiedad, configuran cosas como el host el puerto etc...
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.stmp.port", "587");
        //Pasamos la cuenta emisora y su contraseña como constantes
        String cuentaCorreo = "tartangagrupo3c@gmail.com";
        String contraCorreo = "thsl bqfa tmtg deox";
      
        
        //Con Session iniciamos la sesion
        Session session = Session.getInstance(properties,  new Authenticator(){
               @Override
               protected PasswordAuthentication getPasswordAuthentication(){
                   return new PasswordAuthentication(cuentaCorreo, contraCorreo);
               }
    });
        //Aqui preparamos el mensaje para ser enviado con la funcion prepareMessage, el cual tiene la sesion, la cuenta de correo y la configuracion como parámetro
        Message message = prepareMessage(session, cuentaCorreo, body);
        //.send() envia el mensaje
         Transport.send(message);
         System.out.println("Correo Enviado");
    }
    //PrepareMessage es el mensaje de por sí
    private static Message prepareMessage(Session session, String cuentaCorreo, String body){
        //Capturamos una excepión en caso de que no se pueda enviar
        try{
            //Iniciamos el mensaje
            Message message = new MimeMessage(session);
            //Indicamos a quien le tiene que llegar
            message.setFrom(new InternetAddress(cuentaCorreo));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(body));
            //Indicamos el sujeto y el texto del mensaje
            message.setSubject("Correo prueba ");
            message.setText("Hola Esti");
            return message;
        }catch(Exception e){
            System.out.println("Error");
        }
        return null;
    }
    //Desde un método main llamamos a send mail y le pasamos la cuenta de correo destinataria
    public static void main(String[] args) throws Exception{
        sendMail("Conkergox98@gmail.com");
    }
}