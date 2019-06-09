package pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
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
		lamina.setBackground(new Color(170,170,170));
		add(lamina);
		
	}

}

class Lamina extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int alto;
	private int ancho;
	
	JButton btnGenerarCarnets = new JButton("Generar carnets");
	JButton btnEnviarEmail = new JButton("Enviar email");
	
	public Lamina(int ancho, int alto) {
		// en ancho y alto tendré las dimensiones de la ventana
		this.alto = alto/2;
		this.ancho = ancho/2;

		add(btnGenerarCarnets);
		add(btnEnviarEmail);
		
		EscuchaBotones amarillo = new EscuchaBotones(Color.YELLOW);
		EscuchaBotones verde = new EscuchaBotones(Color.GREEN);
		
		btnGenerarCarnets.addActionListener(amarillo);
		btnEnviarEmail.addActionListener(verde);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawString("Un texto de rpueba", ancho/20, alto/20);
		
		Graphics2D graphics2d = (Graphics2D) g;
		Rectangle2D rectangle2d = new Rectangle2D.Double( 0, alto * 0.7, ancho, alto * 0.3);
		graphics2d.setPaint(new Color(200,250,200));
		graphics2d.fill(rectangle2d);
		
	}
	


	private class EscuchaBotones implements ActionListener{
		
		private Color colorDeFondo;
		
		public EscuchaBotones(Color c) {

			colorDeFondo = c;
			
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			setBackground(colorDeFondo);
			
		}
	}
}
