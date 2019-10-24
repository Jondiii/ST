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
import javax.swing.JDialog;
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
	public JPanel panel_j1;
	public JPanel panel_j2;
	public JPanel panel_poke_J1;
	public JPanel panel_poke_J2;
	public JPanel panel_vacio_1;
	public JPanel panel_vacio_2;
	public JLabel poke_1;
	public JLabel poke_2;
	private JDialog info_poke;

	
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
		
		setResizable(false);
		
		setTitle("POKEMON");
		setLocation(200, 100);
		setSize(800, 500);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
//		ImageIcon fondo = new ImageIcon(getClass().getResource("/img/"+ c.getpActivo().getNombre() +"_espaldas.png"));
//		this.setIconImage(fondo);
//		
		crearPanelFrontal();
		crearPanelLateral();
		crearPanelInferior();	
	}
	
	/** Se crea un panel donde van a estar contenidos otros 4 paneles y 
	 * en los inferiores apareceran los pokemon, su vida(JProgressbar),
	 * su estado(si es que tienen).
	 */
	private void crearPanelFrontal() {
		PanelConFondo panel_central = new PanelConFondo();
		panel_central.setImage("/img/campo_batalla_2.png");
		panel_central.setLayout(new GridLayout(2,2));
		
		
//		ImageIcon img_fondo = new ImageIcon(getClass().getResource("/img/campo_batalla_1.png"));
//		ImageIcon img_fondo_1 = new ImageIcon(img_fondo.getImage().getScaledInstance(400, 600, java.awt.Image.SCALE_DEFAULT));
//		JLabel img = new JLabel( img_fondo_1);
//		img.setSize(500, 500);
//		img.setLocation(0, 0);
//		panel_central.add(img); 
		
		panel_vacio_1 = new JPanel();
		panel_vacio_1.setOpaque(false);
		panel_vacio_2 = new JPanel();
		panel_vacio_2.setOpaque(false);
		panel_poke_J1 = new JPanel();
		panel_poke_J1.setOpaque(false);
		panel_poke_J2 = new JPanel();
		panel_poke_J2.setOpaque(false);
		
		
		vida_1 = new JProgressBar();
		vida_1.setValue(c.getpActivo().calcuPsPorcentaje());
		vida_1.setForeground(Color.GREEN);
		vida_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_poke_J1.add(vida_1, BorderLayout.NORTH);
		
		vida_2 = new JProgressBar();
		vida_2.setValue(c.getpEnemigo().calcuPsPorcentaje());
		vida_2.setForeground(Color.GREEN);
		vida_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_poke_J2.add(vida_2, BorderLayout.NORTH);
		
		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpActivo().getNombre() +"_espaldas.png"));
		ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(290, 290, java.awt.Image.SCALE_DEFAULT));
		poke_1 = new JLabel();
		poke_1.setIcon(icono_2);
		
		ImageIcon iconoo_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpEnemigo().getNombre() +"_frente.png"));
		ImageIcon iconoo_2 = new ImageIcon(iconoo_1.getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT));
		poke_2 = new JLabel();
		poke_2.setIcon(iconoo_2);
		
		panel_poke_J2.add(poke_2, BorderLayout.CENTER);
		panel_poke_J1.add(poke_1, BorderLayout.CENTER);
		
		panel_central.add(panel_vacio_1);
		panel_central.add(panel_poke_J2);
		panel_central.add(panel_poke_J1);
		panel_central.add(panel_vacio_2);
		
		
		add(panel_central, BorderLayout.CENTER);
		
		
	}
	/** Se crea un panel en cada lateral con el numero de pokemons que posee cada 
	 * entranador, ademas apereceran unos sprites por los pokemon. Cuando se 
	 * pasa el raton por encima se puede ver las caracteristicas del pokemon. Si se
	 * pulsa se puede cambiarde  pokemon.
	 */
	private void crearPanelLateral() {
		panel_j1 = new JPanel();
		panel_j2 = new JPanel();
		
		//Imagen de pokeballs
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/pokeball.png"));
		ImageIcon iconoPokeball = new ImageIcon(icono.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_DEFAULT));
		
		panel_j1.setLayout(new BorderLayout());
		panel_j2.setLayout(new BorderLayout());
		
		/**
		 * Añadair un listener al label para que se cambie de pokemon.
		 */
		JPanel panel_pokeballs_J1 = new JPanel();
		panel_pokeballs_J1.setLayout(new GridLayout(6, 0));
		
		JPanel panel_pokeballs_J2 = new JPanel();
		panel_pokeballs_J2.setLayout(new GridLayout(6, 0));

		for (Pokemon p : miEquipo) { //Podríamos hacer que en vez de pokéballs saliesen los mini sprites de los pokes, para saber a quién se está cambiando.
			
//			if (c.getpActivo() == p) {
//				Pokeball ball = new Pokeball(iconoPokeball, p);
//				ball.mostrarPoke();
//				panel_pokeballs_J1.add(ball, BorderLayout.SOUTH);
//			}
//			else {
			//el primer pokemon sino no tenia el listener. he cambiado la forma del point
			//es que a veces como que daaba error sino lo volvemos a poner
				Pokeball ball = new Pokeball(iconoPokeball, p);
				if (p == c.getpActivo()) {
					ball.mostrarPoke();
				}
				ball.addMouseListener( new MouseAdapter(){
					 
					@Override
					public void mouseClicked(MouseEvent e) {
						if(c.isJ1_accion_hecha()) return;
//						Point pPulsado = new Point(e.getPoint());
//						if (ball.getBounds().getCenterX() >= (pPulsado.getX())) {
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							c.setpActivo(ball.getPoke());
							ball.mostrarPoke();
							c.setJ1_cambia(true);
							c.setJ1_accion_hecha(true);
						}
						cambiarEstados();
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							Pokemon poke = ball.getPoke();
							info_poke = new JDialog();
							info_poke.add(new JLabel(poke.getNombre() + " " + (poke.getTipos()).toString()), BorderLayout.NORTH); //Pequeña prueba. Podemos	
																																  //hacer nuestro propio dialog
																																  //de ser necesario.
							info_poke.setSize(200, 200);
							info_poke.setLocation(0, 300);
							info_poke.setVisible(true);
							
						}
					}
					@Override
					public void mouseExited(MouseEvent e) {
						info_poke.dispose();
						
					}

				});
				panel_pokeballs_J1.add(ball, BorderLayout.SOUTH);
