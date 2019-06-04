package util;

public class Comprobaciones {
	
	public static boolean noEsNullNiBlanco(String cadena){
		return cadena != null && !cadena.trim().equals("");
	}

}
