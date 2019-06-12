package pantalla;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import acciones.LeerFicherosExcel;

public class PantallaAcceso extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldRutaBBDD;
	private JButton btnSeleccionar;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private List<Map<String, String>> lista;

	/**
	 * Create the frame.
	 */
	public PantallaAcceso() {
		
//		Toolkit monitor = Toolkit.getDefaultToolkit();
//		
//		Dimension screenSize = monitor.getScreenSize();
//		int anchoMonitor = screenSize.width;
//		int altoMonitor = screenSize.height;		
//		setSize(anchoMonitor/2,altoMonitor/2);
//		setLocation(anchoMonitor/4, altoMonitor/4);
//		
//		setTitle("AMPA - Creación y envío de carnets");
//		
//		Image icono = monitor.getImage("src/imagenes/Logo_AMPA.gif");
//		setIconImage(icono);
				
//		Lamina lamina = new Lamina(anchoMonitor, altoMonitor);
//		lamina.setBackground(new Color(170,170,170));
//		add(lamina);
		
		
		
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		
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
		
		JButton btnGenerarCarnets = new JButton("Generar carnets");
		btnGenerarCarnets.setBackground(new Color(153, 204, 153));
		btnGenerarCarnets.setForeground(Color.BLACK);
		
		JButton btnEnviarEmail = new JButton("Enviar email");
		
//		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addGap(144)
//							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
//									.addComponent(btnEnviarEmail, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
//									.addGroup(gl_contentPane.createSequentialGroup()
//										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
//											.addComponent(lblCurso, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//											.addComponent(lblRutaBBDD, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//											.addGroup(gl_contentPane.createSequentialGroup()
//												.addGap(18)
//												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
//											.addGroup(gl_contentPane.createSequentialGroup()
//												.addGap(18)
//												.addComponent(comboBox, 0, 145, Short.MAX_VALUE)))))
//								.addGroup(gl_contentPane.createSequentialGroup()
//									.addComponent(lblValidoHasta, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
//									.addGap(18)
//									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
//							.addGap(337))
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addGap(186)
//							.addComponent(btnGenerarCarnets, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)))
//					.addGap(180))
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addGap(76)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblRutaBBDD)
//						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(41)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
//						.addGroup(gl_contentPane.createSequentialGroup()
//							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
//								.addComponent(lblCurso)
//								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//							.addGap(47)
//							.addComponent(lblValidoHasta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
//						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(96)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addComponent(btnGenerarCarnets, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
//						.addComponent(btnEnviarEmail, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
//					.addContainerGap(135, Short.MAX_VALUE))
//		);
//		contentPane.setLayout(gl_contentPane);
		setLayout(new FlowLayout());
		add(lblRutaBBDD);
		add(textFieldRutaBBDD);
		add(btnSeleccionar);
		add(lblCurso);
		add(comboBox);
		add(lblValidoHasta);
		add(dateChooser);
		add(btnGenerarCarnets);
		add(btnEnviarEmail);
		
		
		btnSeleccionar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
            	
            	JFileChooser fc=new JFileChooser();
            	contentPane = new JPanel();
            	int seleccion=fc.showOpenDialog(contentPane);
            	if(seleccion==JFileChooser.APPROVE_OPTION){
            		File fichero=fc.getSelectedFile();
            		textFieldRutaBBDD.setText(fichero.getAbsolutePath());
            	}
 
            }
        });

		btnGenerarCarnets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeerFicherosExcel leerFicherosExcel = new LeerFicherosExcel();
				lista = leerFicherosExcel.leerExcel(textFieldRutaBBDD.getText());
			}
		});
		
		btnEnviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(lista);
			}
		});
	}
}
