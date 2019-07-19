package main.java.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email{
	private static String absolutePath = new File("").getAbsolutePath();
    
    public static boolean enviarCarnet(String emailReceptor, String rutaArchivo, String familia, Properties conexion, Logger LOG){
        String asunto = "Envío de carnet AMPA";
        String mensaje = EmailDisenio.disenioCarnets(familia, nombreArchivo(rutaArchivo));
        boolean enviadoCompra = funcionEnviar(emailReceptor, rutaArchivo, asunto, mensaje, conexion, LOG);
        /*if(enviadoCompra){
            //Esto sirve para enviarle el mismo email al remitente
            asunto = "Copia del original";
            mensaje = "Una copia de lo enviado";
            funcionEnviar(p.getProperty("email"),rutaArchivo,asunto,mensaje);
        }*/
        return enviadoCompra;
    }
    
    public static boolean enviarResumen(String emailReceptor, List<Map<String, String>> datosEnviados, Properties conexion, Logger LOG){
        String asunto = "Resumen envío carnets";
        String mensaje = EmailDisenio.disenioResumen(datosEnviados);
        boolean enviadoCompra = funcionEnviar(emailReceptor, null, asunto, mensaje, conexion, LOG);
        return enviadoCompra;
    }
    
    //public static boolean enviarCorreo(String[] para){
    private static boolean funcionEnviar(String para, String rutaArchivo, String asunto, String mensaje, Properties conexion, Logger LOG){
        boolean enviado = false;
        boolean existeArchivo = false; //variable para obligar a que exista el archivo para el envío del email
        LOG.info(LocalLogger.log("funcionEnviar", "Email para: " + para));
            try{        		
                String de = conexion.getProperty("email");
                final String username = conexion.getProperty("usuario");
                final String password = conexion.getProperty("clave");
                
                Properties prop = System.getProperties();
                
                prop.put("mail.smtp.starttls.enable",conexion.getProperty("mail.smtp.starttls.enable"));
                prop.put("mail.smtp.host", conexion.getProperty("mail.smtp.host"));
                prop.put("mail.smtp.port",conexion.getProperty("mail.smtp.port"));
                prop.put("mail.smtp.auth",conexion.getProperty("mail.smtp.auth"));
                
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
                if(Comprobaciones.noEsNullNiBlanco(rutaArchivo)) {
	                String tipoDeFormato = Comprobaciones.verSiExisteCarpetaOArchivo(rutaArchivo);
	                if(tipoDeFormato != null && tipoDeFormato.equals("archivo")){
	                    messageBodyPart = new MimeBodyPart();
	                    String archivo = nombreArchivo(rutaArchivo);
	                    DataSource source = new FileDataSource(rutaArchivo);
	                    messageBodyPart.setDataHandler(new DataHandler(source));
	                    messageBodyPart.setFileName(archivo);
	                    multipart.addBodyPart(messageBodyPart);
	                    existeArchivo = true;
	                    //para agregar la imagen al multipart (aunque no estoy seguro si el multipart se reemplazará, como
	                    //debería ser, o si se agregará lo que traigo a lo que ya había, con lo cual se duplicarán cosas)
	                    multipart = addCID("logo_AMPA",absolutePath + "\\src\\main\\resources\\imagenes\\Logo_AMPA_completo.gif",multipart);
	                    multipart = addCID("logo_email",absolutePath + "\\src\\main\\resources\\imagenes\\Email.gif",multipart);
	                    multipart = addCID("logo_facebook",absolutePath + "\\src\\main\\resources\\imagenes\\Facebook.gif",multipart);
	                    multipart = addCID("logo_twitter",absolutePath + "\\src\\main\\resources\\imagenes\\Twitter.gif",multipart);
	                    multipart = addCID("logo_instagram",absolutePath + "\\src\\main\\resources\\imagenes\\Instagram.gif",multipart);
	                    multipart = addCID("logo_youtube",absolutePath + "\\src\\main\\resources\\imagenes\\Youtube.gif",multipart);
	                }  
                }
                message.setContent(multipart);            
//                messageBodyPart.setText(mensaje); 
                if(existeArchivo || asunto.substring(0, 7).equals("Resumen")) {
                	Transport.send(message);
                }
                multipart = null;
                enviado = true;                
            }catch(Exception e){
    			StringWriter sw = new StringWriter();
    			PrintWriter pw = new PrintWriter(sw);
    			e.printStackTrace(pw);
    			LOG.info(LocalLogger.logError(sw.toString()));               
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