package main.java.acciones;

import java.util.logging.Logger;

import main.java.util.ArchivoTexto;
import main.java.util.Comprobaciones;
import main.java.util.LocalLogger;

public class GenerarArchivoConexion {
	
	public static void generar(String ruta, String archivo, Logger LOG) {

		LOG.info(LocalLogger.logIn("GenerarArchivoConexion.generar"));
		String contenido = "email=\r\n" + 
				"usuario=\r\n" + 
				"clave=\r\n" + 
				"mail.smtp.host=\r\n" + 
				"mail.smtp.port=\r\n" + 
				"mail.smtp.starttls.enable=\r\n" + 
				"mail.smtp.auth=\r\n" + 
				"receptorResumen=";
		Comprobaciones.verificarCrearDirectorio(ruta);
		ArchivoTexto.crearYEscribirArchivoTexto(ruta + archivo, contenido, LOG);
		LOG.info(LocalLogger.logOut("GenerarArchivoConexion.generar"));
		
	}

}
