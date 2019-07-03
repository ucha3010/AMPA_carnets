package util;

import java.util.Arrays;

public class Util {

	public static String conversionHTML(String palabra) {
		
		String[] caracteresMalos = {"ñ","Ñ","à","á","À","Á","è","é","È","É","ì","í","Ì","Í","ò","ó","Ò","Ó","ù","ú","Ù","Ú","¿","ü","Ü"};
        String[] caracteresBuenos = {"&ntilde;","&Ntilde;","a","&aacute;","A","&Aacute;","e","&eacute;","E","&Eacute;","i","&iacute;","I","&Iacute;","o","&oacute;","O","&Oacute;","u","&uacute;","U","&Uacute;","&iquest;","&uuml;","&Uuml;"};

        for (String letraMala : caracteresMalos) {
            if(palabra.contains(letraMala)){
                palabra = palabra.replace(letraMala,caracteresBuenos[Arrays.asList(caracteresMalos).indexOf(letraMala)]);
            }
        }

        return palabra;
	}

	public static String conversionHTMLSinAcentos(String palabra) {
		
		String[] caracteresMalos = {"ñ","Ñ","à","á","À","Á","è","é","È","É","ì","í","Ì","Í","ò","ó","Ò","Ó","ù","ú","Ù","Ú","ü","Ü"};
        String[] caracteresBuenos = {"n","N","a","a","A","A","e","e","E","E","i","i","I","I","o","o","O","O","u","u","U","U","u","U"};

        for (String letraMala : caracteresMalos) {
            if(palabra.contains(letraMala)){
                palabra = palabra.replace(letraMala,caracteresBuenos[Arrays.asList(caracteresMalos).indexOf(letraMala)]);
            }
        }

        return palabra;
	}
}
