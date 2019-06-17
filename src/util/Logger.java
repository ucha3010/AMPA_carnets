package util;

public class Logger {
	
	public static String log(String clase, String mensaje) {
		return Hora.ahora() + clase + " - " + mensaje;
	}

}
