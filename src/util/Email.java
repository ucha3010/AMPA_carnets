package util;

import java.io.File;
import java.io.FileReader;
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
	private static String absolutePath = new File("").getAbsolutePath();
    
    public static boolean enviarCarnet(String emailReceptor, String rutaArchivo, String familia){
        String asunto = "Envío de carnet AMPA";
        String mensaje = EmailDisenio.disenioCarnets(familia, nombreArchivo(rutaArchivo));
        boolean enviadoCompra = funcionEnviar(emailReceptor, rutaArchivo, asunto, mensaje);
        /*if(enviadoCompra){
            //Esto sirve para enviarle el mismo email al remitente
            asunto = "Copia del original";
            mensaje = "Una copia de lo enviado";
            funcionEnviar(p.getProperty("email"),rutaArchivo,asunto,mensaje);
        }*/
        return enviadoCompra;
    }
    
    //public static boolean enviarCorreo(String[] para){
    private static boolean funcionEnviar(String para, String rutaArchivo, String asunto, String mensaje){
        boolean enviado = false;
        boolean existeArchivo = false; //variable para obligar a que exista el archivo para el envío del email
            try{
            	Properties p = new Properties();
    			p.load(new FileReader("src/util/Conexion.properties"));
        		
                String de = p.getProperty("email");
                final String username = p.getProperty("usuario");
                final String password = p.getProperty("clave");
                
                Properties prop = System.getProperties();
                
                prop.put("mail.smtp.starttls.enable",p.getProperty("mail.smtp.starttls.enable"));
                prop.put("mail.smtp.host", p.getProperty("mail.smtp.host"));
                prop.put("mail.smtp.port",p.getProperty("mail.smtp.port"));
                prop.put("mail.smtp.auth",p.getProperty("mail.smtp.auth"));
                
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
                    existeArchivo = true;
                    //para agregar la imagen al multipart (aunque no estoy seguro si el multipart se reemplazará, como
                    //debería ser, o si se agregará lo que traigo a lo que ya había, con lo cual se duplicarán cosas)
                    multipart = addCID("logo_AMPA",absolutePath + "\\src\\imagenes\\Logo_AMPA_completo.gif",multipart);
                    multipart = addCID("logo_email",absolutePath + "\\src\\imagenes\\Email.gif",multipart);
                    multipart = addCID("logo_facebook",absolutePath + "\\src\\imagenes\\Facebook.gif",multipart);
                    multipart = addCID("logo_twitter",absolutePath + "\\src\\imagenes\\Twitter.gif",multipart);
                    multipart = addCID("logo_instagram",absolutePath + "\\src\\imagenes\\Instagram.gif",multipart);
                    multipart = addCID("logo_youtube",absolutePath + "\\src\\imagenes\\Youtube.gif",multipart);
                }                
                message.setContent(multipart);            
//                messageBodyPart.setText(mensaje); 
                if(existeArchivo) {
                	Transport.send(message);
                }
                multipart = null;
                enviado = true;                
            }catch(Exception e){
                e.printStackTrace();               
            }        
        return enviado && existeArchivo;
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