package main.java.principal;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import main.java.acciones.GenerarArchivoConexion;
import main.java.pantalla.VentanaPrincipal;
import main.java.util.Comprobaciones;
import main.java.util.LocalLogger;

public class AMPAcarnets {

	public static void main(String[] args) {
		Logger LOG = Logger.getLogger(AMPAcarnets.class.getName());
		
		// Pantalla de visualización
		try {
			String idioma = Locale.getDefault().getLanguage();
			ArrayList<String> idiomasPermitidos = new ArrayList<String>();
			idiomasPermitidos.add("es");
			
			// genero conexión con archivo de constantes
			Properties p = new Properties();
			if(idioma != null && idiomasPermitidos.contains(idioma)) {
				p.load(new FileReader("src/main/resources/Constantes_" + idioma.toLowerCase() + ".properties"));
			} else {
				p.load(new FileReader("src/main/resources/Constantes_es.properties"));
			}
			
			// preparo ficheros de log
			Comprobaciones.verificarCrearDirectorio(p.getProperty("carpetaLogs"));
			FileHandler fh = new FileHandler(p.getProperty("carpetaLogs") + "logs.log", 10485760, 10, true);
			LOG.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
	        
	        // verifico existencia de archivo de conexión
	        if(Comprobaciones.verSiExisteCarpetaOArchivo(p.getProperty("carpetaConexion") + "Conexion.properties") == null ) {
	        	GenerarArchivoConexion.generar(p.getProperty("carpetaConexion"), "Conexion.properties", LOG);	        	
	        }
	        
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(p, LOG);
			ventanaPrincipal.setVisible(true);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.info(LocalLogger.logError(sw.toString()));
		}

	}

}
