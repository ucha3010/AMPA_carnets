package util;

import java.util.Arrays;

public class Util {

	public static String conversionHTML(String palabra) {
		
		String[] caracteresMalos = {"ñ","Ñ","à","á","À","Á","è","é","È","É","ì","í","Ì","Í","ò","ó","Ò","Ó","ù","ú","Ù","Ú","¿"};
        String[] caracteresBuenos = {"&ntilde;","&Ntilde;","a","&aacute;","A","&Aacute;","e","&eacute;","E","&Eacute;","i","&iacute;","I","&Iacute;","o","&oacute;","O","&Oacute;","u","&uacute;","U","&Uacute;","&iquest;"};

        for (String letraMala : caracteresMalos) {
            if(palabra.contains(letraMala)){
                palabra = palabra.replace(letraMala,caracteresBuenos[Arrays.asList(caracteresMalos).indexOf(letraMala)]);
            }
        }

        return palabra;
	}
}
