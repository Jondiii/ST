package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.Serializable;
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
import principal.Combate;
import principal.EstadosAlterados;
import principal.HistorialCombate;
import principal.Movimiento;
import principal.MuestraInfoPoke;
import principal.PanelConFondo;
import principal.PanelMovimiento;
import principal.Pokeball;
import principal.Pokemon;
import principal.Tipo;

public class VentanaJuego extends JFrame implements Serializable {
	

	private static final long serialVersionUID = 1L;
	public static int esperar = 0;
	public static EstadosJuego estado;
	
	private static ArrayList<Pokemon> miEquipo = new ArrayList<>();
	private static ArrayList<Pokemon> oponente = new ArrayList<>();
	private static VentanaJuego vj;
	public static JPanel movimientos_P1  = new JPanel();
	public static JPanel movimientos_P2  = new JPanel();
	public JPanel panelInfo  = new JPanel();
	public JPanel panel_j1;
	public JPanel panel_j2;
	public JPanel panel_poke_J1;
	public JPanel panel_poke_J2;
	public JPanel panel_vacio_1;
	public JPanel panel_vacio_2;
	public JPanel panelInferior = new JPanel(new BorderLayout());
	public JLabel poke_1;
	public JLabel poke_2;
	public JLabel estadoAlterado1 = new JLabel("");
	public JLabel estadoAlterado2 = new JLabel("");
	private MuestraInfoPoke info_poke;
	private JFrame info_mov;

	private VentanaJuego v;
	
	private JProgressBar vida_1;
	private JProgressBar vida_2;
	
	private Combate c;
	private JButton historial;
	private HistorialCombate hc;
	
