package acciones;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.Hora;
import util.Logger;

public class LeerFicherosExcel {

	/**
	 * pom.xml 
	 * <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
	 * <dependency> <groupId>org.apache.poi</groupId> <artifactId>poi</artifactId>
	 * <version>3.16</version> </dependency> 
	 * <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml --> <dependency>
	 * <groupId>org.apache.poi</groupId> <artifactId>poi-ooxml</artifactId>
	 * <version>3.16</version> </dependency> <!--
	 * https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml-schemas -->
	 * <dependency> <groupId>org.apache.poi</groupId>
	 * <artifactId>poi-ooxml-schemas</artifactId> <version>3.16</version>
	 * </dependency> <!--
	 * https://mvnrepository.com/artifact/org.apache.xmlbeans/xmlbeans -->
	 * <dependency> <groupId>org.apache.xmlbeans</groupId>
	 * <artifactId>xmlbeans</artifactId> <version>2.6.0</version> </dependency>
	 */

	public List<Map<String, String>> leerExcel(String rutaArchivo) {

		List<Map<String, String>> listaDatos = new ArrayList<>();

		try {
			FileInputStream file = new FileInputStream(new File(rutaArchivo));
			Map<String, String> datos = null;

			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);

			// obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);

			XSSFRow columna = sheet.getRow(0);
			String[] cabeceras = new String[columna.getLastCellNum()];

			// obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();

			Row row;
			boolean guardoDatos = false;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				datos = new HashMap<>();
				row = rowIterator.next();
				// se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				// se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en espec�fico y se la imprime
					cell = cellIterator.next();
					if (guardoDatos) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							datos.put(cabeceras[cell.getColumnIndex()], cell.getStringCellValue());
						} else if (DateUtil.isCellDateFormatted(cell)) {
							if (cell.getDateCellValue() != null) {
								datos.put(cabeceras[cell.getColumnIndex()], cell.getDateCellValue().toString());
							} else {
								datos.put(cabeceras[cell.getColumnIndex()], "");
							}
						} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
							BigDecimal number = new BigDecimal(String.valueOf(cell.getNumericCellValue()));
							datos.put(cabeceras[cell.getColumnIndex()], number.longValue() + "");
						}
					} else if (!guardoDatos) {
						if (cell.getCellTypeEnum() == CellType.STRING) {
							cabeceras[cell.getColumnIndex()] = cell.getStringCellValue();
						}
						// else if (cell.getCellTypeEnum() == CellType.NUMERIC) { }
					}
				}
				if (guardoDatos) {
					listaDatos.add(datos);
				} else {
					guardoDatos = true;
				}
			}
			worbook.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

		System.out.println(Logger.log(this.getClass().getName(), listaDatos.toString()));
		return listaDatos;
	}
}