package Logica;

import javax.swing.ImageIcon;

/**
 * Modela la funcionalidad de la entidad grafica de las celdas.
 * @author Ezequiel Perez
 *
 */
public class Grafica {
	public ImageIcon imagen;
	public String[] imagenes;

	/**
	 * Construye la entidad grafica, inicializando las variables y las imagenes a utilizar.
	 */
	public Grafica() {
		imagen = new ImageIcon();
		imagenes = new String[] {"/img/tablero1.png", "/img/tablero2.png", "/img/error.png"};
	}
	
	/**
	 * Establece el icono correspondiente a la celda dependiendo su ubicacion. 
	 * @param i Posicion en el eje x del tablero.
	 * @param j Posicion en el eje y del tablero.
	 */
	public void setIcono(int i, int j) {
		if((j<3) || j>=6) {
			if(i<3 || i>5) 
				imagen = new ImageIcon(this.getClass().getResource(imagenes[0]));
			else 
				imagen = new ImageIcon(this.getClass().getResource(imagenes[1]));
		}
		else {
			if(i<3 || i>5)
				imagen = new ImageIcon(this.getClass().getResource(imagenes[1]));	
			else
				imagen = new ImageIcon(this.getClass().getResource(imagenes[0]));
		}
	}
	
	/**
	 * Consulta la imagen que contiene la celda.
	 * @return Retorna la imagen de la celda.
	 */
	public ImageIcon getImagen() {
		return imagen;
	}
	
	/**
	 * Establece una imagen especifica de tipo error a la celda.
	 */
	public void setError() {
		imagen = new ImageIcon(this.getClass().getResource(imagenes[2]));
	}
}
