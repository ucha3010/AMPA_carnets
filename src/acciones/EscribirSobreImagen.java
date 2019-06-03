package acciones;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class EscribirSobreImagen {

	public void rellenarCarnet(List<Map<String,String>> datos, String rutaImagen) {

		try {

			BufferedImage img = ImageIO.read(new File(rutaImagen));
			int width = img.getWidth();
			int height = img.getHeight();

			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = bufferedImage.createGraphics();

			// draw graphics
			g2d.drawImage(img, 0, 0, null);
			g2d.drawString(datos.get(0).get("Nº SOCIO"), 0, 0);
			g2d.drawString(datos.get(0).get("FAMILIAS"), 10, 0);

			g2d.dispose();

			// Save as JPEG
			File file = new File(datos.get(0).get("Nº SOCIO") + ".jpg");
			ImageIO.write(bufferedImage, "jpg", file);

		} catch (IOException e) {
		}
	}

}
