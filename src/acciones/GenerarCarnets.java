package acciones;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import util.Comprobaciones;
import util.LocalLogger;
import util.Util;

public class GenerarCarnets {

	public Map<String, Integer> rellenarCarnet(List<Map<String, String>> datos, String rutaImagen,
			String carpetaCarnets, String curso, String validoHasta, Logger LOG) {

		LOG.info(LocalLogger.logIn("rellenarCarnet"));
		Map<String, Integer> salida = null;

		try {
			if (datos != null) {

				Graphics2D g2d = null;
				Font font = null;
				BufferedImage img = ImageIO.read(new File(rutaImagen));
				int width = img.getWidth();
				int height = img.getHeight();
				BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
				Hashtable<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();
				map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

				// verifico que exista el directorio donde voy a guardar los carnets. Si no
				// existe lo creo.
				String rutaCarnetsFinal = "";
				if (Comprobaciones.noEsNullNiBlanco(carpetaCarnets) && Comprobaciones.noEsNullNiBlanco(curso)) {
					Comprobaciones.verificarCrearDirectorio(carpetaCarnets + curso);
					rutaCarnetsFinal = carpetaCarnets + "\\" + curso + "\\";
				}

				int carnetsOK = 0;
				salida = new HashMap<String, Integer>();

				for (int i = 0; i < datos.size(); i++) {
					if (datos.get(i) != null && Comprobaciones.noEsNullNiBlanco(datos.get(i).get("FAMILIAS"))
							&& Comprobaciones.noEsNullNiBlanco(datos.get(i).get("Nº SOCIO"))) {

						g2d = bufferedImage.createGraphics();
						font = new Font("Arial", Font.PLAIN, 26);

						// para mejorar la calidad del texto
						g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
								RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
						g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
								RenderingHints.VALUE_FRACTIONALMETRICS_ON);

						g2d.drawImage(img, 0, 0, null);
						g2d.setColor(Color.BLUE);
						g2d.setFont(font);

						// escribo campo Familia
						g2d.drawString(datos.get(i).get("FAMILIAS").toUpperCase(), 32, 207);
						// escribo campo Nº Socio
						g2d.drawString(datos.get(i).get("Nº SOCIO"), 32, 283);
						// escribo campo Curso
						g2d.drawString(curso, 212, 283);
						// escribo campo Válido hasta
						g2d.drawString(validoHasta, 385, 283);

						// pongo subrayado
						font = font.deriveFont(map);
						g2d.setFont(font);

						// escribo la P del final
						g2d.drawString("P", 569, 283);

						// TODO DAMIAN probando código QR (definir bien webCodigoQR)
						String absolutePath = new File("").getAbsolutePath();
						String rutaImagenCodigoQR = absolutePath + "\\src\\archivos\\qr\\";
						String rutaVerificacion = Util.conversionHTMLSinAcentos("verificacion.php" 
						+ "?familia=" + datos.get(i).get("FAMILIAS").toUpperCase() 
						+ "&curso=" + curso 
						+ "&vencimiento=" + validoHasta);
						String extensionImagenCodigoQR = "png";
						int tamanioImagenCodigoQR = 150;
						Properties pr = new Properties();
						pr.load(new FileReader(
								"src/util/Constantes_" + Locale.getDefault().getLanguage() + ".properties"));
						GenerarCodigoQR.generarQR(pr.getProperty("urlAMPA") + rutaVerificacion, rutaImagenCodigoQR,
								datos.get(i).get("Nº SOCIO"), extensionImagenCodigoQR, tamanioImagenCodigoQR, LOG);
						g2d.drawImage(ImageIO.read(new File(
								rutaImagenCodigoQR + datos.get(i).get("Nº SOCIO") + "." + extensionImagenCodigoQR)),
								475, 25, null);

						g2d.dispose();

						// guardo como JPG (numero_de_socio.jpg)
						File file = new File(rutaCarnetsFinal + datos.get(i).get("Nº SOCIO") + ".jpg");
						ImageIO.write(bufferedImage, "jpg", file);
						carnetsOK++;
					} else {
						LOG.info(
								"Carnet de posición " + (i + 1) + " del listado no se puede generar - " + datos.get(i));
					}
				}
				salida.put("OK", carnetsOK);
				salida.put("KO", datos.size() - carnetsOK);
			}
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.info(LocalLogger.logError(sw.toString()));
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.info(LocalLogger.logError(sw.toString()));
		}

		LOG.info(LocalLogger.logOut("rellenarCarnet: " + salida));
		return salida;
	}

}
