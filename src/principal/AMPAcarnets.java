package principal;

import java.io.File;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import acciones.EnviarEmailConCarnet;
import acciones.GenerarCarnets;
import acciones.LeerFicherosExcel;
import pantalla.VentanaPrincipal;

public class AMPAcarnets {

	public static void main(String[] args) {
		String nombreArchivoXML = "BD Socios curso - 1819-Muestra.xlsx";
		String archivoCarnet = "carnet18-19.png";
		// String archivoAri = "Ari.png";
		String absolutePath = new File("").getAbsolutePath();
		String absolutePathImegenes = absolutePath + "\\src\\imagenes\\";
		String absolutePathArchivos = absolutePath + "\\src\\archivos\\";
		String curso = "2018-2019";
		String validoHasta = "01/09/2019";
		String carpetaCarnets = "C:\\AMPA\\Carnets\\";
		
		// Pantalla de visualización
		try {
			Logger LOG = Logger.getLogger(VentanaPrincipal.class.getName());
			FileHandler fh = new FileHandler(absolutePath + "//src//archivos//logs.log", 10240, 10, true);
			LOG.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(Locale.getDefault().getLanguage(), LOG);
			ventanaPrincipal.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Leer archivo XLSX
		LeerFicherosExcel leerFicherosExcel = new LeerFicherosExcel();
//		List<Map<String, String>> leerExcel = leerFicherosExcel.leerExcel(absolutePathArchivos + nombreArchivoXML);

		// Generar carnets
		GenerarCarnets escribirSobreImagen = new GenerarCarnets();
//		escribirSobreImagen.rellenarCarnet(leerExcel, absolutePathImegenes + archivoCarnet, carpetaCarnets, curso, validoHasta);
		
		// Enviar emails
		EnviarEmailConCarnet enviarEmailConCarnet = new EnviarEmailConCarnet();
//		enviarEmailConCarnet.enviarEmail(leerExcel, carpetaCarnets, curso);

		// Generar archivo PDF
		// String ubicacionDondeDejarArchivoPDF = "C:\\Users\\Damian\\Desktop\\workspace-sts\\GeneratePDFFileIText.pdf";
		// GenerarPDF generatePDFFileIText = new GenerarPDF();
		// generatePDFFileIText.createPDF(new File(ubicacionDondeDejarArchivoPDF), absolutePathImegenes +
		// archivoCarnet);
	}

}
