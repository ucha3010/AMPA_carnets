package principal;

import java.io.File;
import java.util.List;
import java.util.Map;

import acciones.EscribirSobreImagen;
import acciones.LeerFicherosExcel;

public class AMPAcarnets {
	
	public static void main(String[] args) {
			String nombreArchivoXML = "BD Socios curso - 1819-Muestra.xlsx";
			String archivoCarnet = "carnet18-19.png";
//			String archivoAri = "Ari.png";
	        String absolutePath = new File("").getAbsolutePath();
	        String absolutePathImegenes = absolutePath + "\\src\\imagenes\\";
	        String absolutePathArchivos = absolutePath + "\\src\\archivos\\";
	        String curso = "2018-2019";
	        String validoHasta = "01/09/2019";
	        
	        //Leer archivo XLSX
			LeerFicherosExcel lfe = new LeerFicherosExcel();
			List<Map<String, String>> leerExcel = lfe.leerExcel(absolutePathArchivos + nombreArchivoXML);
			System.out.println();
			
			EscribirSobreImagen esi = new EscribirSobreImagen();
			esi.rellenarCarnet(leerExcel, absolutePathImegenes + archivoCarnet, curso, validoHasta);

			//Generar archivo PDF
//			String ubicacionDondeDejarArchivoPDF = "C:\\Users\\Damian\\Desktop\\workspace-sts\\GeneratePDFFileIText.pdf";
//	        GenerarPDF generatePDFFileIText = new GenerarPDF();
//	        generatePDFFileIText.createPDF(new File(ubicacionDondeDejarArchivoPDF), absolutePathImegenes + archivoCarnet);
	}

}
