package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.Border;

import main.EstadosJuego;

public class VentanaJuego extends JFrame{
	
	public static int esperar = 0;
	public static EstadosJuego estado;
	
	private static ArrayList<Pokemon> miEquipo = new ArrayList<>();
	private static ArrayList<Pokemon> oponente = new ArrayList<>();
	private static VentanaJuego vj;
	public static JPanel panel_movimientos_1;
	public static JPanel panel_movimientos_2;

	
	private JProgressBar vida_1;
	private JProgressBar vida_2;
	
	private Combate c;
	
	public VentanaJuego(ArrayList<Pokemon> miPokemon, ArrayList<Pokemon> oponente) {
		
		setTitle("POKEMON");
		setLocation(200, 100);
		setSize(800, 500);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		this.miEquipo = miPokemon;
		this.oponente = oponente;
		
		crearPanelFrontal();
		crearPanelLateral();
		crearPanelInferior();
		
	}
	
	/**
	 * Usando este constructor, podemos crear distintas ventanas donde visualizar un mismo combate.
	 * Con el anterior se creaba un combate distinto por venana.
	 * @param c
	 */
	public VentanaJuego(Combate c) {
		
		this.miEquipo = c.getAliados();
		this.oponente = c.getOponentes();
		
		this.c = c;
		
		setTitle("POKEMON");
		setLocation(200, 100);
		setSize(800, 500);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		crearPanelFrontal();
		crearPanelLateral();
		crearPanelInferior();	
	}
	
	/* Se crea un panel donde van a estar contenidos otros 4 paneles y 
	 * en los inferiores apareceran los pokemon, su vida(JProgressbar),
	 * su estado(si es que tienen).
	 */
	private void crearPanelFrontal() {
		JPanel panel_central = new JPanel();
		panel_central.setLayout(new GridLayout(2, 2));
		
		JPanel panel_vacio_1 = new JPanel();
		JPanel panel_vacio_2 = new JPanel();
		JPanel panel_central_1 = new JPanel();
		JPanel panel_central_2 = new JPanel();
		
		vida_1 = new JProgressBar();
		vida_1.setValue(miEquipo.get(2).calcuPsPorcentaje());
		vida_1.setForeground(Color.GREEN);
		vida_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_central_1.add(vida_1, BorderLayout.NORTH);
		
		vida_2 = new JProgressBar();
		vida_2.setValue(oponente.get(2).calcuPsPorcentaje());
		vida_2.setForeground(Color.GREEN);
		vida_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_central_2.add(vida_2, BorderLayout.NORTH);
			
		panel_central.add(panel_vacio_1);
		panel_central.add(panel_vacio_2);
		panel_central.add(panel_central_1);
		panel_central.add(panel_central_2);
		add(panel_central, BorderLayout.CENTER);
		
	}
	/* Se crea un panel en cada lateral con el numero de pokemons que posee cada 
	 * entranador, ademas apereceran unos sprites por los pokemon. Cuando se 
	 * pasa el raton por encima se puede ver las caracteristicas del pokemon. Si se
	 * pulsa se puede cambiarde  pokemon.
	 */
	private void crearPanelLateral() {
		JPanel panel_entrenadores = new JPanel();
		JPanel panel_entrenadores_1 = new JPanel();
		
		//Imagen de pokeballs
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/pokeball.png"));
		ImageIcon iconoPokeball = new ImageIcon(icono.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_DEFAULT));
		
		panel_entrenadores.setLayout(new BorderLayout());
		panel_entrenadores_1.setLayout(new BorderLayout());
		
		/**
		 * Añadair un listener al label para que se cambie de pokemon.
		 */
		JPanel panel_pokeballs_J1 = new JPanel();
		panel_pokeballs_J1.setLayout(new GridLayout(6, 0));
		
		JPanel panel_pokeballs_J2 = new JPanel();
		panel_pokeballs_J2.setLayout(new GridLayout(6, 0));

		for (Pokemon p : miEquipo) { //Podríamos hacer que en vez de pokéballs saliesen los mini sprites de los pokes, para saber a quién se está cambiando.
			
			if (c.getpActivo() == p) {
				Pokeball ball = new Pokeball(iconoPokeball, p);
				ball.mostrarPoke();
				panel_pokeballs_J1.add(ball, BorderLayout.SOUTH);
			}
			else {
				Pokeball ball = new Pokeball(iconoPokeball, p);
				ball.addMouseListener( new MouseAdapter(){
					 
					@Override
					public void mouseClicked(MouseEvent e) {
						Point pPulsado = new Point(e.getPoint());
						esperar ++; //Habría que añadir un boolean para saber si el J1 está jugando o no.
						cambiarEstados();
						if (ball.getBounds().getCenterX() >= (pPulsado.getX())) {
							ball.mostrarPoke();
							c.getVentana().revalidate();
						}
					}

				});
				panel_pokeballs_J1.add(ball, BorderLayout.SOUTH);
			}
		}
		panel_entrenadores.add(panel_pokeballs_J1, BorderLayout.SOUTH);
		
