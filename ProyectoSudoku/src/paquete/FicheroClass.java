package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
@author Dani Moreno
*/

 // Clase para trabajar con los ficheros de texto

public class FicheroClass {

	/// PROPIEDADES
	private BufferedReader bufferLectura;
	private BufferedWriter bufferEscritura;
	private File archivo;
	private Properties propiedades;
	private String rutaConfiguracion;
	
	/// CONSTRUCTOR
	public FicheroClass() {
		this.bufferLectura		= null;
		this.bufferEscritura 	= null;
		this.archivo 			= null;
		this.propiedades 	 	= new Properties();
		this.rutaConfiguracion 	= "conf/config.properties";
		
	}
	
	/// METODOS
	
	
	 // Lee un CSV que contiene los valores del sudoku y lo almacena en matriz de enteros
	
	
	public int[][] leerCSVSimple(String ruta, String separador) throws IOException, NumberFormatException {
		
		int i 			= 0;
		int[][] matriz 	= new int[9][9];
		this.bufferLectura 	= new BufferedReader(new FileReader(ruta));
		String linea 	= bufferLectura.readLine();
		
		while (linea != null) {
			// hago el salto de linea en la matriz
			
			// Separa la línea leída con el separador definido previamente
			String[] campos = linea.split(separador);
			
			// Vuelco en la matriz los resultados
			int j = 0;
			
			for (String num : campos) {
				matriz[i][j] = Integer.parseInt(num);
				j++;
			}
			
			i++;
			
			linea = this.bufferLectura.readLine();
		}
		
		// Cierro el buffer
		if (this.bufferLectura != null) {
			bufferLectura.close();
		}
		
		return matriz;
	}
	
	
	 // Devuelve una propiedad del fichero properties
	
	 
	public String leerPropiedad(String miClave) throws IOException {//lee el archivo de configuracion del proyecto 
		
		String resultado = "";
		
		this.propiedades.load(new FileReader(this.rutaConfiguracion));//donde se ubica el archivo de configuracion del csv
		Enumeration<Object> claves = this.propiedades.keys();//
		//define las reglas que seguiran los archivos CSV
		while (claves.hasMoreElements()) {//recorre el archivo de configuracion 
			Object clave = claves.nextElement();
			if (clave.toString().equals(miClave)) {
				resultado = this.propiedades.get(clave).toString();//lo guarda como string
			}
		}
		
		return resultado;
	}
	

	 // Genera un fichero CSV en la ruta pasada como parametro

	
	public void guardarCSV(String ruta, int[][] matrizContenido) {
		
		String textoAGuardar = "";
		
		for (int i = 0; i < 9; i++) {//recorre el tablero
			for (int j = 0; j < 9; j++) {
				textoAGuardar += String.valueOf(matrizContenido[i][j]);//
				if (j != 8) {
					textoAGuardar += ",";//va separando las lineas por coma
				}
			}
			textoAGuardar += "\n";
		}
		
		this.archivo = new File(ruta);
		
		try {
			this.bufferEscritura = new BufferedWriter(new FileWriter(this.archivo));
			this.bufferEscritura.write(textoAGuardar);
			JOptionPane.showMessageDialog(null, "CSV generado correctamente");
		} 
		catch (IOException e) {
			String mensajeError = "Error al generar el CSV" + "\n" + e.getMessage();
			JOptionPane.showMessageDialog(null, mensajeError, "Ocurrio un error", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			
			if (this.bufferEscritura != null) {
				try {
					this.bufferEscritura.close();
				} 
				catch (IOException e) {}
			}
			
			if (this.archivo != null) {
				this.archivo = null;
			}
		}
		
	}
}
