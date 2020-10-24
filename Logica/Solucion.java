package Logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Modela la funciones para establecer si la solucion dada es valida y pasarla a un formato matriz de enteros.
 * @author Ezequiel Perez
 *
 */
public class Solucion {
	
	/**
	 * Consulta la matriz obtenida luego de verificar y pasar al formato correspondiente.
	 * @return Retorna una matriz (9x9) con la solucion. Si la matriz es NULL entonces hubo un error al verificar la solucion.
	 */
	public int[][] getResultado(){
		int [][] resultado = setear();
		boolean correcto;
		if(resultado!=null)
			 correcto = esta(resultado);
		else correcto=false;
		if(!correcto)
			resultado=null;
		return resultado;
	}
	
	/**
	 * Consulta la matriz obtenidad de una archivo que contiene la solucion.
	 * @return Retorna la matriz obtenida de leer el archivo. Si la matriz es NULL entonces hubo un problema al leer el archivo.
	 */
	private int[][] setear(){
		int [][]toReturn = new int[9][9]; 
		BufferedReader buffer;
		String linea;
		char c;
		boolean correcto=true;
		try {
			buffer = new BufferedReader(new InputStreamReader(this.getClass().getResource("/Soluciones/Solucion1.txt").openStream()));		
			int cont=0;
			while((linea = buffer.readLine()) != null && correcto && cont<9){
				int con =0;	
				for(int i=0; i<linea.length(); i++) {
					c = linea.charAt(i);
					if(c==' ' || (c=='1' || c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9') && con<9 ) {
						if((c=='1' || c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9')){
							toReturn[cont][con] = Character.getNumericValue(c);
							con++;
						}
					}
					else correcto=false;	
				}
				cont++;
			}
		}catch(IOException e){correcto=false;}
		catch(NullPointerException e) {correcto=false;};
		if(!correcto)
			toReturn=null;	
		return toReturn;	
	}
	
	/**
	 * Consulta si la matriz pasada por parametros cumple con todas las reglas del juego. 
	 * @param resultado Matriz de enteros a ser verificada.
	 * @return Delvuelve verdadero si la matriz es correcta, falso en caso contrario.
	 */
	private boolean esta(int [][] resultado) {
		boolean toReturn=true;
		for(int a=0; a<9 && toReturn; a++) { 
			for(int b=0; b<9 && toReturn; b++){
				int casilla = resultado[a][b];
				for(int i=0; i<9 && toReturn; i++) {
					if(i!=b)
						if(resultado[a][i]==casilla) {
							toReturn=false;
						}
				}
				for(int i=0; i<9 && toReturn; i++) {
					if(i!=a)
						if(resultado[i][b]==casilla) {
							toReturn=false;
						}
				}
				if(toReturn) {
					Par<Integer, Integer> cas = new Par<Integer, Integer>(a, b);
					Par<Integer, Integer> ubicacion = getCuadrante(cas);
					for(int i=ubicacion.getA(); i<ubicacion.getA()+3 && toReturn; i++) {
						for(int j=ubicacion.getB(); j<ubicacion.getB()+3 && toReturn; j++) {
							if(i!=a && j!=b)
								if(resultado[i][j]==casilla) {
									toReturn=false;
								}
						}
					}
				}	
			}
		}
		return toReturn;
	}
	
	/**
	 * Consulta a que cuadrante pertenece la ubicacion pasada por parametros.
	 * @param ubi Ubicacion donde se quiere saber a que cuadrante pertenece.
	 * @return Retorna un par de enteros que representa la primer posicion al cuadrante al cual pertenece la ubicacion pasada por parametros-
	 */
	public Par<Integer, Integer> getCuadrante(Par<Integer, Integer> ubi) {
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
	
	
}