		for (Pokemon p : oponente) { //Podríamos hacer que en vez de pokéballs saliesen los mini sprites de los pokes, para saber a quién se está cambiando.
			
			if (c.getpActivo() == p) {
				Pokeball ball = new Pokeball(iconoPokeball, p);
				ball.mostrarPoke();
				panel_pokeballs_J2.add(ball, BorderLayout.SOUTH);
			}
			else {
				Pokeball ball = new Pokeball(iconoPokeball, p);
				ball.addMouseListener( new MouseAdapter(){
					 
					@Override
					public void mouseClicked(MouseEvent e) {
						Point pPulsado = new Point(e.getPoint());
						esperar ++; //Habría que añadir un boolean para saber si el J1 está jugando o no.
						cambiarEstados();
						if (ball.getBounds().getCenterX() >= (pPulsado.getX())) {
							ball.mostrarPoke();
							c.getVentana().revalidate();
						}
					}
				});
				panel_pokeballs_J2.add(ball, BorderLayout.SOUTH);
			}
		}
		panel_entrenadores_1.add(panel_pokeballs_J2, BorderLayout.SOUTH);
		
		panel_entrenadores.setPreferredSize(new Dimension(100, 400));
		panel_entrenadores_1.setPreferredSize(new Dimension(100, 400));
		
		add(panel_entrenadores, BorderLayout.WEST);
		add(panel_entrenadores_1, BorderLayout.EAST);
	}

	/* Crear un JPanel donde van a estar conenido otros dos JPanel con los 
	 * movimientos de cada correspondiente pokemon.
	 */
	private void crearPanelInferior() {
		JPanel panel_inf = new JPanel();
		panel_inf.setLayout(new GridLayout(0, 2, 8, 0));
		
		panel_movimientos_1 = new JPanel();
		panel_movimientos_1.setBackground(Color.GRAY);
		panel_movimientos_1.setLayout(new FlowLayout());
		JButton l;
		
		for (Movimiento m :c.getpActivo().getMovimientos_poke()) {
			l = new JButton(m.getNombre());
			l.setName(m.getNombre());
			if(m.getTipo() == Tipo.SINIESTRO  || m.getTipo() == Tipo.ROCA || m.getTipo() == Tipo.FANTASMA) { l.setForeground(Color.white);} //Cambia la letra a blanco
			l.setBackground(m.getTipo().getColor());
			
			l.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//setEnabled(false); //NO PONERLO AQUÍ PORQUE SE BLOQUEA TODA LA VENTANA Y NO SE PUEDE CERRAR. AYUDA.
					for (Component boton : panel_movimientos_1.getComponents()) {
						((JButton)boton).setEnabled(false);
						if (((JButton)boton).getName() == m.getNombre()) {
							c.setMovActivo(m);
						}
					}
					esperar++;
					cambiarEstados();
				}

			});
			panel_movimientos_1.add(l);
		}
		
		panel_movimientos_2 = new JPanel();
		panel_movimientos_2.setBackground(Color.GRAY);
		panel_movimientos_2.setLayout(new FlowLayout());
		JButton l_1;
		
		for (Movimiento m : c.getpEnemigo().getMovimientos_poke()) {
			l_1 = new JButton(m.getNombre());
			l_1.setName(m.getNombre());
			if(m.getTipo() == Tipo.SINIESTRO  || m.getTipo() == Tipo.ROCA || m.getTipo() == Tipo.FANTASMA) { l_1.setForeground(Color.white);} //Cambia la letra a blanco
			l_1.setBackground(m.getTipo().getColor());
			l_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (Component boton : panel_movimientos_2.getComponents()) {
						((JButton)boton).setEnabled(false);
						if (((JButton)boton).getName() == m.getNombre()) {
							c.setMovEnemigo(m);
						}
					}
//					
//					for (Movimiento mov : c.getpEnemigo().getMovimientos_poke()) {
//						if(mov.getNombre() == l_1.getName()) {
//							System.out.println(l_1.getName());
//						}
				
					//}
					esperar++;
					cambiarEstados();	
				}
			});
			panel_movimientos_2.add(l_1);
		}
		panel_inf.add(panel_movimientos_1);
		panel_inf.add(panel_movimientos_2);
		add(panel_inf, BorderLayout.SOUTH);
	}
	
	private void cambiarEstados() {
		if (esperar == 2) {
			estado = EstadosJuego.CALCULANDO;
		}
		else {
			estado = EstadosJuego.ESPERANDO;
		}
		
	}
	

	public static ArrayList<Pokemon> getMiEquipo() {
		return miEquipo;
	}
	public static void setMiEquipo(ArrayList<Pokemon> miEquipo) {
		VentanaJuego.miEquipo = miEquipo;
	}
	public static ArrayList<Pokemon> getOponente() {
		return oponente;
	}
	public static void setOponente(ArrayList<Pokemon> oponente) {
		VentanaJuego.oponente = oponente;
	}
	public static VentanaJuego getVj() {
		return vj;
	}
	public static void setVj(VentanaJuego vj) {
		VentanaJuego.vj = vj;
	}
	
	public JProgressBar getVida_1() {
		return vida_1;
	}
	public JProgressBar getVida_2() {
		return vida_2;
	}
	
}
