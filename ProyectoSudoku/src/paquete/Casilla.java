package paquete;



/**
  @author Dani Moreno
 */
 //Esta clase contiene los metodos para poder trabajar con las casillas del tablero
public class Casilla {
	
	//Son las partes del tablero que contendran un 3x3
	public enum Seccion
	{
		PRIMERO, SEGUNDO, TERCERO,
		CUARTO, QUINTO, SEXTO,
		SEPTIMO, OCTAVO, NOVENO
	}

	/// PROPIEDADES
	private int valor;
	private int posX;
	private int posY;
	private Seccion sector;
	private boolean editable;
	
	/// CONSTRUCTORES
	
	public Casilla() {}
	
	
	public Casilla(int valor, int posX, int posY, Seccion sector, boolean editable) {
		
		this.valor 		= valor;
		this.posX 		= posX;
		this.posY 		= posY;
		this.sector 	= sector;
		this.editable 	= editable;
	}
	
	/// METODOS
	
	
	 //Asigna la seccion a la casilla dependiendo del eje Y y el eje X
	 
	public void establecerSectorSegunPosicion() {
		switch(posX) {
		
		case 0:
		case 1:
		case 2:
			switch(posY) {
			case 0:
			case 1:
			case 2:
				this.setSector(Seccion.PRIMERO);
				break;
			case 3:
			case 4:
			case 5:
				this.setSector(Seccion.SEGUNDO);
				break;
			case 6:
			case 7: 
			case 8:
				this.setSector(Seccion.TERCERO);
				break;
			default:
				break;
			}
			break;
		case 3:
		case 4:
		case 5:
			switch(posY) {
			case 0:
			case 1:
			case 2:
				this.setSector(Seccion.CUARTO);
				break;
			case 3:
			case 4:
			case 5:
				this.setSector(Seccion.QUINTO);
				break;
			case 6:
			case 7: 
			case 8:
				this.setSector(Seccion.SEXTO);
				break;
			default:
				break;
			}
			break;
		case 6:
		case 7:
		case 8:
			switch(posY) {
			case 0:
			case 1:
			case 2:
				this.setSector(Seccion.SEPTIMO);
				break;
			case 3:
			case 4:
			case 5:
				this.setSector(Seccion.OCTAVO);
				break;
			case 6:
			case 7: 
			case 8:
				this.setSector(Seccion.NOVENO);
				break;
			default:
				break;
			}
			break;
		}
	}

	
	 // Devuelve el valor de la casilla
	
	public int getValor() {
		return valor;
	}

	
	 // Establece el valor de la casilla
	
	public void setValor(int valor) {
		this.valor = valor;
	}

	// Devuelve la posicion horizontal
	
	public int getPosX() {
		return posX;
	}

	
	 //Establece la posicion horizontal
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	
	 //Devuelve la posicion vertical
	 
	 
	public int getPosY() {
		return posY;
	}

	
	 //Establece la posicion vertical
	 	public void setPosY(int posY) {
		this.posY = posY;
	}


	 // Devuelve el sector de la casilla
	
	public Seccion getSector() {
		return sector;
	}

	
	 //Establece el sector de la casilla
	
	public void setSector(Seccion sector) {
		this.sector = sector;
	}

	
	 //Devuelve si la casilla tiene un valor fijo o no
	
	public boolean isEditable() {
		return editable;
	}

	
	 // Establece la opcion de editar la casilla
	
	 
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
