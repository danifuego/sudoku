package app;

// contiene la estructura del tablero de Sudoku 

public class Tablero {

	/// PROPIEDADES 
	
	private Casilla[][] casillas;
	
	/// CONSTRUCTOR
	
	
	 // Genera un tablero de sudoku basico de 9x9
	 
	public Tablero() {
		this.casillas = new Casilla[9][9];
	}
	
	/// METODOS
	
	
	  //Devuelve las casillas del tablero
	
	public Casilla[][] getCasillas() {
		return this.casillas;
	}
	
	/**
	 * Inicializa el tablero estableciendo por defecto los valores:
	 * Valor 	= 0
	 * PosX 	= i
	 * PosY 	= j
	 * Editable = true
	 * Sector 	= Segun posicion
	 */
	public void inicializarTablero() {
		
		for (int i = 0; i < this.casillas.length; i++) {
			for (int j = 0; j < this.casillas[i].length; j++) {
				Casilla casilla = new Casilla();
				casilla.setValor(0);
				casilla.setPosX(i);
				casilla.setPosY(j);
				casilla.setEditable(true);
				casilla.establecerSectorSegunPosicion();
				
				this.casillas[i][j] = casilla;
			}
		}
	}
	
	
	 // Establece una nueva casilla en la posicion que tenga configurada la misma
	 
	public void editarCasilla(Casilla casilla) {
		this.casillas[casilla.getPosX()][casilla.getPosY()] = casilla;
	}
	
	/**
	 * Reestablece el valor de la casilla segun su posicion
	 * @param x Posicion horizontal
	 * @param y Posicion vertical
	 */
	public void reestablecerCasillaPorPosicion(int x, int y) {
		this.casillas[x][y].setValor(0);
	}
	
	/**
	 * Inserta los valores de la matriz en la misma casilla respecto a su posicion
	 * Ha tenido que inicializarse la matriz de casillas previamente
	
	 */
	public void insertarValores(int[][] matriz) {
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] != 0) {
					this.casillas[i][j].setEditable(false);
				}
				this.casillas[i][j].setValor(matriz[i][j]);
			}
		}
	}
}
