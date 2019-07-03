package principal;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import pantalla.VentanaPrincipal;
import util.LocalLogger;

public class AMPAcarnets {

	public static void main(String[] args) {
		String absolutePath = new File("").getAbsolutePath();
		Logger LOG = Logger.getLogger(AMPAcarnets.class.getName());
		
		// Pantalla de visualización
		try {
			FileHandler fh = new FileHandler(absolutePath + "//src//archivos//logs.log", 10240, 10, true);
			LOG.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(Locale.getDefault().getLanguage(), LOG);
			ventanaPrincipal.setVisible(true);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.info(LocalLogger.logError(sw.toString()));
		}

	}

}
