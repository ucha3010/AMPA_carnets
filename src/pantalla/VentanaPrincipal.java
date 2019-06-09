package pantalla;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaPrincipal() {
		
		Toolkit monitor = Toolkit.getDefaultToolkit();
		
		Dimension screenSize = monitor.getScreenSize();
		int anchoMonitor = screenSize.width;
		int altoMonitor = screenSize.height;		
		setSize(anchoMonitor/2,altoMonitor/2);
		setLocation(anchoMonitor/4, altoMonitor/4);
		
		setTitle("AMPA - Creación y envío de carnets");
		
		Image icono = monitor.getImage("src/imagenes/Logo_AMPA.gif");
		setIconImage(icono);
		
		Lamina lamina = new Lamina(anchoMonitor, altoMonitor);
		add(lamina);
		
	}

}

class Lamina extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int alto;
	private int ancho;
	
	public Lamina(int ancho, int alto) {
		this.alto = alto;
		this.ancho = ancho;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawString("Un texto de rpueba", ancho/20, alto/20);
		
	}
}