//			}
		}
		panel_j1.add(panel_pokeballs_J1, BorderLayout.SOUTH);
		
		for (Pokemon p : oponente) { //Podríamos hacer que en vez de pokéballs saliesen los mini sprites de los pokes, para saber a quién se está cambiando.
			
//			if (c.getpEnemigo() == p) {
//				Pokeball ball = new Pokeball(iconoPokeball, p);
//				ball.mostrarPoke();
//				panel_pokeballs_J2.add(ball, BorderLayout.SOUTH);
//			}
//			else {
			
				Pokeball ball = new Pokeball(iconoPokeball, p);
				if (c.getpEnemigo() == p) {
					ball.mostrarPoke();
				}
				ball.addMouseListener( new MouseAdapter(){
					 
					@Override
					public void mouseClicked(MouseEvent e) {
						if(c.isJ2_accion_hecha()) return;
//						Point pPulsado = new Point(e.getPoint());
//						if (ball.getBounds().getCenterX() >= (pPulsado.getX())) {
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							ball.mostrarPoke();
							c.setpEnemigo(ball.getPoke());
							c.setJ2_cambia(true);
							c.setJ2_accion_hecha(true);
							}
						cambiarEstados();
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							info_poke = new JDialog();
							info_poke.setSize(200, 200);
							info_poke.setLocation(1000, 300);
							info_poke.setVisible(true);
						}
					}
					@Override
					public void mouseExited(MouseEvent e) {
						info_poke.dispose();
						
					}
				});
				panel_pokeballs_J2.add(ball, BorderLayout.SOUTH);
