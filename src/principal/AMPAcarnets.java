package principal;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import pantalla.VentanaPrincipal;
import util.Comprobaciones;
import util.LocalLogger;

public class AMPAcarnets {

	public static void main(String[] args) {
		Logger LOG = Logger.getLogger(AMPAcarnets.class.getName());
		
		// Pantalla de visualización
		try {
			Properties p = new Properties();
			p.load(new FileReader("src/util/Constantes_" + Locale.getDefault().getLanguage() + ".properties"));
			Comprobaciones.verificarCrearDirectorio(p.getProperty("carpetaLogs"));
			FileHandler fh = new FileHandler(p.getProperty("carpetaLogs") + "logs.log", 10240, 10, true);
			LOG.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
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
