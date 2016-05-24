/*
 Esta clase se utiliza para enviar mail de productos
que no sean encontrados durante la consulta
de precios.

si el correo se envia desde una cuenta de google primero
hay que desactivar esta seguridad 
"https://www.google.com/settings/security/lesssecureapps"
 */
package odaprice;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Krlitos
 */
public class EnviadorDeEmail {
    private final Properties properties=new Properties();
    
    //Principal pri=new Principal();
    //BuscarDescripcion desc=new BuscarDescripcion();
            
    public String user;//correo usuario
    public String host;//servidor
    public String password;//contraseña de correo usuario
    public int puerto;//puerto para el servidor de correo
    public String transporte;
    private Session session;
    public String mensaje; 
    public String correo;//correo destinatario
    
    // este método establece las preferencias del remitente del correo
    private void init(){
        //smtp.google.com el host para google
        //"mail.parbras.com.ar"
        properties.put("mail.smtp.host",this.getHost());
        properties.put("mail.smtp.starttls.enable","true");
        
        // 587 el puerto para google
        //9025 parbras
        properties.put("mail.smtp.port",this.getPuerto());
        
        //properties.put("mail.smtp.mail.sender","carlos.arregin@parbras.com.ar");
        properties.put("mail.smtp.mail.sender",this.getUser());

        //properties.put("mail.smtp.user","carlos.arregin@parbras.com.ar");
         properties.put("mail.smtp.user",this.getUser());
        properties.put("mail.smtp.auth","true");
        
        session= Session.getDefaultInstance(properties);
    }
     
    //en este método indicamos los parámetros a enviar al destinatario
    public void sendEmail(){
        
        init();
        try{
            MimeMessage message= new MimeMessage(session);
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.getCorreo()));
            message.setSubject("AUTOMENSAJE DEL PROGRAMA CONSULTOR DE PRECIOS ODA");
            message.setText(this.getMensaje());
            //Transport t =session.getTransport("smtp");
            Transport t =session.getTransport(this.getTransporte());
            //t.connect((String)properties.get("mail.smtp.user"),"jcla39476");
            t.connect((String)properties.get("mail.smtp.user"),this.getPassword());
            t.sendMessage(message,message.getAllRecipients());
            t.close();
            
                        
        }catch(MessagingException me){
            JOptionPane.showMessageDialog(null, "tan mal los datos pa! fijate si? ");
            System.out.println("usuario "+this.getUser());
            System.out.println("contraseña: "+this.getPassword());
            System.out.println("sender: "+this.getUser());
            System.out.println("host: "+this.getHost());
            System.out.println("puerto: "+this.getPuerto());
            System.out.println("protocolo: "+this.getTransporte());
            System.out.println("destinatario: "+this.getCorreo());
            throw new RuntimeException(me);
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }
    
}

