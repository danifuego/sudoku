package jo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

public class Interfaz_Tablero extends JFrame {

	/// PROPIEDADES
	private JPanel contenedor;
	
	
	private JFormattedTextField[][] casillas = new JFormattedTextField[9][9];
	private MaskFormatter mascara;
	
	private JSeparator separadorHorizontal1;
	private JSeparator separadorHorizontal2;
	private JSeparator separadorVertical1;
	private JSeparator separadorVertical2;
	
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mnItemNuevo;
	private JMenuItem mnItemCargarCSV;
	private JMenuItem mnItemExportarCSV;
	private JMenuItem mnItemFuncionamiento;
	private JMenuItem mnItemInfo;
	
	private JButton btnSolucionar;
	private JButton btnLimpiar;
	JButton btnsalir;
	private JFileChooser selectorDeFichero;
	
	private JLabel lblLogo;
	
	private EstructuraTablero tablero;
	private Resultado solucionador;
	private FicheroClass manejadorDeFicheros;
	
	private ActionListener actionNuevo;
	private ActionListener actionCargarCSV;
	private ActionListener actionExportarCSV;
	private ActionListener actionFuncionamiento;
	private ActionListener actionInfo;
	private ActionListener actionSolucionar;
	ActionListener salirapp;
	
	private FileNameExtensionFilter filtroExtension;
	
	/// CONSTRUCTOR
	public Interfaz_Tablero() {
		
		this.tablero = new EstructuraTablero();
		tablero.inicializarTablero();
		
		this.selectorDeFichero = new JFileChooser();
		this.filtroExtension = new FileNameExtensionFilter("Archivos CSV", "csv");
		this.selectorDeFichero.setFileFilter(this.filtroExtension);
		
		this.solucionador = new Resultado();
		this.manejadorDeFicheros = new FicheroClass();
		
		this.inicializarActionListeners();
		this.cargarConfiguracionBasica();
		
	}
	
	/// METODOS
	
	/**
	 inicializamos todos los componentes de la interfaz
	 */
	private void cargarConfiguracionBasica() {
		
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		contenedor.setLayout(null);
		contenedor.setBackground(Color.orange);
		
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0,0, 547, 21);
		contenedor.add(menuBar);
		
		
		mnArchivo = new JMenu("Archivo");
		menuBar.add(this.mnArchivo);
		
		mnItemNuevo = new JMenuItem("Nuevo");
		mnItemNuevo.addActionListener(this.actionNuevo);
		mnArchivo.add(this.mnItemNuevo);
		
		mnArchivo.addSeparator();
		
		mnItemCargarCSV = new JMenuItem("Cargar desde CSV");
		mnItemCargarCSV.addActionListener(this.actionCargarCSV);
		mnArchivo.add(this.mnItemCargarCSV);
		
