package main.java.util;

public class LocalLogger {

	public static String log(String clase, String mensaje) {
		return Hora.ahora() + clase + " - " + mensaje;
	}

	public static String logIn() {
		return "ENTRADA";
	}

	public static String logOut() {
		return "SALIDA";
	}

	public static String logIn(String ejecutor) {
		return "ENTRADA - " + ejecutor;
	}

	public static String logOut(String ejecutor) {
		return "SALIDA - " + ejecutor;
	}

	public static String logError(String mensaje) {
		return "ERROR: " + mensaje;
	}

}
