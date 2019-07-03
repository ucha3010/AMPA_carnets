package util;

public class EmailDisenio {
    
    public static String disenioCarnets(String familia, String file){
        String mensaje = "<HTML><BODY>"
                + "<p>Estimada familia " + Util.conversionHTML(familia) + "</p>"
                + "<p>" + "Desde el AMPA hemos preparado unos nuevos <b>carnets de socios</b>.  Cada familia asociada dispondr&aacute; de un carnet en <u>formato digital</u>. " + "</p>"
                + "<p>" + "<b>&iquest;Para qu&eacute; sirven los carnets?</b>" + "</p>"
                + "<p>" + "Os identificar&aacute; como familia asociada al AMPA del colegio en futuras actividades, por lo que se os podr&aacute; requerir al entrar/participar en las mismas." + "</p>"
                + "<p>" + "<b>Otras utilidades</b>" + "</p>"
                + "<p>" + "Desde el AMPA estamos llegando a <b>acuerdos con diferentes empresas/comercios de la zona</b> que no ofrecen <b>descuentos a las familias asociadas</b>. De esta manera fomentamos el comercio de cercan&iacute;a y adem&aacute;s los socios nos beneficiamos de sus ofertas y promociones." + "</p>"
                + "<p>" + "En el &Aacute;rea de socios de la web iremos publicando poco a poco informaci&oacute;n de los acuerdos: empresa/comercio, porcentaje de descuento o promoci&oacute;n que nos ofrecen. Los carnets llevan unos c&oacute;digos QR de verificaci&oacute;n y adem&aacute;s podr&aacute; ser requerido, por el comercio/empresa, el DNI del poseedor del mismo." + "</p>"
                + "<p>" + "<b>Importante</b>" + "</p>"
                + "<p>" + "Esta iniciativa est&aacute; dirigida a socios del AMPA. Asociarse cuesta s&oacute;lo 20&euro; al a&ntilde;o. <u>Estar&iacute;amos encantados de recibir nuevas familias socias</u>. Por favor, no utilic&eacute;is el carnet de socio de manera fraudulenta cuando la cuota anual es m&iacute;nima para cualquier familia del colegio. Fomentemos entre todos nuevas altas al AMPA que nos permitan hacer m&aacute;s actividades, y que el trabajo del AMPA beneficie expresamente a las familias asociadas." + "</p>"
                + "<p>" + "Si ten&eacute;is alg&uacute;n problema con el &aacute;rea de socios no dud&eacute;is en decirnos." + "</p>"
                + "<br/>"
                + firma()
                + "</BODY></HTML>";
        return mensaje;
    }
    
