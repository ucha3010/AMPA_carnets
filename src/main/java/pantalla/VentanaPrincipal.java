package main.java.pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import main.java.acciones.GenerarCarnets;
import main.java.acciones.LeerFicherosExcel;
import main.java.util.Comprobaciones;
import main.java.util.Email;
import main.java.util.LocalLogger;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private int anchoVentana;
	private int altoVentana;
	private int altoFila;
	private int margenIzquierdo;
	private JPanel panelPrincipal;
	private JPanel panelListado;
	private JTextField textFieldRutaBBDD;
	private JButton btnSeleccionar;
	private JLabel lblRutaBBDDError;
	private JPanel contentPane;
	private String[] cursos;
	private JComboBox<String> comboCursos;
	private JLabel lblCursoError;
	private Date date;
	private JLabel lblDateChooserError;
	private JButton btnGenerarCarnets;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnEnviarEmail;
	private List<Map<String, String>> lista;
	private JButton btnContinuar;
	private JButton btnCancelar;
	private LeerFicherosExcel leerFicherosExcel;
	private JCheckBox chckbxSelectAll;
	private JPanel panelScroll;
	private String vieneDe;
	private JLabel lblCarnetsGenerados;
	private JLabel lblCarnetsEnviados;
	private JLabel lblError;
	private String absolutePath = new File("").getAbsolutePath();
	private Properties p;
	private Logger LOG;
	private String[] extn;
	private boolean listadoVacio;
	private boolean noTieneColumnaDelCursoSeleccionado;
	private JLabel lblErrorLecturaFicheroExcel;

	public VentanaPrincipal(Properties p, Logger LOG) throws Exception {

		this.p = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.LOG = LOG;
		LOG.info("Acceso al programa");
		inicializarVentana();
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, anchoVentana, altoVentana);
		panelPrincipal.setVisible(true);
		panelPrincipal.setLayout(null);
		panelListado = new JPanel();
		panelListado.setBounds(0, 0, anchoVentana, altoVentana);
		panelListado.setVisible(false);
		altoFila = altoVentana / 20;
		margenIzquierdo = anchoVentana / 32;
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
		vieneDe = new String();

	}

	private void inicializarPanelPrincipal() {

		LOG.info(LocalLogger.logIn());
		JLabel lblRutaBBDD = new JLabel(p.getProperty("lblBBDD"));
		lblRutaBBDD.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRutaBBDD.setBounds(margenIzquierdo, altoFila, 115, 16);

		// ruta de la base de datos
		textFieldRutaBBDD = new JTextField();
		textFieldRutaBBDD.setEditable(false);
		textFieldRutaBBDD.setColumns(30);
		textFieldRutaBBDD.setBounds(margenIzquierdo + lblRutaBBDD.getWidth(), altoFila - 3, 350, 22);

		// botón para buscar archivo base de datos
		btnSeleccionar = new JButton(p.getProperty("btnSeleccionar"));
		btnSeleccionar.setBounds(margenIzquierdo + lblRutaBBDD.getWidth() + textFieldRutaBBDD.getWidth() + 5,
				altoFila - 7, 90, 30);

		// aviso de ruta vacía o errónea
		lblRutaBBDDError = new JLabel();
		lblRutaBBDDError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRutaBBDDError.setForeground(Color.RED);
		lblRutaBBDDError.setVisible(false);
		lblRutaBBDDError.setBounds(margenIzquierdo, altoFila * 2, anchoVentana - margenIzquierdo, 16);

		JLabel lblCurso = new JLabel(p.getProperty("lblCurso"));
		lblCurso.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCurso.setBounds(margenIzquierdo, altoFila * 4, 115, 16);

		// listado de cursos
		cursos = p.getProperty("listaCursos").split(",");

		// combo con cursos a seleccionar
		comboCursos = new JComboBox<String>();
		for (String curso : cursos) {
			comboCursos.addItem(curso);
		}
		comboCursos.setBounds(margenIzquierdo + lblCurso.getWidth(), altoFila * 4 - 6, 130, 25);

		// aviso de curso no seleccionado
		lblCursoError = new JLabel(p.getProperty("lblCursoError"));
		lblCursoError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCursoError.setForeground(Color.RED);
		lblCursoError.setVisible(false);
		lblCursoError.setBounds(margenIzquierdo, altoFila * 5, anchoVentana - margenIzquierdo, 16);

		JLabel lblValidoHasta = new JLabel(p.getProperty("lblValidoHasta"));
		lblValidoHasta.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblValidoHasta.setBounds(margenIzquierdo, altoFila * 7, 115, 16);

		// cuadro de selección de fecha de vencimiento
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(margenIzquierdo + lblValidoHasta.getWidth(), altoFila * 7 - 3, 100, 20);

		// aviso de fecha de vencimiento no seleccionada
		lblDateChooserError = new JLabel(p.getProperty("lblDateChooserError"));
		lblDateChooserError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDateChooserError.setForeground(Color.RED);
		lblDateChooserError.setVisible(false);
		lblDateChooserError.setBounds(margenIzquierdo, altoFila * 8, anchoVentana - margenIzquierdo, 16);

		// confirmación de generación de carnets
		lblCarnetsGenerados = new JLabel();
		lblCarnetsGenerados.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCarnetsGenerados.setForeground(Color.BLUE);
		lblCarnetsGenerados.setVisible(false);
		lblCarnetsGenerados.setBounds(margenIzquierdo, altoFila * 11, anchoVentana - margenIzquierdo, 16);

		// confirmación de carnets enviados
		lblCarnetsEnviados = new JLabel();
		lblCarnetsEnviados.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCarnetsEnviados.setForeground(Color.BLUE);
		lblCarnetsEnviados.setVisible(false);
		lblCarnetsEnviados.setBounds(margenIzquierdo, altoFila * 11, anchoVentana - margenIzquierdo, 16);

		lblError = new JLabel(p.getProperty("lblError"));
		lblError.setFont(new Font("Arial", Font.PLAIN, 16));
		lblError.setForeground(Color.RED);
		lblError.setVisible(false);
		lblError.setBounds(margenIzquierdo, altoFila * 12, anchoVentana - margenIzquierdo, 18);

		btnGenerarCarnets = new JButton(p.getProperty("btnGenerarCarnets"));
		btnGenerarCarnets.setBackground(new Color(153, 204, 153));
		btnGenerarCarnets.setForeground(Color.BLACK);
		btnGenerarCarnets.setBounds(anchoVentana / 8, altoFila * 15, 150, 30);

		btnEnviarEmail = new JButton(p.getProperty("btnGenerarEmail"));
		btnEnviarEmail.setBounds(5 * anchoVentana / 8, altoFila * 15, 150, 30);

		// imagenCargando = new JLabel();
		// imagenCargando.setIcon(new ImageIcon(absolutePath +
		// "\\src\\main\\resources\\imagenes\\cargando.gif"));
		// imagenCargando.setBounds(anchoVentana * 9/20, altoVentana * 3/4, 32, 32);

		panelPrincipal.add(lblRutaBBDD);
		panelPrincipal.add(textFieldRutaBBDD);
		panelPrincipal.add(btnSeleccionar);
		panelPrincipal.add(lblRutaBBDDError);
		panelPrincipal.add(lblCurso);
		panelPrincipal.add(comboCursos);
		panelPrincipal.add(lblCursoError);
		panelPrincipal.add(lblValidoHasta);
		panelPrincipal.add(dateChooser);
		panelPrincipal.add(lblDateChooserError);
		panelPrincipal.add(lblError);
		panelPrincipal.add(lblCarnetsGenerados);
		panelPrincipal.add(lblCarnetsEnviados);
		panelPrincipal.add(btnGenerarCarnets);
		panelPrincipal.add(btnEnviarEmail);

		// Este checkbox es del otro panel pero se escucha en este por eso lo inicializo
		// acá
		chckbxSelectAll = new JCheckBox(p.getProperty("chckbxSelectAll"));
		// chckbxSelectAll.setBounds(150, 29, 97, 23);
		chckbxSelectAll.setBackground(Color.WHITE);
		chckbxSelectAll.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		extn = p.getProperty("extensionBBDD").split(",");

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
				LOG.info(LocalLogger.logIn("btnGenerarCarnets.addActionListener"));
				date = dateChooser.getDate();
				ocultarAvisos();
				chckbxSelectAll.setSelected(false);
				boolean entro = false;
				listadoVacio = false;
				noTieneColumnaDelCursoSeleccionado = false;
				if (Comprobaciones.noEsNullNiBlanco(textFieldRutaBBDD.getText())
						&& Comprobaciones.verificarExtensionDeArchivo(textFieldRutaBBDD.getText(), extn)
						&& !comboCursos.getSelectedItem().toString().equals(cursos[0]) && date != null) {

					comienzaProceso(true);
					lista = leerFicherosExcel.leerExcel(textFieldRutaBBDD.getText(), lblErrorLecturaFicheroExcel, LOG);
					if(tieneColumnaDelCursoSeleccionado()) {
						if (cargarListado()) {
							panelPrincipal.setVisible(false);
							panelListado.setVisible(true);
							entro = true;
							vieneDe = "generarCarnets";
						} else {
							listadoVacio = true;
						}						
					} else {
						noTieneColumnaDelCursoSeleccionado = true;
					}
					comienzaProceso(false);
				}
				
				if (!entro) {
					avisosDeError(true);
				}
				LOG.info(LocalLogger.logOut("btnGenerarCarnets.addActionListener"));
			}
		});

		btnEnviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info(LocalLogger.logIn("btnEnviarEmail.addActionListener"));
				ocultarAvisos();
				chckbxSelectAll.setSelected(false);
				boolean entro = false;
				if (Comprobaciones.noEsNullNiBlanco(textFieldRutaBBDD.getText())
						&& Comprobaciones.verificarExtensionDeArchivo(textFieldRutaBBDD.getText(), extn)
						&& !comboCursos.getSelectedItem().toString().equals(cursos[0])) {

					comienzaProceso(true);
					lista = leerFicherosExcel.leerExcel(textFieldRutaBBDD.getText(), lblErrorLecturaFicheroExcel, LOG);
					if(tieneColumnaDelCursoSeleccionado()) {
						if (cargarListado()) {
							panelPrincipal.setVisible(false);
							panelListado.setVisible(true);
							entro = true;
							vieneDe = "enviarEmail";
						} else {
							listadoVacio = true;
						}
					} else {
						noTieneColumnaDelCursoSeleccionado = true;
					}
					comienzaProceso(false);
				}
				if (!entro) {
					avisosDeError(false);
				}
				LOG.info(LocalLogger.logOut("btnEnviarEmail.addActionListener"));
			}
		});

		chckbxSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxSelectAll.isSelected()) {

					for (int i = 0; i < table.getRowCount(); i++)
						table.getModel().setValueAt(true, i, 0);

				} else {

					for (int i = 0; i < table.getRowCount(); i++)
						table.getModel().setValueAt(false, i, 0);
				}
			}
		});
		LOG.info(LocalLogger.logOut());

	}

	private void inicializarPanelListado() {

		LOG.info(LocalLogger.logIn());
		btnContinuar = new JButton(p.getProperty("btnContinuar"));
		btnCancelar = new JButton(p.getProperty("btnCancelar"));

		// confirmación de generación de carnets
		lblErrorLecturaFicheroExcel = new JLabel();
		lblErrorLecturaFicheroExcel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblErrorLecturaFicheroExcel.setForeground(Color.RED);
		lblErrorLecturaFicheroExcel.setVisible(false);
		lblErrorLecturaFicheroExcel.setBounds(margenIzquierdo, altoFila * 11, anchoVentana - margenIzquierdo, 16);

		panelListado.setLayout(new BorderLayout());

		panelScroll = new JPanel();
		panelListado.add(panelScroll, BorderLayout.CENTER);

		JPanel botones = new JPanel();
		botones.add(btnContinuar);
		botones.add(btnCancelar);
		botones.add(lblErrorLecturaFicheroExcel);
		panelListado.add(botones, BorderLayout.SOUTH);

		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info(LocalLogger.logIn("btnContinuar.addActionListener"));
				comienzaProceso(true);
				ocultarAvisos();
				panelPrincipal.setVisible(true);
				panelListado.setVisible(false);
				lista = new ArrayList<>();
				Map<String, String> dato;
				LOG.info("table.getRowCount(): " + table.getRowCount());
				for (int i = 0; i < table.getRowCount(); i++) {
					// miro que, si se va a enviar email traiga número de socio e email
					// y si se va a generar carnet traiga número de socio
					if ((vieneDe.equals("enviarEmail")
							&& Comprobaciones.noEsNullNiBlanco(table.getValueAt(i, 1).toString())
							&& Comprobaciones.noEsNullNiBlanco(table.getValueAt(i, 2).toString()))
							|| (vieneDe.equals("generarCarnets")
									&& Comprobaciones.noEsNullNiBlanco(table.getValueAt(i, 1).toString()))) {

						Boolean chked = Boolean.valueOf(table.getValueAt(i, 0).toString());
						if (chked) {
							dato = new HashMap<>();
							dato.put("Nº SOCIO", table.getValueAt(i, 1).toString());
							dato.put("EMAIL", table.getValueAt(i, 2).toString());
							dato.put("FAMILIAS", table.getValueAt(i, 3).toString());
							lista.add(dato);
						}
					}
				}

				LOG.info("lista.size(): " + lista.size());
				if (vieneDe.equals("generarCarnets") && lista.size() > 0) {
					GenerarCarnets generarCarnets = new GenerarCarnets();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
					Map<String, Integer> respuestaGenerarCarnets = generarCarnets.rellenarCarnet(lista,
							absolutePath + "\\src\\main\\resources\\imagenes\\" + p.getProperty("archivoCarnetVacio"),
							p.getProperty("carpetaGuardarCarnets"), comboCursos.getSelectedItem().toString(),
							String.valueOf(sdf.format(date)), LOG, p.getProperty("carpetaGuardarCodigosQR"));
					if (respuestaGenerarCarnets == null) {
						lblCarnetsGenerados.setText(p.getProperty("lblCarnetsGeneradosError"));
						lblCarnetsGenerados.setVisible(true);
					} else {
						lblCarnetsGenerados.setText(p.getProperty("carnets1") + " " + respuestaGenerarCarnets.get("OK")
								+ " " + p.getProperty("carnets2") + " " + respuestaGenerarCarnets.get("KO") + " "
								+ p.getProperty("carnets3"));
						lblCarnetsGenerados.setVisible(true);
					}
					LOG.info(lblCarnetsGenerados.getText());
				} else if (vieneDe.equals("enviarEmail") && lista.size() > 0) {
					try {
						new Thread(new Hilo()).start();
						lblCarnetsEnviados.setVisible(true);
						LOG.info(lblCarnetsEnviados.getText());
					} catch (Exception e1) {
						lblError.setVisible(true);
						LOG.info(lblError.getText());
						LOG.info(e1.getStackTrace().toString());
					}
				} else {
					lblError.setVisible(true);
					LOG.info(lblError.getText());
				}
				comienzaProceso(false);
				LOG.info(LocalLogger.logOut("btnContinuar.addActionListener"));
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOG.info(LocalLogger.logIn("btnCancelar.addActionListener"));
				ocultarAvisos();
				panelPrincipal.setVisible(true);
				panelListado.setVisible(false);
				vieneDe = "cancelar";
				LOG.info(LocalLogger.logOut("btnCancelar.addActionListener"));
			}
		});
		LOG.info(LocalLogger.logOut());

	}

	private boolean tieneColumnaDelCursoSeleccionado() {
		String cursoPagado = pagoAMirar(comboCursos.getSelectedItem().toString());
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).get(cursoPagado) != null) {
				return true;
			}			
		}
		return false;
	}

	// 1 = OK, 2 = Ruta vacía o errónea, 3 = No existe columna del año 20XX o nadie pagó
	private boolean cargarListado() {

		LOG.info(LocalLogger.logIn());
		panelScroll.add(chckbxSelectAll);

		// ScrollPane for Table
		if (scrollPane == null)
			scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, anchoVentana - (anchoVentana / 5), altoVentana - (altoVentana / 10));
		panelScroll.add(scrollPane);

		// Table
		table = new JTable();
		scrollPane.setViewportView(table);

		// Model for Table
		@SuppressWarnings("serial")
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

		// Para que las tablas se puedan ordenar por columna se agregan estas dos líneas
		TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
		table.setRowSorter(elQueOrdena);

		model.addColumn("");
		model.addColumn(p.getProperty("col1"));
		model.addColumn(p.getProperty("col2"));
		model.addColumn(p.getProperty("col3"));
		model.addColumn(p.getProperty("col4"));
		model.addColumn(p.getProperty("col5"));
		
		String cursoPagado = pagoAMirar(comboCursos.getSelectedItem().toString());

		// Data Row
		int filaModelo = 0;
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				if (Comprobaciones.noEsNullNiBlanco(lista.get(i).get(cursoPagado)) 
						&& lista.get(i).get(cursoPagado).equalsIgnoreCase("PAGADO")
						&& (Comprobaciones.noEsNullNiBlanco(lista.get(i).get("Nº SOCIO"))
						|| Comprobaciones.noEsNullNiBlanco(lista.get(i).get("EMAIL"))
						|| Comprobaciones.noEsNullNiBlanco(lista.get(i).get("FAMILIAS"))
						|| Comprobaciones.noEsNullNiBlanco(lista.get(i).get("NOMBRE MADRE"))
						|| Comprobaciones.noEsNullNiBlanco(lista.get(i).get("NOMBRE2 PADRE")))) {
					model.addRow(new Object[0]);
					model.setValueAt(false, filaModelo, 0);
					model.setValueAt(lista.get(i).get("Nº SOCIO"), filaModelo, 1);
					model.setValueAt(lista.get(i).get("EMAIL"), filaModelo, 2);
					model.setValueAt(lista.get(i).get("FAMILIAS"), filaModelo, 3);
					model.setValueAt(lista.get(i).get("NOMBRE MADRE"), filaModelo, 4);
					model.setValueAt(lista.get(i).get("NOMBRE2 PADRE"), filaModelo, 5);
					filaModelo++;
				}
			}
		}
		LOG.info(LocalLogger.logOut());
		return model.getRowCount() > 0;
	}

	private String pagoAMirar(String curso) {
		
		return curso.substring(0, 4);
	}

	private void avisosDeError(boolean miroDate) {
		if (!Comprobaciones.noEsNullNiBlanco(textFieldRutaBBDD.getText())) {
			//ruta vacía
			lblRutaBBDDError.setText(p.getProperty("lblRutaBBDDError.vacia"));
			lblRutaBBDDError.setVisible(true);
			LOG.info(lblRutaBBDDError.getText());
		} else if(!Comprobaciones.verificarExtensionDeArchivo(textFieldRutaBBDD.getText(), extn)) {
			//archivo con extensión errónea
			lblRutaBBDDError.setText(p.getProperty("lblRutaBBDDError.extension"));
			lblRutaBBDDError.setVisible(true);
			LOG.info(lblRutaBBDDError.getText());
		} else if(noTieneColumnaDelCursoSeleccionado) {
			// no existe columna curso seleccionado
			lblRutaBBDDError.setText(p.getProperty("lblRutaBBDDError.columna"));
			lblRutaBBDDError.setVisible(true);
			LOG.info(lblRutaBBDDError.getText());						
		} else if(listadoVacio) {
			// nadie pagó la cuota de ese curso
			lblRutaBBDDError.setText(p.getProperty("lblRutaBBDDError.cuota"));
			lblRutaBBDDError.setVisible(true);
			LOG.info(lblRutaBBDDError.getText());						
		}
		if (comboCursos.getSelectedItem().toString().equals(cursos[0])) {
			lblCursoError.setVisible(true);
			LOG.info(lblCursoError.getText());
		}
		if (date == null && miroDate) {
			lblDateChooserError.setVisible(true);
			LOG.info(lblDateChooserError.getText());
		}				
	}

	private void ocultarAvisos() {
		lblRutaBBDDError.setVisible(false);
		lblCursoError.setVisible(false);
		lblDateChooserError.setVisible(false);
		lblCarnetsGenerados.setVisible(false);
		lblCarnetsEnviados.setVisible(false);
		lblError.setVisible(false);
		lblErrorLecturaFicheroExcel.setVisible(false);
	}

	@SuppressWarnings({ "deprecation" })
	private void comienzaProceso(boolean estado) {
		if (estado) {
			setCursor(Cursor.WAIT_CURSOR);
		} else {
			setCursor(Cursor.DEFAULT_CURSOR);
		}
		btnEnviarEmail.setEnabled(!estado);
		btnGenerarCarnets.setEnabled(!estado);
		btnContinuar.setEnabled(!estado);
		btnCancelar.setEnabled(!estado);
	}
	
	public class Hilo implements Runnable {

		@Override
		public void run() {
			Properties conexion = new Properties();
			try {
				conexion.load(new FileReader(p.getProperty("carpetaConexion") + "Conexion.properties"));

				String respuesta = null;
				String rutaCarnets = p.getProperty("carpetaGuardarCarnets") + comboCursos.getSelectedItem().toString() + "\\";
				String rutaOArchivo = Comprobaciones.verSiExisteCarpetaOArchivo(rutaCarnets);
				
				List<Map<String, String>> datosEnviados;
				if (rutaOArchivo != null && rutaOArchivo.equals("carpeta") && lista != null && lista.size() > 0) {
					boolean enviados = true;
					int contarOK = 0;
					datosEnviados = new ArrayList<Map<String, String>>();
					for (int i = 0; i < lista.size(); i++) {
						if (lista.get(i) != null && Comprobaciones.noEsNullNiBlanco(lista.get(i).get("EMAIL"))
								&& Comprobaciones.noEsNullNiBlanco(lista.get(i).get("Nº SOCIO"))) {
							lblCarnetsEnviados
									.setText("Enviando email " + (i + 1) + " a: " + lista.get(i).get("EMAIL"));
							if (!Email.enviarCarnet(lista.get(i).get("EMAIL"),
									rutaCarnets + lista.get(i).get("Nº SOCIO") + ".jpg", lista.get(i).get("FAMILIAS"),
									conexion, LOG)) {
								enviados = false;
							} else {
								datosEnviados.add(lista.get(i));
								contarOK++;
							}

						} else {
							LOG.info(LocalLogger.logError("Email de posición " + (i + 1)
									+ " del listado no se puede enviar por falta de datos - " + lista.get(i)));
						}
					}
					if (datosEnviados != null && datosEnviados.size() > 0) {
						Email.enviarResumen(conexion.getProperty("receptorResumen"), datosEnviados, conexion, LOG);
					}
					if (enviados) {
						respuesta = p.getProperty("enviosOK") + " " + contarOK + " "
								+ p.getProperty("enviosFinMensaje");
					} else {
						respuesta = p.getProperty("enviosKO1") + " " + contarOK + " " + p.getProperty("enviosKO2") + " "
								+ lista.size() + " " + p.getProperty("enviosFinMensaje");
					}

				} else {
					respuesta = p.getProperty("carpetaError");
				}

				lblCarnetsEnviados.setText(respuesta);
			} catch (Exception e1) {
				lblError.setVisible(true);
				LOG.info(lblError.getText());
				LOG.info(e1.getStackTrace().toString());
			}
		}
		
	}
}
