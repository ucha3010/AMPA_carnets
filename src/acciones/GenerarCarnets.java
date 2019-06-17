package acciones;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import util.Comprobaciones;
import util.Logger;

public class GenerarCarnets {

	public Map<String, Integer> rellenarCarnet(List<Map<String, String>> datos, String rutaImagen, String carpetaCarnets, String curso,
			String validoHasta) {
		
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

				// verifico que exista el directorio donde voy a guardar los carnets. Si no existe lo creo.
				String rutaCarnetsFinal = "";
				if(Comprobaciones.noEsNullNiBlanco(carpetaCarnets) && Comprobaciones.noEsNullNiBlanco(curso)){
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

						g2d.dispose();

						// guardo como JPG (numero_de_socio.jpg)
						File file = new File(
								rutaCarnetsFinal + datos.get(i).get("Nº SOCIO") + ".jpg");
						ImageIO.write(bufferedImage, "jpg", file);
						carnetsOK++;
					} else {
						System.out.println(Logger.log(this.getClass().getName(), "Carnet de posición " + (i+1) + " del listado no se puede generar"));
					}
				}
				salida.put("OK", carnetsOK);
				salida.put("KO", datos.size() - carnetsOK);
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return salida;
	}

}