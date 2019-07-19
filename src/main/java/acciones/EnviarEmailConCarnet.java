package main.java.acciones;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import main.java.util.Comprobaciones;
import main.java.util.Email;
import main.java.util.LocalLogger;

public class EnviarEmailConCarnet {
	
	public String enviarEmail(List<Map<String, String>> datos, String curso, Properties p, Logger LOG) throws Exception {
		
    	Properties conexion = new Properties();
    	conexion.load(new FileReader(p.getProperty("carpetaConexion") + "Conexion.properties"));
		
		String respuesta = null;
		String rutaCarnets = p.getProperty("carpetaGuardarCarnets") + curso + "\\";
		String rutaOArchivo = Comprobaciones.verSiExisteCarpetaOArchivo(rutaCarnets);
		List<Map<String, String>> datosEnviados;
		if(rutaOArchivo != null && rutaOArchivo.equals("carpeta") && datos != null && datos.size() > 0){
			boolean enviados = true;
			int contarOK = 0;
			datosEnviados = new ArrayList<Map<String,String>>();
			for (int i = 0; i < datos.size(); i++) {
				if (datos.get(i) != null && Comprobaciones.noEsNullNiBlanco(datos.get(i).get("EMAIL"))
						&& Comprobaciones.noEsNullNiBlanco(datos.get(i).get("Nº SOCIO"))) {
					
					if (!Email.enviarCarnet(datos.get(i).get("EMAIL"),
							rutaCarnets + datos.get(i).get("Nº SOCIO") + ".jpg", datos.get(i).get("FAMILIAS"), conexion, LOG)) {
						enviados = false;
					} else {
						datosEnviados.add(datos.get(i));
						contarOK++;
					}					

				} else {
					LOG.info(LocalLogger.logError("Email de posición " + (i+1) + " del listado no se puede enviar por falta de datos - " + datos.get(i)));	
				}
			}
			if(datosEnviados != null && datosEnviados.size() > 0) {
				Email.enviarResumen(conexion.getProperty("receptorResumen"), datosEnviados, conexion, LOG);
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