		mnItemExportarCSV = new JMenuItem("Exportar a CSV");
		mnItemExportarCSV.addActionListener(this.actionExportarCSV);
		mnArchivo.add(this.mnItemExportarCSV);
		
		
		//textfield del tablero
		try {
			this.mascara = new MaskFormatter("#");
		} catch (ParseException e) {
			this.mostrarMensajeDeError(e.getMessage());
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.casillas[i][j] = new JFormattedTextField(this.mascara);
				this.casillas[i][j].setColumns(10);
				this.casillas[i][j].setBounds(this.calcularPosicionHorizontal(j), this.calcularPosicionVertical(i), 30, 30);
				this.casillas[i][j].setFont(new Font("Tahoma", Font.PLAIN, 12));
				this.casillas[i][j].setHorizontalAlignment(JTextField.CENTER);

				this.contenedor.add(this.casillas[i][j]);
			}
		}
		
		
		
		//separadores
		this.separadorVertical1 = new JSeparator();
		this.separadorVertical1.setOrientation(SwingConstants.VERTICAL);
		this.separadorVertical1.setForeground( Color.BLUE);
		this.separadorVertical1.setBackground( Color.BLUE);
		this.separadorVertical1.setBounds(113, 32, 5, 322);
		this.contenedor.add(this.separadorVertical1);
		
		this.separadorVertical2 = new JSeparator();
		this.separadorVertical2.setOrientation(SwingConstants.VERTICAL);
		this.separadorVertical2.setForeground (Color.BLUE);
		this.separadorVertical2.setBackground(Color.BLUE);
		this.separadorVertical2.setBounds(223, 32, 5, 322);
		this.contenedor.add(this.separadorVertical2);
		
		this.separadorHorizontal1 = new JSeparator();
		this.separadorHorizontal1.setForeground(Color.BLUE);
		this.separadorHorizontal1.setBackground(Color.BLUE);
		this.separadorHorizontal1.setBounds(10, 137,320, 2);
		this.contenedor.add(this.separadorHorizontal1);
		
		this.separadorHorizontal2 = new JSeparator();
		this.separadorHorizontal2.setForeground(Color.BLUE);
		this.separadorHorizontal2.setBackground(Color.BLUE);
		this.separadorHorizontal2.setBounds(10, 248, 320, 2);
		this.contenedor.add(this.separadorHorizontal2);
		
		//botones
		
		this.btnsalir = new JButton("Salir de la aplicacion");
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.btnsalir.setBounds(340, 261, 162,23);
		this.btnsalir.addActionListener(salirapp);
		this.contenedor.add(this.btnsalir);
		
		this.btnSolucionar = new JButton("Solucionar Sudoku");
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.btnSolucionar.setBounds(340, 296, 162, 23);
		this.btnSolucionar.addActionListener(this.actionSolucionar);
		this.contenedor.add(this.btnSolucionar);
		
		this.btnLimpiar = new JButton("Limpiar tablero");
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.btnLimpiar.setBounds(340, 331, 162,23);
		this.btnLimpiar.addActionListener(this.actionNuevo);
		this.contenedor.add(this.btnLimpiar);
		
		
		
		
		setTitle("Juego del Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,528,405);
		setContentPane(contenedor);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	


	
	//los eventos de los botones de la interfaz
	private void inicializarActionListeners() {
		
		// BOTON NUEVO
		this.actionNuevo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.inicializarTablero();
				pintarTablero(tablero.getCasillas());
			}
		};
		
		// BOTON CARGAR CSV
		this.actionCargarCSV = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String ruta = elegirFicheroConVentana();
				
				// Si el usuario no pulsa cancelar
				if (!ruta.equals("CANCEL")) {
					try {
						
						int[][] matriz = manejadorDeFicheros.leerCSVSimple(ruta, manejadorDeFicheros.leerPropiedad("SEPARADOR_CSV"));
						tablero.inicializarTablero();
						tablero.insertarValores(matriz);
						pintarTablero(tablero.getCasillas());
					} catch (NumberFormatException | IOException ex) {
						mostrarMensajeDeError(ex.getMessage());
					}
				}
				
			}
		};
		
		// ACTION EXPORTAR CSV
		this.actionExportarCSV = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String ruta = RutaGuardadoCSV();
				if (!ruta.equals("CANCEL")) {
					if (!ruta.endsWith(".csv")) {
						ruta += ".csv";
					}
					manejadorDeFicheros.guardarCSV(ruta, leerContenidoDelTablero());
				}
				
			}
		};
			
		// BOTON SOLUCIONAR
		this.actionSolucionar = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				actualizarcasillasTablero();
				EstructuraTablero nuevoTablero = solucionador.solucionarSudoku(tablero);
				if (nuevoTablero == null) {
					mostrarMensajeDeError("el sudoku no es correcto");
				}
				else {
					tablero = nuevoTablero;
					pintarTablero(tablero.getCasillas());
				}
			}
		};
		
		//BOTON Salir de la aplicacion
		salirapp = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
	}
	
	
	 // Muestra un mensaje de error con interfaz grafica
	 
	
	private void mostrarMensajeDeError(String mensaje) {//metodo para mostrar por pantalla  un mensaje de error
		
		JOptionPane.showMessageDialog(this.contenedor, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	

	//se le pasa la matriz con los numeros y los va imprimiendo en el tablero
	public void pintarTablero(Casilla[][] matriz) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String texto = "";
				if (matriz[i][j].getValor() != 0) {
					texto = String.valueOf(matriz[i][j].getValor());
				}
				this.casillas[i][j].setText(texto);
				
				if (matriz[i][j].isEditable()) {
					this.casillas[i][j].setForeground(Color.BLUE);
				}
				else {
					this.casillas[i][j].setForeground(Color.BLACK);
				}
			}
		}
	}
	
