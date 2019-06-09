package acciones;

import java.util.List;
import java.util.Map;

import util.Comprobaciones;
import util.Email;

public class EnviarEmailConCarnet {
	
	public void enviarEmail(List<Map<String, String>> datos, String carpetaCarnets, String curso){
		
		String rutaCarnets = carpetaCarnets + curso + "\\";
		String rutaOArchivo = Comprobaciones.verSiExisteCarpetaOArchivo(rutaCarnets);
		boolean enviados = false;
		if(rutaOArchivo != null && rutaOArchivo.equals("carpeta") && datos != null && datos.size() > 0){
			enviados = true;
			for (int i = 0; i < datos.size(); i++) {
				if (datos.get(i) != null && Comprobaciones.noEsNullNiBlanco(datos.get(i).get("EMAIL"))
						&& Comprobaciones.noEsNullNiBlanco(datos.get(i).get("Nº SOCIO"))) {
					
					if(!Email.enviarCarnet(datos.get(i).get("EMAIL"), rutaCarnets + datos.get(i).get("Nº SOCIO") + ".jpg")) {
						enviados = false;
					}
					

				} else {
					System.out.println("Email de posición " + (i+1) + " del listado no se puede enviar");	
				}
			}
			
		} else {
			System.out.println("No existe la carpeta donde buscar los carnets");
		}
		
		if(enviados) {
			System.out.println("Se enviaron todos los emails");
		} else {
			System.out.println("No se enviaron todos los emails");
		}
		
	}

}
