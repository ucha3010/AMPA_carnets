package util;

import java.io.File;
import java.util.Arrays;

public class Comprobaciones {

	/*
	 * Verifica que la cadena de entrada no sea null ni esté en blanco
	 */
	public static boolean noEsNullNiBlanco(String cadena) {
		return cadena != null && !cadena.trim().equals("");
	}

	/*
	 * Verifica si una carpeta en una ruta dada existe. Si existe no hace nada. Si
	 * no existe la crea.
	 */
	public static void verificarCrearDirectorio(String carpetaCarnets) {
		if (Comprobaciones.noEsNullNiBlanco(carpetaCarnets)) {
			String[] listadoRuta = carpetaCarnets.split("\\\\");
			String rutaAux = "C:", rutaFinal = "";
			for (int i = 1; i < listadoRuta.length; i++) {
				rutaFinal = rutaAux.concat("\\" + listadoRuta[i]);
				File directorio = new File(rutaFinal);
				directorio.mkdir();
				rutaAux = rutaFinal;
			}
		}
	}

	/*
	 * Mira si existe un archivo o una carpeta y devuelve "archivo" si existe y la
	 * ruta es de un archivo o "carpeta" si existe y la ruta es de una carpeta o
	 * bien null si no existe
	 */
	public static String verSiExisteCarpetaOArchivo(String ruta) {
		String respuesta = null;
		if(noEsNullNiBlanco(ruta)) {
			File archivo = new File(ruta);
			if (archivo.exists()) {
				if (archivo.isFile()) {
					respuesta = "archivo";
				}
				if (archivo.isDirectory()) {
					respuesta = "carpeta";
				}
			}
		}
		return respuesta;
	}

	/*
	 * Verifica si un archivo tiene una extensión de las del listado
	 */
	public static boolean verificarExtensionDeArchivo(String s, String[] extn) {
		return Arrays.stream(extn).anyMatch(entry -> s.endsWith(entry));
	}

}
