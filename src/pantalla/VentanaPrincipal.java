package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import acciones.LeerFicherosExcel;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private int anchoVentana;
	private int altoVentana;
	private JPanel panelPrincipal;
	private JPanel panelListado;
	private JTextField textFieldRutaBBDD;
	private JButton btnSeleccionar;
	private JPanel contentPane;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private JButton btnGenerarCarnets;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnEnviarEmail;
	private List<Map<String, String>> lista;
	private JButton btnContinuar;
	private JButton btnCancelar;
	private LeerFicherosExcel leerFicherosExcel;
	private JButton seleccionarTodo;
	private JCheckBox chckbxSelectAll;
	private JPanel panelScroll;
	
	Properties p;

	public VentanaPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new Properties();
		try {
			p.load(new FileReader("src/util/Constantes_es.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		leerFicherosExcel = new LeerFicherosExcel();
	}

	private void inicializarVentana() {

		Toolkit monitor = Toolkit.getDefaultToolkit();

		// detecto la resolución del monitor donde se abre la aplicación
		Dimension screenSize = monitor.getScreenSize();
		anchoVentana = screenSize.width / 2;
		altoVentana = screenSize.height / 2;
		// centro la aplicación en el monitor
		setSize(anchoVentana, altoVentana);
		setLocation(anchoVentana / 2, altoVentana / 2);
		// título
		setTitle(p.getProperty("titulo"));
		// Icono con el logo
		Image icono = monitor.getImage(p.getProperty("ruta_logo"));
		setIconImage(icono);

	}

	private void inicializarPanelPrincipal() {

		JLabel lblRutaBBDD = new JLabel(p.getProperty("lblBBDD"));
		lblRutaBBDD.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		textFieldRutaBBDD = new JTextField();
		textFieldRutaBBDD.setEditable(false);
		textFieldRutaBBDD.setColumns(30);

		btnSeleccionar = new JButton(p.getProperty("btnSeleccionar"));

		JLabel lblCurso = new JLabel(p.getProperty("lblCurso"));
		lblCurso.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		comboBox = new JComboBox();
		comboBox.addItem("2018-2019");
		comboBox.addItem("2019-2020");
		comboBox.addItem("2020-2021");
		comboBox.addItem("2021-2022");

		JLabel lblValidoHasta = new JLabel(p.getProperty("lblValidoHasta"));
		lblValidoHasta.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		dateChooser = new JDateChooser();

		btnGenerarCarnets = new JButton(p.getProperty("btnGenerarCarnets"));
		btnGenerarCarnets.setBackground(new Color(153, 204, 153));
		btnGenerarCarnets.setForeground(Color.BLACK);

		btnEnviarEmail = new JButton(p.getProperty("btnGenerarEmail"));

		panelPrincipal.add(lblRutaBBDD);
		panelPrincipal.add(textFieldRutaBBDD);
		panelPrincipal.add(btnSeleccionar);
		panelPrincipal.add(lblCurso);
		panelPrincipal.add(comboBox);
		panelPrincipal.add(lblValidoHasta);
		panelPrincipal.add(dateChooser);
		panelPrincipal.add(btnGenerarCarnets);
		panelPrincipal.add(btnEnviarEmail);

		//Este checkbox es del otro panel pero se escucha en este por eso lo inicializo acá
		chckbxSelectAll = new JCheckBox(p.getProperty("chckbxSelectAll"));
//        chckbxSelectAll.setBounds(150, 29, 97, 23);
        chckbxSelectAll.setBackground(Color.WHITE);
        chckbxSelectAll.setFont(new Font("Verdana", Font.PLAIN, 15));

		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				contentPane = new JPanel();
				int seleccion = fc.showOpenDialog(contentPane);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					textFieldRutaBBDD.setText(fichero.getAbsolutePath());
				}

			}
		});

		btnGenerarCarnets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.setVisible(false);
				panelListado.setVisible(true);
				lista = leerFicherosExcel.leerExcel(textFieldRutaBBDD.getText());		

		        panelScroll.add(chckbxSelectAll);

				// ScrollPane for Table
				if(scrollPane == null)
				scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, anchoVentana - (anchoVentana / 5), altoVentana - (altoVentana / 10));
				panelScroll.add(scrollPane);

				// Table
				table = new JTable();
				scrollPane.setViewportView(table);

				// Model for Table
				DefaultTableModel model = new DefaultTableModel() {

					public Class<?> getColumnClass(int column) {
						switch (column) {
						case 0:
							return Boolean.class;
						case 1:
							return String.class;
						case 2:
							return String.class;
						case 3:
							return String.class;
						case 4:
							return String.class;
						case 5:
							return String.class;
						case 6:
							return String.class;
						default:
							return String.class;
						}
					}
				};
				
		        
				table.setModel(model);

				model.addColumn("");
				model.addColumn(p.getProperty("col1"));
				model.addColumn(p.getProperty("col2"));
				model.addColumn(p.getProperty("col3"));
				model.addColumn(p.getProperty("col4"));
				model.addColumn(p.getProperty("col5"));

				// Data Row
				if (lista != null) {
					for (int i = 0; i < lista.size(); i++) {
						model.addRow(new Object[0]);
						model.setValueAt(false, i, 0);
						model.setValueAt(lista.get(i).get("Nº SOCIO"), i, 1);
						model.setValueAt(lista.get(i).get("EMAIL"), i, 2);
						model.setValueAt(lista.get(i).get("FAMILIAS"), i, 3);
						model.setValueAt(lista.get(i).get("NOMBRE MADRE"), i, 4);
						model.setValueAt(lista.get(i).get("NOMBRE2 PADRE"), i, 5);
					}
				}
				
				
				
			}
		});

		btnEnviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.setVisible(false);
				panelListado.setVisible(true);
				lista = leerFicherosExcel.leerExcel(textFieldRutaBBDD.getText());
			}
		});
		
		chckbxSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSelectAll.isSelected()){

	                for(int i=0;i<table.getRowCount();i++)
	                table.getModel().setValueAt(true, i, 0);

	            }else{

	                for(int i=0;i<table.getRowCount();i++)
	                    table.getModel().setValueAt(false, i, 0);                   
	            }  
			}
		});

	}

	private void inicializarPanelListado() {

		btnContinuar = new JButton(p.getProperty("btnContinuar"));
		btnCancelar = new JButton(p.getProperty("btnCancelar"));
		
		panelListado.setLayout(new BorderLayout());
		
		panelScroll = new JPanel();
		panelListado.add(panelScroll, BorderLayout.CENTER);

		JPanel botones = new JPanel();
		botones.add(btnContinuar);
		botones.add(btnCancelar);
		panelListado.add(botones, BorderLayout.SOUTH);

		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.setVisible(true);
				panelListado.setVisible(false);
				lista = new ArrayList<>();
				Map<String, String> dato;
				for (int i = 0; i < table.getRowCount(); i++) {
					Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
					//String dataCol1 = table.getValueAt(i, 1).toString();
					if (chked) {
						dato = new HashMap<>();
						dato.put("Nº SOCIO", table.getValueAt(i, 1).toString());
						dato.put("EMAIL", table.getValueAt(i, 2).toString());
						lista.add(dato);
						//JOptionPane.showMessageDialog(null, dataCol1);
					}
				}

				System.out.println(lista);
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrincipal.setVisible(true);
				panelListado.setVisible(false);
			}
		});

	}

}
