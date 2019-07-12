package main.java.acciones;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/*
<!-- https://mvnrepository.com/artifact/com.google.zxing/core -->
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>2.0</version>
</dependency>
 * */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import main.java.util.LocalLogger;

public class GenerarCodigoQR {

	public static BufferedImage generarQR(String direccionDestino, String rutaGuardado, String nombreArchivo,
			String formatoImagen, int tamanio, Logger LOG) {
		try {
			/*
			 * direccionDestino: la dirección web que debe abrir el código QR
			 * rutaGuardado: la dirección en la compu donde se guardará el archivo con la imagen del código QR
			 * nombreArchivo: nombre del archivo
			 * formatoImagen: el formato de la imagen (png por ejemplo)
			 * tamanio: el tamaño de la imagen que será un cuadrado
			 * */

			LOG.info(LocalLogger.logIn("generarQR: "+direccionDestino+" - "+rutaGuardado+" - "+nombreArchivo+" - "+formatoImagen+" - "+tamanio));
			QRCodeWriter qrcode = new QRCodeWriter();
			BitMatrix matrix = qrcode.encode(direccionDestino, BarcodeFormat.QR_CODE, tamanio, tamanio);
			File qrFile = new File(rutaGuardado + nombreArchivo + "." + formatoImagen);
			int matrixWidth = matrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			graphics.setColor(Color.BLACK);

			for (int b = 0; b < matrixWidth; b++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (matrix.get(b, j)) {
						graphics.fillRect(b, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, formatoImagen, qrFile);
			BufferedImage bufferedImage =  ImageIO.read( qrFile);
			LOG.info(LocalLogger.logOut("generarQR: " + bufferedImage));
			return bufferedImage;
		} catch (WriterException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.info(LocalLogger.logError(sw.toString()));
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.info(LocalLogger.logError(sw.toString()));
		}
		return null;
	}
}
