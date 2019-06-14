package pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int anchoVentana;
	private int altoVentana;
	private JPanel panelPrincipal;
	private JPanel panelListado;
	private JTextField textFieldRutaBBDD;
	private JButton btnSeleccionar;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private JButton btnGenerarCarnets;
	private JButton btnEnviarEmail;
	private List<Map<String, String>> lista;
	private JButton btnContinuar;
	private JButton btnCancelar;

	public VentanaPrincipal() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarVentana();
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, anchoVentana, altoVentana);
		panelPrincipal.setVisible(true);
		panelListado = new JPanel();
		panelListado.setBounds(0, 0, anchoVentana, altoVentana);
		panelListado.setVisible(false);
		inicializarPanelPrincipal();
		inicializarPanelListado();
		add(panelPrincipal);
		add(panelListado);
		
	}	

	private void inicializarVentana() {
		
		Toolkit monitor = Toolkit.getDefaultToolkit();
		
		//detecto la resolución del monitor donde se abre la aplicación
		Dimension screenSize = monitor.getScreenSize();
		anchoVentana = screenSize.width/2;
		altoVentana = screenSize.height/2;
		//centro la aplicación en el monitor
		setSize(anchoVentana,altoVentana);
		setLocation(anchoVentana/2, altoVentana/2);
		//título
		setTitle("AMPA - Creación y envío de carnets");
		//Icono con el logo
		Image icono = monitor.getImage("src/imagenes/Logo_AMPA.gif");
		setIconImage(icono);
		
	}
	
	private void inicializarPanelPrincipal() {

		JLabel lblRutaBBDD = new JLabel("Ruta Base de Datos");
		lblRutaBBDD.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		textFieldRutaBBDD = new JTextField();
		textFieldRutaBBDD.setEditable(false);
		textFieldRutaBBDD.setColumns(30);
		
		btnSeleccionar = new JButton("Examinar");
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		comboBox = new JComboBox();
		comboBox.addItem("2018-2019");
		comboBox.addItem("2019-2020");
		comboBox.addItem("2020-2021");
		comboBox.addItem("2021-2022");
		
		JLabel lblValidoHasta = new JLabel("Válido hasta");
		lblValidoHasta.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		dateChooser = new JDateChooser();
		
		btnGenerarCarnets = new JButton("Generar carnets");
		btnGenerarCarnets.setBackground(new Color(153, 204, 153));
		btnGenerarCarnets.setForeground(Color.BLACK);
		
		btnEnviarEmail = new JButton("Enviar email");
		
//		panelPrincipal.setLayout(new FlowLayout());
		panelPrincipal.add(lblRutaBBDD);
		panelPrincipal.add(textFieldRutaBBDD);
		panelPrincipal.add(btnSeleccionar);
		panelPrincipal.add(lblCurso);
		panelPrincipal.add(comboBox);
		panelPrincipal.add(lblValidoHasta);
		panelPrincipal.add(dateChooser);
		panelPrincipal.add(btnGenerarCarnets);
		panelPrincipal.add(btnEnviarEmail);
	
		btnGenerarCarnets.addActionListener(new ActionListener(){
	        public void actionPerformed (ActionEvent e){
				panelPrincipal.setVisible(false);
				panelListado.setVisible(true);
				System.out.println("Debería mostrar el panel listado");	        	
	        }
		});

		btnEnviarEmail.addActionListener(new ActionListener(){
	        public void actionPerformed (ActionEvent e){
				panelPrincipal.setVisible(false);
				panelListado.setVisible(true);
				System.out.println("Debería mostrar el panel listado");	        	
	        }
		});
		
	}
	
	private void inicializarPanelListado() {

		btnContinuar = new JButton("Continuar");
		btnCancelar = new JButton("Cancelar");
		
//		panelListado.setLayout(new FlowLayout());
		panelListado.add(btnContinuar);
		panelListado.add(btnCancelar);
	
		btnContinuar.addActionListener(new ActionListener(){
	        public void actionPerformed (ActionEvent e){
				panelPrincipal.setVisible(true);
				panelListado.setVisible(false);
				System.out.println("Debería mostrar el panel principal");	        	
	        }
		});

		btnCancelar.addActionListener(new ActionListener(){
	        public void actionPerformed (ActionEvent e){
				panelPrincipal.setVisible(true);
				panelListado.setVisible(false);
				System.out.println("Debería mostrar el panel principal");	        	
	        }
		});
		
	}

}




















//class Lamina extends JPanel{
//	
//	private static final long serialVersionUID = 1L;
//	private int alto;
//	private int ancho;
//	
//	JButton btnGenerarCarnets = new JButton("Generar carnets");
//	JButton btnEnviarEmail = new JButton("Enviar email");
//	
//	public Lamina(int ancho, int alto) {
//		// en ancho y alto tendré las dimensiones de la ventana
//		this.alto = alto/2;
//		this.ancho = ancho/2;
//
//		add(btnGenerarCarnets);
//		add(btnEnviarEmail);
//		
//		EscuchaBotones amarillo = new EscuchaBotones(Color.YELLOW);
//		EscuchaBotones verde = new EscuchaBotones(Color.GREEN);
//		
//		btnGenerarCarnets.addActionListener(amarillo);
//		btnEnviarEmail.addActionListener(verde);
//	}
//	
//	public void paintComponent(Graphics g) {
//		
//		super.paintComponent(g);
//		
//		g.drawString("Un texto de rpueba", ancho/20, alto/20);
//		
//		Graphics2D graphics2d = (Graphics2D) g;
//		Rectangle2D rectangle2d = new Rectangle2D.Double( 0, alto * 0.7, ancho, alto * 0.3);
//		graphics2d.setPaint(new Color(200,250,200));
//		graphics2d.fill(rectangle2d);
//		
//	}
//	
//
//
//	private class EscuchaBotones implements ActionListener{
//		
//		private Color colorDeFondo;
//		
//		public EscuchaBotones(Color c) {
//
//			colorDeFondo = c;
//			
//		}
//	
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//			setBackground(colorDeFondo);
//			
//		}
//	}
//}
