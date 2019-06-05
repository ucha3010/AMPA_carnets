package util;

import java.net.PasswordAuthentication;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class Email{
    
    static String ruta;
    
    public static boolean enviarAlta(String destinatario, int id){
        boolean enviadoAlta = false;
        String asunto = "Activación de cuenta de usuario";
        String mensaje = EmailDisenio.DisenioAlta(id);
        enviadoAlta = funcionEnviar(destinatario, "", asunto, mensaje);
        return enviadoAlta;
    }
    
    public static boolean enviarCorreo(String to, String file){
        boolean enviadoCompra = false;
        String asunto = "Envío de factura";
        String mensaje = EmailDisenio.DisenioCompra(to, file);
        enviadoCompra = funcionEnviar(to, file, asunto, mensaje);
        if(enviadoCompra){
            //Esto sirve para enviarle la factura también al vendedor cada vez que se realice una venta
            asunto = "Se realizó una compra";
            mensaje = "Factura de la compra";
            funcionEnviar(Acciones.HomePropiedades.muestraValor("email.vendedor"),file,asunto,mensaje);
        }
        return enviadoCompra;
    }
    
    public static boolean enviarApadrina(String nombre, String email, Usuarios u){
        boolean enviadoCompra = Boolean.FALSE;
        String asunto = "Invitación enviada por "+u.getUsuNombre();
        String mensaje = EmailDisenio.DisenioApadrina(nombre, email, u);
        enviadoCompra = funcionEnviar(email, "", asunto, mensaje);
        if(enviadoCompra){
            Usuarios invitado = new Usuarios();
            Date ahora = new Date();
            invitado.setUsuNombre("");
            invitado.setUsuApellidos("");
            invitado.setUsuEmail(email);
            invitado.setUsuPassword("qualisoftware");
            invitado.setUsuDni("");
            invitado.setUsuDireccion("");
            invitado.setUsuCp("");
            invitado.setUsuFechaNac(ahora);
            invitado.setUsuSexo(Boolean.FALSE);
            invitado.setUsuTelefono("");
            invitado.setUsuLocalidad("");
            double desc = 0;
            invitado.setUsuDescuento(desc);
            invitado.setUsuFechaLimiteDesc(ahora);
            invitado.setUsuAdministrador(2);
            invitado.setProvincias("d");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 1908);
            cal.set(Calendar.MONTH, Calendar.APRIL);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date alta = cal.getTime();
            invitado.setUsuAlta(alta);
            invitado.setUsuActivo(Boolean.FALSE);
            ControladoresDAO.cUsuarios.Inserta(invitado);            
        }
        return enviadoCompra;
    }
    
    public static boolean enviarRecuperaPass(Usuarios u){
        boolean enviadoCompra = Boolean.FALSE;
        String asunto = "Recuperación de contraseña de "+u.getUsuNombre();
        String mensaje = EmailDisenio.DisenioRecuperaPass(u);
        enviadoCompra = funcionEnviar(u.getUsuEmail(), "", asunto, mensaje);
        return enviadoCompra;
    }
    
    //public static boolean enviarCorreo(String[] para){
    private static boolean funcionEnviar(String para, String archivo, String asunto, String mensaje){
        boolean enviado = false;
            try{
                String de = Acciones.HomePropiedades.muestraValor("email.empresa");
//                String host = "smtp.gmail.com";
//                final String username = "empresa2016sl@gmail.com";
//                final String password = "********";
                String host = "smtp.superlook.es";
                final String username = "no-reply@superlook.es";
                final String password = "Superl@@k2017";
                
                Properties prop = System.getProperties();
                
                prop.put("mail.smtp.starttls.enable","false");
                prop.put("mail.smtp.host", host);
//                prop.put("mail.smtp.port",587);
                prop.put("mail.smtp.port",25);
                prop.put("mail.smtp.auth","true");
                
                Session sesion = Session.getInstance(prop,
                   new javax.mail.Authenticator() {
                      protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication(username, password);
                      }
                });                
                MimeMessage message = new MimeMessage(sesion);                
                message.setFrom(new InternetAddress(de));                
                /*                    
                    NOTA: para enviar correo electronico masivo
                
                    InternetAddress[] direcciones = new InternetAddress[para.length];
                    for(int i=0;i<para.length;i++){
                        direcciones[i] = new InternetAddress(para[i]);
                    }                
                    for(int i=0;i<direcciones.length;i++){
                        message.addRecipient(Message.RecipientType.TO, direcciones[i]);
                    }                
                */                
                //Para correo a un solo receptor va esta línea
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));                
                message.setSubject(asunto);                
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(mensaje, "text/html");                
                //messageBodyPart.setText(mensaje);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                if(!archivo.equals("")){
                    messageBodyPart = new MimeBodyPart();
                    Ruta();
                    String filename = ruta + archivo;
                    DataSource source = new FileDataSource(filename);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(archivo);
                    multipart.addBodyPart(messageBodyPart);
                    //para agregar la imagen al multipart (aunque no estoy seguro si el multipart se reemplazará, como
                    //debería ser, o si se agregará lo que traigo a lo que ya había, con lo cual se duplicarán cosas)
                    //multipart = addCID("cidnombre","ruta comleta del archivo",multipart);
                }                
                message.setContent(multipart);                
                Transport.send(message);
                multipart = null;
                enviado = true;                
            }catch(Exception e){
                e.printStackTrace();
            }        
        return enviado;
    }
        
    private static void Ruta(){
        ruta = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
        String eliminar = "build"+System.getProperty("file.separator");
        ruta = ruta.replace(eliminar, "");
        ruta += "Archivos"+System.getProperty("file.separator");       
   }
    
    public static Multipart addCID(String cidname,String pathname, Multipart multipart) throws Exception {
        DataSource fds = new FileDataSource(pathname);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<"+cidname+">");
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}