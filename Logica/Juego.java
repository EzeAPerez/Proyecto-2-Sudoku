package Logica;

import java.util.Random;

/**
 * Modelas las operaciones principales de juego para que funcione
 * @author Ezequiel Perez
 *
 */
public class Juego {
	public Celdas[][] tablero;
	public int[][] resuelto;
	
	/**
	 * Construye el tablero que consiste en una matris (9x9) que contiene todas las celdas y con un random le va dando valor a algunas de ellas. 
	 * Si solucion.getResultado() es nulo entonces el resultado es erroneo por ende no se construyte la matriz 
	 * 
	 */
	public Juego() {
		Solucion solucion = new Solucion();
		resuelto = solucion.getResultado();
		if(resuelto!=null) {
			tablero = new Celdas[9][9];	
			for(int i=0; i<9; i++) {
			int cont=0;
				for(int j=0; j<9; j++) {
					tablero[i][j] = new Celdas(i,j);
					Random rand = new Random();
					int value = rand.nextInt(4);
					if(value==0 && cont<3) {
						getCelda(i,j).setCasillero(resuelto[i][j]);
						cont++;
					}
				}
			}
		}
	}
	
	/**
	 * Consulta la celda que se encuentra en la posicion (i, j)
	 * @param i posicion en el eje x del tablero
	 * @param j posicion en el eje y del tablero
	 * @return retorna la celda de la ubicacion (i, j)
	 */
	public Celdas getCelda(int i, int j) {
		return tablero[i][j];
	}
	
	/**
	 * Consulta si la celda cumple con las condiciones de que en una misma fila o/y columna tengan valores iguales y en un mismo cuadrante.
	 * @param celda Celda a verificar si cumple con las reglas.
	 * @return Retorna verdadedor si cumple, falso si no cumple alguna de las 3 reglas.
	 */
	public boolean esta(Celdas celda) {
		boolean toReturn=true;
		int a=celda.getUbi().getA();
		int b=celda.getUbi().getB();
		Par<Integer,Integer> par;
		
		for(int i=0; i<9 && toReturn; i++) {
			if(i!=b)
				if(tablero[a][i].casillero()==celda.casillero()) {
					toReturn=false;
				}
		}
		for(int i=0; i<9 && toReturn; i++) {
			if(i!=a)
				if(tablero[i][b].casillero()==celda.casillero()) {
					toReturn=false;
				}
		}
		
		if(toReturn) {
			par=getCuadrante(celda.getUbi());
			for(int i=par.getA(); i<par.getA()+3 && toReturn; i++) {
				for(int j=par.getB(); j<par.getB()+3 && toReturn; j++) {
					if(i!=a && j!=b)
						if(tablero[i][j].casillero()==celda.casillero()) {
							toReturn=false;
						
					}
				}
			}
			
		}
		return toReturn;
	}
	
	/**
	 * Consulta en que cuadrante se encuenta la celda con la ubicacion pasada por parametros.
	 * @param ubi Ubicacion de la celda que contiene las cordenadas.
	 * @return Retorna la ubicacion donde se encuentra el primer elemento del cuadrante donde pertenece la celda.
	 */
	private Par<Integer, Integer> getCuadrante(Par<Integer, Integer> ubi) {
		int a = ubi.getA();
		int b = ubi.getB();
		Integer auxA=0;
		Integer auxB=0;
		Par<Integer,Integer> toReturn;
		switch (a) {
			case 0 : case 1 : case 2 : auxA = 0;
			break;
			case 3 : case 4 : case 5 : auxA = 3;
			break;
			case 6 : case 7 : case 8 : auxA = 6;
			break;
		};
		switch (b) {
			case 0 : case 1 : case 2 : auxB = 0; 
			break;
			case 3 : case 4 : case 5 : auxB = 3;
			break;
			case 6 : case 7 : case 8 : auxB = 6;
			break;
		};
		toReturn= new Par<Integer, Integer>(auxA, auxB);
		return toReturn;
	}
	
	/**
	 * Consulta si se gano la partida. Se encarga de verificar que todos los casilleros no sean nulos y cumplan con la unica solucion disponible.
	 * @return Retorna verdadero si gano, falso si todavia no gano.
	 */
	public boolean gano() {
		boolean gano=true;
		for(int i=0; i<9 && gano; i++)
			for(int j=0; j<9 && gano; j++) {
				if(tablero[i][j].casillero()!=null) {
					if(!(tablero[i][j].casillero()==resuelto[i][j]))
						gano=false;
					}
				else gano=false;
			}
		return gano;
	}

	
}

	