//			}
		}
		panel_j2.add(panel_pokeballs_J2, BorderLayout.SOUTH);
		
		panel_j1.setPreferredSize(new Dimension(100, 400));
		panel_j2.setPreferredSize(new Dimension(100, 400));
		
		add(panel_j1, BorderLayout.WEST);
		add(panel_j2, BorderLayout.EAST);
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
					if(c.isJ1_accion_hecha()) return;
					//setEnabled(false); //NO PONERLO AQUÍ PORQUE SE BLOQUEA TODA LA VENTANA Y NO SE PUEDE CERRAR. AYUDA.
//					panel_j1.setEnabled(false);
					for (Component boton : panel_movimientos_1.getComponents()) {
//						((JButton)boton).setEnabled(false); 
						if (((JButton)boton).getName() == m.getNombre()) {
							c.setMovActivo(m);
						}
					}
					c.setJ1_accion_hecha(true);
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
					if(c.isJ2_accion_hecha()) return;
					panel_j2.setEnabled(false);
					for (Component boton : panel_movimientos_2.getComponents()) {
						((JButton)boton).setEnabled(false);
						if (((JButton)boton).getName() == m.getNombre()) {
							c.setMovEnemigo(m);
						}
					}
					
					c.setJ2_accion_hecha(true);

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
//		if (esperar == 2) {
//			estado = EstadosJuego.CALCULANDO;
//		}
//		else {
//			estado = EstadosJuego.ESPERANDO;
//		}

		if (c.isJ1_accion_hecha() == true && c.isJ2_accion_hecha() == true) {
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
	
	public static int getEsperar() {
		return esperar;
	}

	public static void setEsperar(int esperar) {
		VentanaJuego.esperar = esperar;
	}

	public static EstadosJuego getEstado() {
		return estado;
	}

	public static void setEstado(EstadosJuego estado) {
		VentanaJuego.estado = estado;
	}

	public static JPanel getPanel_movimientos_1() {
		return panel_movimientos_1;
	}

	public static void setPanel_movimientos_1(JPanel panel_movimientos_1) {
		VentanaJuego.panel_movimientos_1 = panel_movimientos_1;
	}

	public static JPanel getPanel_movimientos_2() {
		return panel_movimientos_2;
	}

	public static void setPanel_movimientos_2(JPanel panel_movimientos_2) {
		VentanaJuego.panel_movimientos_2 = panel_movimientos_2;
	}

	public JPanel getPanel_j1() {
		return panel_j1;
	}

	public void setPanel_j1(JPanel panel_j1) {
		this.panel_j1 = panel_j1;
	}

	public JPanel getPanel_j2() {
		return panel_j2;
	}

	public void setPanel_j2(JPanel panel_j2) {
		this.panel_j2 = panel_j2;
	}

	public JPanel getPanel_poke_J1() {
		return panel_poke_J1;
	}

	public void setPanel_poke_J1(JPanel panel_poke_J1) {
		this.panel_poke_J1 = panel_poke_J1;
	}

	public JPanel getPanel_poke_J2() {
		return panel_poke_J2;
	}

	public void setPanel_poke_J2(JPanel panel_poke_J2) {
		this.panel_poke_J2 = panel_poke_J2;
	}

	public JPanel getPanel_vacio_1() {
		return panel_vacio_1;
	}

	public void setPanel_vacio_1(JPanel panel_vacio_1) {
		this.panel_vacio_1 = panel_vacio_1;
	}

	public JLabel getPoke_1() {
		return poke_1;
	}

	public void setPoke_1(JLabel poke_1) {
		this.poke_1 = poke_1;
	}

	public JLabel getPoke_2() {
		return poke_2;
	}

	public void setPoke_2(JLabel poke_2) {
		this.poke_2 = poke_2;
	}

	public JPanel getPanel_vacio_2() {
		return panel_vacio_2;
	}

	public void setPanel_vacio_2(JPanel panel_vacio_2) {
		this.panel_vacio_2 = panel_vacio_2;
	}

	public Combate getC() {
		return c;
	}

	public void setC(Combate c) {
		this.c = c;
	}

	public void setVida_1(JProgressBar vida_1) {
		this.vida_1 = vida_1;
	}

	public void setVida_2(JProgressBar vida_2) {
		this.vida_2 = vida_2;
	}

	public JProgressBar getVida_1() {
		return vida_1;
	}
	public JProgressBar getVida_2() {
		return vida_2;
	}
	
}
