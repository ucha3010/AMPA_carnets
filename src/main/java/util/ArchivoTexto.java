package main.java.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class ArchivoTexto {
	
	public static void crearYEscribirArchivoTexto(String nombre, String content, Logger LOG) {
	
		BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            fw = new FileWriter(nombre);
            bw = new BufferedWriter(fw);
            bw.write(content);

        } catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			LOG.info(LocalLogger.logError(sw.toString())); 
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
    			StringWriter sw = new StringWriter();
    			PrintWriter pw = new PrintWriter(sw);
    			ex.printStackTrace(pw);
    			LOG.info(LocalLogger.logError(sw.toString())); 
            }
        }
	}

}
