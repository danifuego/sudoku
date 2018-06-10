package Paquete;


 
 //Esta clase contiene los metodos para poder trabajar con las casillas del tablero
public class Casilla {

	/// PROPIEDADES
	private int valor;
	private int posX;
	private int posY;
	private Sector sector;
	private boolean editable;
	
	/// CONSTRUCTORES
	
	public Casilla() {}
	
	
	public Casilla(int valor, int posX, int posY, Sector sector, boolean editable) {
		
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
				this.setSector(Sector.PRIMERO);
				break;
			case 3:
			case 4:
			case 5:
				this.setSector(Sector.SEGUNDO);
				break;
			case 6:
			case 7: 
			case 8:
				this.setSector(Sector.TERCERO);
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
				this.setSector(Sector.CUARTO);
				break;
			case 3:
			case 4:
			case 5:
				this.setSector(Sector.QUINTO);
				break;
			case 6:
			case 7: 
			case 8:
				this.setSector(Sector.SEXTO);
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
				this.setSector(Sector.SEPTIMO);
				break;
			case 3:
			case 4:
			case 5:
				this.setSector(Sector.OCTAVO);
				break;
			case 6:
			case 7: 
			case 8:
				this.setSector(Sector.NOVENO);
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
	
	public Sector getSector() {
		return sector;
	}

	
	 //Establece el sector de la casilla
	
	public void setSector(Sector sector) {
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