// Lee el contenido del tablero y devuelve los valores de las casillas en una matriz de enteros
	private int[][] leerContenidoDelTablero() {
		int[][] matriz = new int[9][9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9 ; j++) {
				matriz[i][j] = this.tablero.getCasillas()[i][j].getValor();
			}
		}
		
		return matriz;
	}
	
	// Actualiza los campos del tablero  con los insertados y / o modificados por el usuario en la interfaz grÃ¡fica.
	private void actualizarcasillasTablero() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String valor = this.casillas[i][j].getText();//variable de tipo string para comprobar si tiene la casilla espacios o esta vacia 
				int valorReal;//valor real que tiene o va a tener la casilla
				if (valor.equals("") || valor.equals(" ")) {//compara si el contenido de la casilla es vacio o tiene un espacio
					valorReal = 0;//en tal caso el numero de la casilla sera 0 (representa un campo vacio )
				}
				else {
					valorReal = Integer.parseInt(valor);//se convierte el valor cogido en la variable "valor" dado que recoge un string
				}
				
				tablero.getCasillas()[i][j].setValor(valorReal);//para darle ese valor conseguido antes a la casilla que este en esa posicion
				if (this.tablero.getCasillas()[i][j].getValor() == 0) {//si el valor de la casilla es 0 
					this.tablero.getCasillas()[i][j].setEditable(true);//sera un numero que tiene que sacar el usuario por tanto es editable
				}
				else {
					this.tablero.getCasillas()[i][j].setEditable(false);//sino la casilla no es editable y es un numero precargado
				}
			}
		}
	}
	
	//Abre una ventana de dialogo para elegir un fichero CSV  usando la clase filechooser
	private String elegirFicheroConVentana() {
		
		String ruta = "";
		int seleccion = this.selectorDeFichero.showOpenDialog(this.contenedor);
		 
		 if (seleccion == JFileChooser.APPROVE_OPTION) {
			 ruta = this.selectorDeFichero.getSelectedFile().getAbsolutePath();
		 }
		 else {
			 ruta = "CANCEL";
		 }
		 
		 return ruta;
	}
	
	// Abre una ventana de dialogo para elegir donde guardar el nuevo fichero CSV
	private String RutaGuardadoCSV() {
		
		String ruta = "";
		int seleccion = this.selectorDeFichero.showSaveDialog(this.contenedor);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			ruta = this.selectorDeFichero.getSelectedFile().getAbsolutePath();
		}
		else {
			ruta = "CANCEL";
		}
		
		return ruta;
	}
	
	//Calcula la posicion horizontal en pixeles  de la casilla 
	//se van recorriendo en el bucle de la interfaz los casos y se van añadiendo en su eje vertical de forma automatica
	private int calcularPosicionHorizontal(int x) {
		
		int resultado = 0;
		
		switch(x) {
		case 0:
			resultado = 10;
			break;
		case 1:
			resultado = 45;
			break;
		case 2:
			resultado = 80;
			break;
		case 3:
			resultado = 120;
			break;
		case 4:
			resultado = 155;
			break;
		case 5:
			resultado = 190;
			break;
		case 6:
			resultado = 230;
			break;
		case 7:
			resultado = 265;
			break;
		case 8:
			resultado = 300;
			break;
		}
		
		return resultado;
	}

	
	 // Calcula la posicion vertical en pixeles de la casilla
		//se van recorriendo en el bucle de la interfaz los casos y se van añadiendo en su eje vertical de forma automatica
	
	 
	private int calcularPosicionVertical(int y) {
		
		int resultado = 0;
		
		switch(y) {
		case 0:
			resultado = 32;
			break;
		case 1:
			resultado = 67;
			break;
		case 2:
			resultado = 102;
			break;
		case 3:
			resultado = 143;
			break;
		case 4:
			resultado = 178;
			break;
		case 5:
			resultado = 213;
			break;
		case 6:
			resultado = 254;
			break;
		case 7:
			resultado = 289;
			break;
		case 8:
			resultado = 324;
			break;
		}
		
		return resultado;
	}
	
}
