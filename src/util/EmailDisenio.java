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
                + "<p>" + "" + "</p>"
                + firma()
                + "</BODY></HTML>";
        //para agregar im&aacute;genes pongo dentro del body
        //<img src='cid:cidnombre' />
        //colocando en el campo cidnombre el nombre que quiera
        return mensaje;
    }
    
    private static String firma() {
    	
    	String cuatroEspaciosBlancos = "&nbsp;&nbsp;&nbsp;&nbsp;";
    	String firma = ""
    			+ "<p style=\"background:white\"><span style=\"font-size:9.5pt;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;;\r\n" + 
    			"color:#201F1E\">Un saludo,</span><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;\r\n" + 
    			"color:#201F1E\"><o:p></o:p></span></p>"
    			+ "<p style=\"background:white;-webkit-font-smoothing: antialiased;font-variant-ligatures: normal;\r\n" + 
    			"font-variant-caps: normal;orphans: 2;text-align:start;widows: 2;-webkit-text-stroke-width: 0px;\r\n" + 
    			"text-decoration-style: initial;text-decoration-color: initial;word-spacing:\r\n" + 
    			"0px\"><span style=\"font-size:9.5pt;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;;color:#201F1E\">AMPA\r\n" + 
    			"Colegio El Valle <span class=\"SpellE\">Sanchinarro</span></span><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;color:#201F1E\"><o:p></o:p></span></p>"
    			+ "<p style=\"mso-outline-level:1;background:white;-webkit-font-smoothing: antialiased;\r\n" + 
    			"font-variant-ligatures: normal;font-variant-caps: normal;orphans: 2;text-align:\r\n" + 
    			"start;widows: 2;-webkit-text-stroke-width: 0px;text-decoration-style: initial;\r\n" + 
    			"text-decoration-color: initial;word-spacing:0px\"><span style=\"font-size:9.5pt;\r\n" + 
    			"font-family:&quot;Arial&quot;,&quot;sans-serif&quot;;color:#201F1E\">Descarga la revista del AMPA\r\n" + 
    			"aqu&iacute;:&nbsp;</span><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;\r\n" + 
    			"color:#201F1E\"><a href=\"https://ampacolegioelvallesanchinarro.com/la-revista-del-ampa/\" target=\"_blank\" data-auth=\"NotApplicable\" style=\"-webkit-font-smoothing: antialiased\">https://ampacolegioelvallesanchinarro.com/la-revista-del-ampa/</a><o:p></o:p></span></p>"
    			+ "<p style=\"background:white;-webkit-font-smoothing: antialiased;font-variant-ligatures: normal;\r\n" + 
    			"font-variant-caps: normal;orphans: 2;text-align:start;widows: 2;-webkit-text-stroke-width: 0px;\r\n" + 
    			"text-decoration-style: initial;text-decoration-color: initial;word-spacing:\r\n" + 
    			"0px\"><span style=\"font-size:9.5pt;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;;color:#201F1E\">-\r\n" + 
    			"- - - - - - - - - - - - - - - - - - - - -</span><span style=\"font-size:9.5pt;\r\n" + 
    			"font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;color:#201F1E\"><o:p></o:p></span></p>"
    			+ "<p style=\"mso-outline-level:1;background:white;-webkit-font-smoothing: antialiased;\r\n" + 
    			"font-variant-ligatures: normal;font-variant-caps: normal;orphans: 2;text-align:\r\n" + 
    			"start;widows: 2;-webkit-text-stroke-width: 0px;text-decoration-style: initial;\r\n" + 
    			"text-decoration-color: initial;word-spacing:0px\"><span style=\"font-size:10.0pt;\r\n" + 
    			"font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;color:#201F1E\">S&Iacute;GUENOS EN LAS REDES\r\n" + 
    			"SOCIALES<o:p></o:p></span></p>"
    			
    			+ "<p style=\"background:white\"><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;\r\ncolor:#201F1E\">"
    			+ "<a href=\"http://ampacolegioelvallesanchinarro.com/\">"
    			+ "<img src=\"cid:logo_AMPA\" alt=\"Página AMPA\" height=\"36\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    	
    			+ "<a href=\"mailto:info@ampacolegioelvallesanchinarro.com\">"
    			+ "<img src=\"cid:logo_email\" alt=\"Facebook AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    	
    			+ "<a href=\"https://es-es.facebook.com/AMPAColegioElValleSanchinarro/\">"
    			+ "<img src=\"cid:logo_facebook\" alt=\"Facebook AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    			
    			+ "<a href=\"https://twitter.com/colegioselvalle?lang=es\">"
    			+ "<img src=\"cid:logo_twitter\" alt=\"Twitter AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    			
    			+ "<a href=\"https://www.instagram.com/p/BUMIiOeF-Gs/?hl=es\">"
    			+ "<img src=\"cid:logo_instagram\" alt=\"Instagram AMPA\" height=\"60\" width=\"60\"></a></span>" + cuatroEspaciosBlancos
    			
    			+ "<a href=\"https://www.youtube.com/channel/UCvL-WAhlcKFvATkLXHoWynQ\">"
    			+ "<img src=\"cid:logo_youtube\" alt=\"Youtube AMPA\" height=\"60\" width=\"60\"></a></span></p>"
    			
    			+ "<p style=\"background:white;-webkit-font-smoothing: antialiased;font-variant-ligatures: normal;\r\n" + 
    			"font-variant-caps: normal;orphans: 2;text-align:start;widows: 2;-webkit-text-stroke-width: 0px;\r\n" + 
    			"text-decoration-style: initial;text-decoration-color: initial;word-spacing:\r\n" + 
    			"0px\"><span style=\"font-size:10.0pt;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;;color:#201F1E\">La\r\n" + 
    			"Asociaci&oacute;n de Madres y Padres de Alumnos Colegio El Valle <span class=\"SpellE\">Sanchinarro</span>,\r\n" + 
    			"le informa de que los datos que nos facilite ser&aacute;n incorporados a nuestro\r\n" + 
    			"fichero, con la finalidad de organizar y realizar todas aquellas actuaciones\r\n" + 
    			"que sean necesarias para la adecuada comunicaci&oacute;n y consecuci&oacute;n de los fines de\r\n" + 
    			"la Asociaci&oacute;n con sus asociados,&nbsp;as&iacute;&nbsp;como para la gesti&oacute;n interna\r\n" + 
    			"asociaci&oacute;n-asociado.&nbsp;</span><span style=\"-webkit-font-smoothing: antialiased;\r\n" + 
    			"font-style:inherit;font-variant:inherit;font-weight:inherit;font-stretch: inherit;\r\n" + 
    			"font-size:inherit;line-height:inherit;color:inherit\"><span style=\"font-size:\r\n" + 
    			"9.5pt;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;;color:#201F1E\">Sus\r\n" + 
    			"datos&nbsp;ser&aacute;n&nbsp;cedidos a las Entidades Bancarias para el cobro de los\r\n" + 
    			"recibos.</span></span><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;\r\n" + 
    			"color:#201F1E\"><o:p></o:p></span></p>"
    			+ "<p style=\"background:white;-webkit-font-smoothing: antialiased;font-variant-ligatures: normal;\r\n" + 
    			"font-variant-caps: normal;orphans: 2;text-align:start;widows: 2;-webkit-text-stroke-width: 0px;\r\n" + 
    			"text-decoration-style: initial;text-decoration-color: initial;word-spacing:\r\n" + 
    			"0px\"><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;\r\n" + 
    			"color:#201F1E\">Este mensaje va dirigido, de manera exclusiva, a su destinatario\r\n" + 
    			"y puede contener informaci&oacute;n confidencial y sujeta al secreto profesional, cuya\r\n" + 
    			"divulgaci&oacute;n no est&aacute; permitida por Ley. En caso de haber recibido este mensaje por\r\n" + 
    			"error, le rogamos que de forma inmediata nos lo comunique mediante correo\r\n" + 
    			"electr&oacute;nico remitido a nuestra atenci&oacute;n y proceda a su eliminaci&oacute;n, as&iacute; como a\r\n" + 
    			"la de cualquier documento adjunto al mismo.&nbsp;<o:p></o:p></span></p>"
    			+ "<p style=\"background:white;-webkit-font-smoothing: antialiased;font-variant-ligatures: normal;\r\n" + 
    			"font-variant-caps: normal;orphans: 2;text-align:start;widows: 2;-webkit-text-stroke-width: 0px;\r\n" + 
    			"text-decoration-style: initial;text-decoration-color: initial;word-spacing:\r\n" + 
    			"0px\"><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;\r\n" + 
    			"color:#201F1E\">Asimismo, le comunicamos que la distribuci&oacute;n, copia o\r\n" + 
    			"utilizaci&oacute;n de este mensaje, o de cualquier documento adjunto al mismo,\r\n" + 
    			"cualquiera que fuera su finalidad, est&aacute;n prohibidas por la ley.<o:p></o:p></span></p>"
    			+ "<p style=\"background:white;-webkit-font-smoothing: antialiased;font-variant-ligatures: normal;\r\n" + 
    			"font-variant-caps: normal;orphans: 2;text-align:start;widows: 2;-webkit-text-stroke-width: 0px;\r\n" + 
    			"text-decoration-style: initial;text-decoration-color: initial;word-spacing:\r\n" + 
    			"0px\"><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;\r\n" + 
    			"color:#201F1E\">En aras del cumplimiento del Reglamento (UE) 2016/679 del\r\n" + 
    			"Parlamento Europeo y del Consejo, de 27 de Abril de 2016, puede ejercer los\r\n" + 
    			"derechos de acceso, rectificaci&oacute;n, cancelaci&oacute;n, limitaci&oacute;n, oposici&oacute;n y\r\n" + 
    			"portabilidad de manera gratuita mediante correo electr&oacute;nico a:&nbsp;</span>" +
    			"<span style=\"font-size:10.0pt;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;;color:#201F1E\">" +
    			"<a href=\"mailto:info@ampacolegioelvallesanchinarro.com\" data-auth=\"NotApplicable\" style=\"-webkit-font-smoothing: antialiased;font-style:inherit;font-variant:\r\n" + 
    			"inherit;font-weight:inherit;font-stretch: inherit;line-height:inherit\">info@ampacolegioelvallesanchinarro.com</a></span><span style=\"font-size:9.5pt;font-family:&quot;Segoe UI&quot;,&quot;sans-serif&quot;;color:#201F1E\">&nbsp;o\r\n" + 
    			"bien en la direcci&oacute;n: calle Ana de Austria 60, 28050 Madrid<o:p></o:p></span></p>";
    	
    	return firma;
    }
}