	public VentanaJuego(ArrayList<Pokemon> miPokemon, ArrayList<Pokemon> oponente) {
		v = this;
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
		v = this;
		this.miEquipo = c.getAliados();
		this.oponente = c.getOponentes();
		
		this.c = c;
		hc = new HistorialCombate();
		
		setResizable(false);
		
		setTitle("Pokemon Stars");
		setLocation(200, 100);
		setSize(850, 500);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

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
		panel_poke_J1.add(estadoAlterado1, BorderLayout.NORTH);
		panel_poke_J1.add(vida_1, BorderLayout.NORTH);

		
		vida_2 = new JProgressBar();
		vida_2.setValue(c.getpEnemigo().calcuPsPorcentaje());
		vida_2.setForeground(Color.GREEN);
		vida_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_poke_J2.add(vida_2, BorderLayout.NORTH);
		panel_poke_J2.add(estadoAlterado2, BorderLayout.NORTH);

		ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpActivo().getNombre() +"_espaldas.png"));
		ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(290, 290, java.awt.Image.SCALE_DEFAULT));
		poke_1 = new JLabel();
		poke_1.setIcon(icono_2);
		
		ImageIcon iconoo_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpEnemigo().getNombre() +"_frente.png"));
		ImageIcon iconoo_2 = new ImageIcon(iconoo_1.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT));
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
		 * Añadir un listener al label para que se cambie de pokemon.
		 */
		JPanel panel_pokeballs_J1 = new JPanel();
		panel_pokeballs_J1.setLayout(new GridLayout(6, 1));
		
		JPanel panel_pokeballs_J2 = new JPanel();
		panel_pokeballs_J2.setLayout(new GridLayout(6, 1));

		for (Pokemon p : miEquipo) { 
				Pokeball ball = new Pokeball(iconoPokeball, p);
				if (p == c.getpActivo()) {
					ball.mostrarPoke();
				}
				ball.addMouseListener( new MouseAdapter(){
					 
					@Override
					public void mouseClicked(MouseEvent e) {
						if (ball.getPoke().getEstado() == EstadosAlterados.DEBILITADO || ball.getPoke() == c.getpActivo()) return;
						if(c.isJ1_accion_hecha() || c.getpEnemigo().getEstado() == EstadosAlterados.DEBILITADO) return;
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							c.setpActivo(ball.getPoke());
							ball.mostrarPoke();
							c.setJ1_cambia(true);
							c.setJ1_accion_hecha(true);
						}
						if (estado == EstadosJuego.POKE_DEBILITADO) {
							return;
						}else {
						cambiarEstados();
						}
						info_poke.dispose();
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							info_poke = new MuestraInfoPoke(ball);
							info_poke.setSize(200, 200);
							info_poke.setLocation(v.getLocation().x - 200, v.getLocation().y + 200);
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
		
		for (Pokemon p : oponente) {
			
				Pokeball ball = new Pokeball(iconoPokeball, p);
				if (c.getpEnemigo() == p) {
					ball.mostrarPoke();
				}
				ball.addMouseListener( new MouseAdapter(){
					 
					@Override
					public void mouseClicked(MouseEvent e) {
						if (ball.getPoke().getEstado() == EstadosAlterados.DEBILITADO || ball.getPoke() == c.getpEnemigo()) return;
						if(c.isJ2_accion_hecha() || c.getpActivo().getEstado() == EstadosAlterados.DEBILITADO) return;
//						Point pPulsado = new Point(e.getPoint());
//						if (ball.getBounds().getCenterX() >= (pPulsado.getX())) {
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							ball.mostrarPoke();
							c.setpEnemigo(ball.getPoke());
							c.setJ2_cambia(true);
							c.setJ2_accion_hecha(true);
							}
						if (estado == EstadosJuego.POKE_DEBILITADO) {
							return;
						}else {
						cambiarEstados();
						}
						info_poke.dispose();

					}
					@Override
					public void mouseEntered(MouseEvent e) {
						Component comp = e.getComponent();
						if (comp instanceof JLabel) {
							info_poke = new MuestraInfoPoke(ball);
							info_poke.setSize(200, 200);
							info_poke.setLocation(v.getLocation().x + 850, v.getLocation().y + 200);
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

	/** Crear un JPanel donde van a estar conenido otros dos JPanel con los 
	 * movimientos de cada correspondiente pokemon.
	 */
	private void crearPanelInferior() {
		panelInfo.setPreferredSize(new Dimension(850, 25));
		JPanel panel_movimientos = new JPanel();
		panel_movimientos.setLayout(new GridLayout(0, 2, 8, 0));
		
		movimientos_P1 = new JPanel();
		movimientos_P1.setBackground(Color.GRAY);
		movimientos_P1.setLayout(new FlowLayout());
		JButton l;
		
		for (Movimiento m :c.getpActivo().getMovimientos_poke()) {
			l = new JButton(m.getNombre());
			l.setName(m.getNombre());
			if(m.getTipo() == Tipo.SINIESTRO  || m.getTipo() == Tipo.ROCA || m.getTipo() == Tipo.FANTASMA || m.getTipo() == Tipo.AGUA) {
				l.setForeground(Color.white);} //Cambia la letra a blanco
			l.setBackground(m.getTipo().getColor());
			
			l.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(c.isJ1_accion_hecha() || c.getpActivo().getEstado() == EstadosAlterados.DEBILITADO || c.getpEnemigo().getEstado() == EstadosAlterados.DEBILITADO) return;
					//setEnabled(false); //NO PONERLO AQUÍ PORQUE SE BLOQUEA TODA LA VENTANA Y NO SE PUEDE CERRAR. AYUDA.
//					panel_j1.setEnabled(false);
					for (Component boton : movimientos_P1.getComponents()) {
//						((JButton)boton).setEnabled(false); 
						if (((JButton)boton).getName() == m.getNombre()) {
							c.setMovActivo(m);
						}
					}
					c.setJ1_accion_hecha(true);
					cambiarEstados();
				}

			});
			
			l.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					Component comp = e.getComponent();
					if (comp instanceof JButton) {
						info_mov = new JFrame();
						info_mov.add(new PanelMovimiento(m));
						info_mov.setLocation(v.getLocation().x, v.getLocation().y + 500);
						info_mov.setSize(300, 115);
						info_mov.setVisible(true);
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					Component comp = e.getComponent();
					if (comp instanceof JButton) {
						info_mov.dispose();
					}
					
				}
			});
			
			movimientos_P1.add(l);
		}
		
		movimientos_P2 = new JPanel();
		movimientos_P2.setBackground(Color.GRAY);
		movimientos_P2.setLayout(new FlowLayout());
		JButton l_1;
		
		for (Movimiento m : c.getpEnemigo().getMovimientos_poke()) {
			l_1 = new JButton(m.getNombre());
			l_1.setName(m.getNombre());
			if(m.getTipo() == Tipo.SINIESTRO  || m.getTipo() == Tipo.ROCA || m.getTipo() == Tipo.FANTASMA || m.getTipo() == Tipo.AGUA) 
			{ l_1.setForeground(Color.white);} //Cambia la letra a blanco
			l_1.setBackground(m.getTipo().getColor());
			l_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(c.isJ2_accion_hecha() || c.getpActivo().getEstado() == EstadosAlterados.DEBILITADO || c.getpEnemigo().getEstado() == EstadosAlterados.DEBILITADO) return;
					//panel_j2.setEnabled(false);
					for (Component boton : movimientos_P2.getComponents()) {
						//((JButton)boton).setEnabled(false);
						if (((JButton)boton).getName() == m.getNombre()) {
							c.setMovEnemigo(m);
						}
					}
					
					c.setJ2_accion_hecha(true);

					//esperar++;
					cambiarEstados();	
				}
			});
			
			l_1.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					Component comp = e.getComponent();
					if (comp instanceof JButton) {
						info_mov = new JFrame();
						info_mov.add(new PanelMovimiento(m));
						info_mov.setLocation(v.getLocation().x + 500, v.getLocation().y + 500);
						info_mov.setSize(300, 115);
						info_mov.setVisible(true);
					}
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					Component comp = e.getComponent();
					if (comp instanceof JButton) {
						info_mov.dispose();
					}
					
				}
			});
			movimientos_P2.add(l_1);
		}
		
		panel_movimientos.add(movimientos_P1);
		panel_movimientos.add(movimientos_P2);
		panelInferior.add(panel_movimientos, BorderLayout.NORTH);
		panelInferior.add(panelInfo, BorderLayout.SOUTH);
		add(panelInferior, BorderLayout.SOUTH);
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
	
	public void cambiaPanelInfo(String info) {
		System.out.println(info);
		hc.añadirPanel(info);
		hc.revalidate();
		v.getPanelInfo().setLayout(new BorderLayout());
		JPanel central = new JPanel();
		central.removeAll();
		v.revalidate();	
		v.repaint();//Hay que hacer el revalidate 2 veces, si no el mensaje anterior se queda.
		JLabel info_label = new JLabel(info);
		central.add(info_label);
		v.getPanelInfo().add(central, BorderLayout.CENTER);
		JButton historial = new JButton("HISTORIAL");
		v.getPanelInfo().add(historial, BorderLayout.EAST);
		historial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hc.setVisible(true);
			}
		});
		v.revalidate();
	}
	
	public void cambiaMovimientos() {
		if (panelInferior != null) panelInferior.removeAll();
		v.crearPanelInferior();
		//System.out.println(c.getpActivo().getMovimientos_poke().get(0).getNombre());
	}
	

	public static ArrayList<Pokemon> getMiEquipo() {
		return miEquipo;
	}
	public static void setMiEquipo(ArrayList<Pokemon> miEquipo) {
		VentanaJuego.miEquipo = miEquipo;
	}
	public JPanel getPanelInferior() {
		return panelInferior;
	}

	public void setPanelInferior(JPanel panelInferior) {
		this.panelInferior = panelInferior;
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

	public JFrame getInfo_mov() {
		return info_mov;
	}

	public JButton getHistorial() {
		return historial;
	}

	public HistorialCombate getHc() {
		return hc;
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
		return movimientos_P1;
	}

	public static void setPanel_movimientos_1(JPanel panel_movimientos_1) {
		VentanaJuego.movimientos_P1 = panel_movimientos_1;
	}

	public static JPanel getPanel_movimientos_2() {
		return movimientos_P2;
	}

	public static void setPanel_movimientos_2(JPanel panel_movimientos_2) {
		VentanaJuego.movimientos_P2 = panel_movimientos_2;
	}

	public static JPanel getMovimientos_P1() {
		return movimientos_P1;
	}

	public static void setMovimientos_P1(JPanel movimientos_P1) {
		VentanaJuego.movimientos_P1 = movimientos_P1;
	}

	public static JPanel getMovimientos_P2() {
		return movimientos_P2;
	}

	public static void setMovimientos_P2(JPanel movimientos_P2) {
		VentanaJuego.movimientos_P2 = movimientos_P2;
	}

	public JLabel getEstadoAlterado1() {
		return estadoAlterado1;
	}

	public void setEstadoAlterado1(JLabel estadoAlterado1) {
		this.estadoAlterado1 = estadoAlterado1;
	}

	public JLabel getEstadoAlterado2() {
		return estadoAlterado2;
	}

	public void setEstadoAlterado2(JLabel estadoAlterado2) {
		this.estadoAlterado2 = estadoAlterado2;
	}

	public MuestraInfoPoke getInfo_poke() {
		return info_poke;
	}

	public void setInfo_poke(MuestraInfoPoke info_poke) {
		this.info_poke = info_poke;
	}

	public VentanaJuego getV() {
		return v;
	}

	public void setV(VentanaJuego v) {
		this.v = v;
	}

	public JPanel getPanelInfo() {
		return panelInfo;
	}

	public void setPanelInfo(JPanel panelInfo) {
		this.panelInfo = panelInfo;
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

	public void setInfo_mov(JFrame info_mov) {
		this.info_mov = info_mov;
	}

	public void setHistorial(JButton historial) {
		this.historial = historial;
	}

	public void setHc(HistorialCombate hc) {
		this.hc = hc;
	}
}