    private static String firma() {
    	
    	String cuatroEspaciosBlancos = "&nbsp;&nbsp;&nbsp;&nbsp;";
    	String firma = ""
    			+ "<p>Un saludo,</p>"
    			+ "<p>AMPA Colegio El Valle Sanchinarro</p>"
    			+ "<br/"
    			+ "<p>Descarga la revista del AMPA:&nbsp;<span style=\"font-size:11.0pt;color:#00008F\">"
    			+ "<a href=\"https://ampacolegioelvallesanchinarro.com/la-revista-del-ampa/\" target=\"_blank\" >pinchando aqu&iacute;</a></span></p>"
    			+ "<p>- - - - - - - - - - - - - - - - - - - - -</p>"
    			+ "<p>S&Iacute;GUENOS EN LAS REDES SOCIALES</p>"
    			
    			+ "<p style=\"background:white\">"
    			+ "<a href=\"http://ampacolegioelvallesanchinarro.com/\">"
    			+ "<img src=\"cid:logo_AMPA\" alt=\"P&aacute;gina AMPA\" height=\"36\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    	
    			+ "<a href=\"mailto:info@ampacolegioelvallesanchinarro.com\">"
    			+ "<img src=\"cid:logo_email\" alt=\"Email AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    	
    			+ "<a href=\"https://es-es.facebook.com/AMPAColegioElValleSanchinarro/\">"
    			+ "<img src=\"cid:logo_facebook\" alt=\"Facebook AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    			
    			+ "<a href=\"https://twitter.com/colegioselvalle?lang=es\">"
    			+ "<img src=\"cid:logo_twitter\" alt=\"Twitter AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    			
    			+ "<a href=\"https://www.instagram.com/p/BUMIiOeF-Gs/?hl=es\">"
    			+ "<img src=\"cid:logo_instagram\" alt=\"Instagram AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    			
    			+ "<a href=\"https://www.youtube.com/channel/UCvL-WAhlcKFvATkLXHoWynQ\">"
    			+ "<img src=\"cid:logo_youtube\" alt=\"Youtube AMPA\" height=\"60\" width=\"60\"></a></p>"
    			
    			+ "<p style=\"font-size:9.0pt;font-family: Arial, sans-serif;text-align: justify;color:#201F1E\">"
    			+ "La Asociaci&oacute;n de Madres y Padres de Alumnos Colegio El Valle Sanchinarro "
    			+ "le informa de que los datos que nos facilite ser&aacute;n incorporados a nuestro "
    			+ "fichero, con la finalidad de organizar y realizar todas aquellas actuaciones "
    			+ "que sean necesarias para la adecuada comunicaci&oacute;n y consecuci&oacute;n de los fines de "
    			+ "la Asociaci&oacute;n con sus asociados, as&iacute; como para la gesti&oacute;n interna "
    			+ "asociaci&oacute;n-asociado. Sus datos ser&aacute;n cedidos a las Entidades Bancarias para el cobro de los recibos."
    			+ "</p>"

    			+ "<p style=\"font-size:9.0pt;font-family: Arial, sans-serif;text-align: justify;color:#201F1E\">"
    			+ "Este mensaje va dirigido, de manera exclusiva, a su destinatario "
    			+ "y puede contener informaci&oacute;n confidencial y sujeta al secreto profesional, cuya "
    			+ "divulgaci&oacute;n no est&aacute; permitida por Ley. En caso de haber recibido este mensaje por "
    			+ "error, le rogamos que de forma inmediata nos lo comunique mediante correo "
    			+ "electr&oacute;nico remitido a nuestra atenci&oacute;n y proceda a su eliminaci&oacute;n, as&iacute; como a "
    			+ "la de cualquier documento adjunto al mismo."
    			+ "</p>"

    			+ "<p style=\"font-size:9.0pt;font-family: Arial, sans-serif;text-align: justify;color:#201F1E\">"
    			+ "Asimismo, le comunicamos que la distribuci&oacute;n, copia o "
    			+ "utilizaci&oacute;n de este mensaje, o de cualquier documento adjunto al mismo, "
    			+ "cualquiera que fuera su finalidad, est&aacute;n prohibidas por la ley."
    			+ "</p>"

    			+ "<p style=\"font-size:9.0pt;font-family: Arial, sans-serif;text-align: justify;color:#201F1E\">"
    			+ "En aras del cumplimiento del Reglamento (UE) 2016/679 del "
    			+ "Parlamento Europeo y del Consejo, de 27 de Abril de 2016, puede ejercer los "
    			+ "derechos de acceso, rectificaci&oacute;n, cancelaci&oacute;n, limitaci&oacute;n, oposici&oacute;n y "
    			+ "portabilidad de manera gratuita mediante correo electr&oacute;nico a: "
    			+ "<span style=\"font-size:10.0pt;font-family: Arial;, sans-serif;color:#201F1E\">"
    			+ "<a href=\"mailto:info@ampacolegioelvallesanchinarro.com\">info@ampacolegioelvallesanchinarro.com</a>"
    			+ "</span>"
    			+ " o bien en la direcci&oacute;n: calle Ana de Austria 60, 28050 Madrid"
    			+ "</p>";
    	
    	return firma;
    }
}
