package acciones;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class EscribirSobreImagen {

	public void rellenarCarnet(List<Map<String,String>> datos, String rutaImagen, String curso, String validoHasta) {

		try {

			BufferedImage img = ImageIO.read(new File(rutaImagen));
			int w = img.getWidth();
			int h = img.getHeight();

			BufferedImage bufferedImage = new BufferedImage(
		            w, h, BufferedImage.TYPE_3BYTE_BGR);
			//BufferedImage.TYPE_INT_ARGB
			Graphics2D g2d = bufferedImage.createGraphics();
			
			Font font = new Font("Arial", Font.PLAIN, 26);
			Hashtable<TextAttribute, Object> map =
		            new Hashtable<TextAttribute, Object>();

			// draw graphics
			g2d.drawImage(img, 0, 0, null);
			g2d.setFont(font);
			g2d.setColor(Color.BLUE);
			g2d.drawString(datos.get(1).get("FAMILIAS").toUpperCase(), 32, 207);
			g2d.drawString(datos.get(1).get("Nº SOCIO"), 32, 283);
			g2d.drawString(curso, 212, 283);
			g2d.drawString(validoHasta, 385, 283);

			map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			font = font.deriveFont(map);
			g2d.setFont(font);
			g2d.drawString("P", 569, 283);

			g2d.dispose();

			// Save as JPEG
			File file = new File(datos.get(0).get("Nº SOCIO") + ".jpg");
			ImageIO.write(bufferedImage, "jpg", file);

		} catch (IOException e) {
		}
	}

}
