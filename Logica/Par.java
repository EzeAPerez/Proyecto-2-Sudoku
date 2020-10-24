package Logica;

/**
 * Modela la funciones de un par de dos tipos de elementos.
 * @author Ezequiel Perez
 *
 * @param <A> Tipo de dato del elemento A.
 * @param <B> Tipo de dato del elemento B. 
 */
public class Par<A,B> {
	protected A a;
	protected B b;
	
	/**
	 * Construye un par de dos elementos de tipo A y B.
	 * @param a Elemento A.
	 * @param b Elemento B.
	 */
	public Par(A a, B b) {
		this.a =  a;
		this.b = b;
	}
	
	/**
	 * Consulta el elemento A.
	 * @return Retorna el elemento A.
	 */
	public A getA() {
		return a;
	}
	
	/**
	 * Consulta el elemento B.
	 * @return Retorna el elemento B.
	 */
	public B getB() {
		return b;
	}

}
