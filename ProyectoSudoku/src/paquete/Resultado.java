package paquete;

import java.util.ArrayList;

/**
@author Dani Moreno
*/

public class Resultado {
	
	/// METODOS
	
	
	 // Metodo que controla las operaciones necesarias para solucionar el sudoku
	 
	public EstructuraTablero solucionarSudoku(EstructuraTablero tablero) {
		
		ArrayList<Casilla> editables = this.extraerCasillasEditables(tablero.getCasillas());
		int i = 0;
		
		while (i < editables.size()) {
			
			// Control de salida. Si el primer resultado es igual a 0 y su valor superior al maximo permitido por el sudoku, 
			// error al solucionar el sudoku
			
			Casilla actual = editables.get(i);
			if (actual.getValor() > 9) {
				tablero.reestablecerCasillaPorPosicion(actual.getPosX(), actual.getPosY());
				i--;
				
				if (i < 0) {
					return null;
				}
				else {
					actual = editables.get(i);
					actual.setValor(actual.getValor() + 1);
					tablero.editarCasilla(actual);
				}
			}
			else {
				if (actual.getValor() == 0) {
					actual.setValor(1);
				}
				
				ArrayList<ArrayList<Casilla>> listasParaValidar = this.extraerCasillasComparables(tablero, actual);
				
				// Validar si listas cumplen reglas de Sudoku (salvo valor sin rellenar)
				if (ComprobarFilaColumnaSector(listasParaValidar)) {
					tablero.editarCasilla(actual);
					i++;
				}
				else
				{
					actual.setValor(actual.getValor() + 1);
					if (actual.getValor() > 9) {
						tablero.reestablecerCasillaPorPosicion(actual.getPosX(), actual.getPosY());
						if (i != 0) {
							i--;
							actual = editables.get(i);
							actual.setValor(actual.getValor() + 1);
							tablero.editarCasilla(actual);
						}
						else {
							return null;
						}
					}
				}
			}
			
		}
		
		return tablero;
	}
	
	
	 // Devuelve un array con todas las casillas en las que la propiedad editable es verdadera
	
	private ArrayList<Casilla> extraerCasillasEditables(Casilla[][] casillas) {
		ArrayList<Casilla> editables = new ArrayList<Casilla>();
		
		for (int i = 0; i < casillas.length; i++ ) {
			for (int j = 0; j < casillas[i].length; j++) {
				if (casillas[i][j].isEditable()) {
					editables.add(casillas[i][j]);
				}
			}
		}
		
		return editables;
	}
	
	
	 //Recoge las casillas que contengan la misma fila, columna o sector que la casilla actual
	
	//ArrayList que contiene tres arrayList de casillas. Fila, columna y sector
	 
	private ArrayList<ArrayList<Casilla>> extraerCasillasComparables(EstructuraTablero tablero, Casilla actual) {
		
		ArrayList<Casilla> fila 	= new ArrayList<Casilla>();
		ArrayList<Casilla> columna 	= new ArrayList<Casilla>();
		ArrayList<Casilla> sector 	= new ArrayList<Casilla>();
		
		for (int i = 0; i < tablero.getCasillas().length; i++) {
			for (int j = 0; j < tablero.getCasillas()[i].length; j++) {
				if (tablero.getCasillas()[i][j].getPosX() == actual.getPosX()) {
					fila.add(tablero.getCasillas()[i][j]);
				}
				
				if (tablero.getCasillas()[i][j].getPosY() == actual.getPosY()) {
					columna.add(tablero.getCasillas()[i][j]);
				}
				
				if (tablero.getCasillas()[i][j].getSector() == actual.getSector()) {
					sector.add(tablero.getCasillas()[i][j]);
				}
			}
		}
		
		ArrayList<ArrayList<Casilla>> resultado = new ArrayList<ArrayList<Casilla>>();
		resultado.add(fila);
		resultado.add(columna);
		resultado.add(sector);
		
		return resultado;
	}
	
	
	 //Valida la fila, columna y sector. Si no se repite ningun numero (sin contar el 0)
		
	private boolean ComprobarFilaColumnaSector(ArrayList<ArrayList<Casilla>> listas) {
		
		for (ArrayList<Casilla> lista : listas) {
			
			ArrayList<Integer> valores = null;
			valores = new ArrayList<Integer>();
			
			for (Casilla casilla : lista) {
				if (casilla.getValor() != 0) {
					if (valores.contains(casilla.getValor())) {
						return false;
					}
					else {
						valores.add(casilla.getValor());
					}
				}
			}
		}
		
		return true;
	}
}
