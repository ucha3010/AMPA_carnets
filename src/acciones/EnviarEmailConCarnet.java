package acciones;

import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import util.Comprobaciones;
import util.Email;
import util.LocalLogger;

public class EnviarEmailConCarnet {
	
	public String enviarEmail(List<Map<String, String>> datos, String carpetaCarnets, String curso, String idioma, Logger LOG) throws Exception {
		

		Properties p = new Properties();
		p.load(new FileReader("src/util/Constantes_" + idioma + ".properties"));
		
		String respuesta = null;
		String rutaCarnets = carpetaCarnets + curso + "\\";
		String rutaOArchivo = Comprobaciones.verSiExisteCarpetaOArchivo(rutaCarnets);
		if(rutaOArchivo != null && rutaOArchivo.equals("carpeta") && datos != null && datos.size() > 0){
			boolean enviados = true;
			int contarOK = 0;
			for (int i = 0; i < datos.size(); i++) {
				if (datos.get(i) != null && Comprobaciones.noEsNullNiBlanco(datos.get(i).get("EMAIL"))
						&& Comprobaciones.noEsNullNiBlanco(datos.get(i).get("Nº SOCIO"))) {
					
					if(!Email.enviarCarnet(datos.get(i).get("EMAIL"), rutaCarnets + datos.get(i).get("Nº SOCIO") + ".jpg", datos.get(i).get("FAMILIAS"))) {
						enviados = false;
					} else {
						contarOK++;
					}					

				} else {
					LOG.info(LocalLogger.logError("Email de posición " + (i+1) + " del listado no se puede enviar por falta de datos - " + datos.get(i)));	
				}
			}
			if(enviados) {
				respuesta = p.getProperty("enviosOK") + " " + contarOK + " " + p.getProperty("enviosFinMensaje");
			} else {
				respuesta = p.getProperty("enviosKO1") + " " + contarOK + " " + p.getProperty("enviosKO2") + " " + datos.size() + " " + p.getProperty("enviosFinMensaje");
			}
			
		} else {
			respuesta = p.getProperty("carpetaError");
		}
		
		return respuesta;
	}

}
