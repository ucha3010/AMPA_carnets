package util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.PasswordAuthentication;

public class Email{
    
    public static boolean enviarCarnet(String to, String rutaArchivo){
        String asunto = "Envío de carnet AMPA";
        String mensaje = EmailDisenio.disenioCarnets(to, nombreArchivo(rutaArchivo));
        boolean enviadoCompra = funcionEnviar(to, rutaArchivo, asunto, mensaje);
        /*if(enviadoCompra){
            //Esto sirve para enviarle la factura también al vendedor cada vez que se realice una venta
            asunto = "Se realizó una compra";
            mensaje = "Factura de la compra";
            funcionEnviar("email.vendedor",rutaArchivo,asunto,mensaje);
        }*/
        return enviadoCompra;
    }
    
    //public static boolean enviarCorreo(String[] para){
    private static boolean funcionEnviar(String para, String rutaArchivo, String asunto, String mensaje){
        boolean enviado = false;
            try{
                String de = "damianjava@gmail.com";
                String host = "smtp.gmail.com";
                final String username = "damianjava@gmail.com";
                final String password = "sepultura30";
                
                Properties prop = System.getProperties();
                
                prop.put("mail.smtp.starttls.enable","true");
                prop.put("mail.smtp.host", host);
                prop.put("mail.smtp.port",587);
                prop.put("mail.smtp.auth","true");
                
                Session sesion = Session.getInstance(prop,
                   new Authenticator() {
            		@Override
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
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                String tipoDeFormato = Comprobaciones.verSiExisteCarpetaOArchivo(rutaArchivo);
                if(Comprobaciones.noEsNullNiBlanco(rutaArchivo) && tipoDeFormato != null && 
                		tipoDeFormato.equals("archivo")){
                    messageBodyPart = new MimeBodyPart();
                    String archivo = nombreArchivo(rutaArchivo);
                    DataSource source = new FileDataSource(rutaArchivo);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(archivo);
                    multipart.addBodyPart(messageBodyPart);
                    //para agregar la imagen al multipart (aunque no estoy seguro si el multipart se reemplazará, como
                    //debería ser, o si se agregará lo que traigo a lo que ya había, con lo cual se duplicarán cosas)
                    //multipart = addCID("cidnombre","ruta comleta del archivo",multipart);
                }                
                message.setContent(multipart);            
//                messageBodyPart.setText(mensaje);               
                Transport.send(message);
                multipart = null;
                enviado = true;                
            }catch(Exception e){
                e.printStackTrace();               
            }        
        return enviado;
    }
    
    private static String nombreArchivo(String rutaArchivo) {
		String[] listado = rutaArchivo.split("\\\\");
		return listado[listado.length - 1];
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