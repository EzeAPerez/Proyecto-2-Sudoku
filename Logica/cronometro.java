package Logica;

import java.awt.Image;
import java.time.Duration;
import java.time.LocalTime;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Modela la funcionalidad de un cronometro.
 * @author Ezequiel Perez
 *
 */
public class cronometro implements Runnable{
	LocalTime start;
	LocalTime stop;
	Boolean funciona;
	JLabel[] time;
	Thread hilo;
	String[] imagen;
	
	/**
	 * Construye el cronometro, estableciondo JLabel correspondiente, las imagenes, y inicializado en "00:00:00" y pausado.
	 * @param tiempo Es un arreglo de JLabel donde se pondran sus respectivas imagenes representando al cronometro.
	 */
	public cronometro(JLabel []tiempo){
		time = tiempo;
		imagen = new String[] {"/img/digito0.png", "/img/digito1.png", "/img/digito2.png", "/img/digito3.png", "/img/digito4.png", "/img/digito5.png", "/img/digito6.png", "/img/digito7.png", "/img/digito8.png", "/img/digito9.png"};
		funciona=false;
		setTime("00:00:00");
	}
	
	/**
	 * Inicia el cronometro. 
	 */
	public void iniciar(){
		start = LocalTime.now();
		stop = LocalTime.now();
		hilo = new Thread(this);
		hilo.start();
		funciona=true;
		
	}
	
	/**
	 * Finaliza el cronometro. Pausandolo y sin cambiar su valor.
	 */
	public void finalizar() {
		funciona=false;
	}
	
	/**
	 * Resetea el cronometro, pausandolo y estableciendo el valor de "00:00:00".
	 */
	public void resetear() {
		funciona=false;
		setTime("00:00:00");
	}
	
	/**
	 * Consulta el el timepo que ha transcurrido.
	 * @return Retorna un String representando al tiempo transicurrido.
	 */
	public String getTime() {	
		stop = LocalTime.now( );
		Duration d = Duration.between( start , stop );
		String hms = String.format("%02d:%02d:%02d",d.toHours(), d.toMinutesPart(),  d.toSecondsPart());
		return hms;
	}
	
	public void run() {
		try {
			while(funciona) {
				setTime(getTime());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {setTime("00:00:00");}
	}
	
	/**
	 * Establece los iconos correspondientes para representar graficamente al cronometro.
	 * @param tim Es el tiempo que transcurrio en formato String.
	 */
	private void setTime(String tim) {
		for(int i=0; i<8; i++) {
			if(i!=2 && i!=5) {
				ImageIcon nue= setIcono(tim, i);
				Image nuee = nue.getImage();
				Image ima =  nuee.getScaledInstance(33, 52,  0);
				nue.setImage(ima);
				time[i].setIcon(nue);
			}
		}
	}
	
	/**
	 * Consulta el icono correspondiente a un especifico JLabel ubicado en una cirta posicion.
	 * @param tim Es el tiempo que transcurrio en formato String.
	 * @param pos Posicion especifica donde se quiere establecer lel icono correspondiente.
	 * @return Retorna el icono correspondiente para esa ubicacion. 
	 */
	private ImageIcon setIcono(String tim, int pos) {
		int digito =  Character.getNumericValue(tim.charAt(pos));
		ImageIcon icon = new ImageIcon(this.getClass().getResource(imagen[digito]));
		return icon;
	}
	
	
}
