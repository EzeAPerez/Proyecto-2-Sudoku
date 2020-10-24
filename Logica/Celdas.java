package Logica;

/**
 * Modela la funcionalidad de una celda del tablero, que contiene su valor, su ubicacion, su respectiva icono. 
 * @author Ezequiel Perez
 *
 */
public class Celdas {
	protected Integer casillero;
	protected Par<Integer, Integer> ubi;
	protected Grafica entidadGrafica;
	protected Juego jue;
	
	/**
	 * Construye una celda con una ubicacion especifica.
	 * @param i Posicion en el eje x del tablero.
	 * @param j Posicion en el eje y del tablero.
	 */
	public Celdas(int i, int j) {
		ubi = new Par<Integer,Integer>(i,j);
		casillero = null;
		entidadGrafica = new Grafica();
		this.setIcono();
	}
	
	/**
	 * Establece el respectivo icono que le corresponde.
	 */
	public void setIcono() {
		entidadGrafica.setIcono(ubi.getA(), ubi.getB());
	}
	
	/**
	 * Consulta la entidad grafica correspondiente a la celda
	 * @return Retorna la entidad grafica correspondiente de tipo Gafica.
	 */
	public Grafica getGrafica() {
		return entidadGrafica;
	}
	
	/**
	 * Aumente en 1 el valor del casillero. Si este valor es supera a 9 entonces el casillero vuelve a empezar desde el valor 1.
	 */
	public void accionar() {
		if(casillero==null) {
			casillero=1;
		}
		else {
			casillero++;
			if(casillero==10) {
				casillero=1;
			}
		}
	}
	
	/**
	 * Consulta el valor que contiene la celda.
	 * @return Retorna un entero que es el valor contenido por la celda.
	 */
	public Integer casillero() {
		return casillero;
	}
	
	/**
	 * Consulta la ubicacion de la celda.
	 * @return Retorna un par de enteros representando la posicion de la celda en el tablero.
	 */
	public Par<Integer,Integer> getUbi(){
		return ubi;
	}
	
	/**
	 * Establece un entero predeterminado al casillero de la celda.
	 * @param val Valor a insertar en el casillero.
	 */
	public void setCasillero(Integer val) {
		casillero=val;
	}
}
	

