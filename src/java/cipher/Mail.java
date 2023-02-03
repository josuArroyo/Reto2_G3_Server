package cipher;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jboss.logging.Logger;
import java.util.logging.Level;


public class Mail {
//El método send Mail es el que se encarga de hacer el envio del correo.
    public static void sendMail(String body) throws Exception{
       // private static final Logger LOGGER = Logger.getLogger(Mail.class.getName());
        
        //Properties se encarga de las configuraciones necesarias para poder enviar correos, se encarga de cosas como
        //darte autoridad para enviar correos, el puerto para poder trabajar etc...
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.stmp.port", "587");
        //Aqui meteremos la cuenta de correo emisora, su cuenta y su CONTRASEÑA DE APLICACIÓN
        //la contraseña de correo normal no vale
        String cuentaCorreo = "tartangagrupo3c@gmail.com";
        String contraCorreo = "thsl bqfa tmtg deox";
        //Session inicia sesion para poder activar la cuenta de gmail para enviar correos
        Session session = Session.getInstance(properties,  new Authenticator(){
               @Override
               protected PasswordAuthentication getPasswordAuthentication(){
                   //Si la autentificacion falla saltará este error
                   return new PasswordAuthentication(cuentaCorreo, contraCorreo);
               }
    });
        //La función prepareMessage crea el mensaje de por sí
        Message message = prepareMessage(session, cuentaCorreo, body);
        //Transport.send() envia el mensaje
         Transport.send(message);
         
    }
    //Esta función se esncarga de crear el mensaje
    private static Message prepareMessage(Session session, String cuentaCorreo, String body){
        try{
            //Creamos una instancia de message a partir de una MimeMessage y le pasamos la sesión
            Message message = new MimeMessage(session);
            //Seteamos la dirección del receptor
            message.setFrom(new InternetAddress(cuentaCorreo));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(body));
            //Asunto del correo
            message.setSubject("Correo prueba");
            //Texto del correo
            message.setText("Mensaje de prueba");
            return message;
            //lanzará un error si ha ocurrido uno al fallar al crear el mensaje
        }catch(Exception e){
            
        }
        return null;
    }
    //El método sendMail(cuenta de correo destinataria) hace toda la función, dentro de ella hay que poner la cuenta que deseamos enviar el correo
    public static void main(String[] args) throws Exception{
        sendMail("xxxxxx@gmail.com");
    }
}