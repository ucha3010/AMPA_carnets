package util;

import java.util.Date;

public class Hora {
	
	public static String ahora() {
		return (new Date()).toString() + " - ";
	}

}
