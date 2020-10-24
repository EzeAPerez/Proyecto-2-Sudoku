package GUI;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Logica.Celdas;
import Logica.Juego;
import Logica.cronometro;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Moldea la parte grafica del juego y las funcionalidades de los botones.
 * @author Ezequiell Perez
 *
 */
public class Tablero extends JFrame{

	private JPanel contentPane;
	private JPanel tabla;
	private JPanel panelDerecho;
	private cronometro cronometro;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero frame = new Tablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * Establece todos los componentes graficos.
	 */
	public Tablero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(930, 600));
		this.setTitle("Sudoku");
		setBounds(100, 100, 930, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBorder(new LineBorder(Color.ORANGE));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(new LineBorder(Color.ORANGE));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(9, 9));
		
		tabla = new JPanel();
		contentPane.add(tabla, BorderLayout.WEST);
		tabla.setBorder(new LineBorder(Color.ORANGE));
		tabla.setBackground(Color.BLACK);
		tabla.setMinimumSize(new Dimension(900, 30));
		tabla.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton inicio = new JButton("Inicio");
		inicio.setForeground(Color.PINK);
		inicio.setBackground(Color.BLACK);
		inicio.setBorder(new LineBorder(Color.ORANGE));
		inicio.setFont(new java.awt.Font("Times New Roman", 1, 20));
		inicio.setMinimumSize(new Dimension(100, 30));
		inicio.setMaximumSize(new Dimension(100, 30));
		tabla.add(inicio);
		
		JButton reiniciar = new JButton("Reiniciar");
		reiniciar.setEnabled(false);
		reiniciar.setForeground(Color.PINK);
		reiniciar.setBackground(Color.BLACK);
		reiniciar.setBorder(new LineBorder(Color.ORANGE));
		reiniciar.setFont(new java.awt.Font("Times New Roman", 1, 20));
		reiniciar.setMinimumSize(new Dimension(100, 30));
		tabla.add(reiniciar);
		
		
		inicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent  e) {
				
				Juego juego = new Juego();
				if(juego.tablero!=null){
					iniciar(panel, juego);
					tabla.updateUI();
					panel.updateUI();
					inicio.setEnabled(false);
					reiniciar.setEnabled(true);
					cronometro.iniciar();
					}
				else {
					JLabel error = new JLabel();
					error.setText("Algo anda mal :(");
					error.setForeground(Color.red);
					error.setFont(new java.awt.Font("Arial", 1, 30));
					error.setHorizontalAlignment(SwingConstants.CENTER);
					panel.add(error);
					panel.updateUI();
					inicio.setEnabled(false);
					reiniciar.setEnabled(true);
				}
			}
		});
		
		
		reiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent  e) {
				panel.removeAll();
				contentPane.add(panel, BorderLayout.CENTER);
				panel.updateUI();
				reiniciar.setEnabled(false);
				inicio.setEnabled(true);
				cronometro.resetear();
			}
		});
		
		JLabel titulo = new JLabel("Sudoku");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.ORANGE);
		titulo.setFont(new java.awt.Font("Times New Roman", 8, 50));
		titulo.setBorder(new LineBorder(Color.ORANGE));
		contentPane.add(titulo, BorderLayout.NORTH);
		
		panelDerecho = new JPanel();
		panelDerecho.setBackground(Color.BLACK);
		panelDerecho.setMinimumSize(new Dimension(100, 600));
		contentPane.add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		cronometroGUI();
		setVisible(true);
	}

	/**
	 * Inicializa el juego estableciendo todos los componentes del tablero.
	 * @param panel Panel donde se incertaran los componentes del tablero.
 	 * @param juego Es la clase donde controlan la parte logica del juego.
	 */
	private void iniciar(JPanel panel, Juego juego) {
	for(int i=0; i<9; i++) {
		for(int j=0; j<9; j++) {
			Celdas celda = juego.getCelda(i, j);
			JLabel label = new JLabel();
			JButton boton = new JButton();
			label.setAlignmentX(CENTER_ALIGNMENT);
			label.setAlignmentY(CENTER_ALIGNMENT);
			label.setFont(new java.awt.Font("Times New Roman", 1, 50));
			label.setForeground(Color.black);
			boton.setBackground(Color.BLACK);
			boton.setBorder(new LineBorder(Color.black));
			boton.add(label);
			panel.add(boton);
			
			boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					celda.accionar();
					if(!juego.esta(celda)) {
						celda.getGrafica().setError();
					}
					else celda.setIcono();
					ImageIcon aux = celda.getGrafica().getImagen();
					reDimensionar(boton, aux); 
					boton.setIcon(aux);
					label.setText(""+celda.casillero());
					if(juego.gano()) {
						gano(panel);
					}
				}
			});	

			boton.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					ImageIcon aux = celda.getGrafica().getImagen();
					reDimensionar(boton, aux);
					boton.setIcon(aux);
				}
			});
			
			if(celda.casillero()!=null){
				label.setText(celda.casillero()+"");
				label.setForeground(Color.WHITE);
				boton.removeActionListener(boton.getActionListeners()[0]);
			}
		}
		}
	panel.updateUI();
	}
	
	/**
	 * Redimensiona las imagenes que contienen los botones adaptandose al tamaño del JFrame.
	 * @param boton Boton a ser redimensionado su contenido grafico.
	 * @param grafico Imagen perteneciente al boton.
	 */
	private void reDimensionar(JButton boton, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image newimg = image.getScaledInstance(boton.getWidth(), boton.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			boton.repaint();
		}
	}
	
	/**
	 * Elimina todos los cmponenetes del panel para dar lugar al mensaje de que se ha ganado el juego.
	 * @param panel Panel node se encuentra el tablero, y donde se incertara el mensaje.
	 */
	private void gano(JPanel panel) {
		panel.removeAll();
		JLabel gano = new JLabel("Has ganado!!!!");
		cronometro.finalizar();
		gano.setForeground(Color.pink);
		gano.setFont(new java.awt.Font("Times New Roman", 1, 30));
		gano.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(gano);
		panel.updateUI();
	}
	
	/**
	 * Establece todos los componentes graficos correspondientes al cronometro.
	 */
	private void cronometroGUI() {
		panelDerecho.setBorder(new LineBorder(Color.ORANGE));
		JLabel []cronometroG = new JLabel[8];
		for(int i=0; i<8; i++) {
			if(i!=2 && i!=5) {
				cronometroG[i] = new JLabel();
				cronometroG[i].setMinimumSize(new Dimension(50, 30));
				cronometroG[i].setMaximumSize(new Dimension(60, 40));
				panelDerecho.add(cronometroG[i]);
			}else {
				cronometroG[i] = new JLabel(":");
				cronometroG[i].setFont(new java.awt.Font("Times New Roman", 1, 30));
				cronometroG[i].setForeground(Color.orange);
				panelDerecho.add(cronometroG[i]);
			
			}
		}
		cronometro = new cronometro(cronometroG);
	}
	
}
