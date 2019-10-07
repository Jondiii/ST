package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;


public class VentanaJuego extends JFrame{
	
	private static ArrayList<Pokemon> miEquipo = new ArrayList<>();
	private static ArrayList<Pokemon> oponente = new ArrayList<>();
	private static VentanaJuego vj;
	
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
		
		JProgressBar vida_1 = new JProgressBar();
		vida_1.setValue(miEquipo.get(0).calcuPsPorcentaje());
		vida_1.setForeground(Color.GREEN);
		vida_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_central_1.add(vida_1, BorderLayout.NORTH);
		
		JProgressBar vida_2 = new JProgressBar();
		vida_2.setValue(oponente.get(0).calcuPsPorcentaje());
		vida_2.setForeground(Color.GREEN);
		vida_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel_central_2.add(vida_2, BorderLayout.NORTH);
		
		
		panel_central.add(panel_vacio_1);
		panel_central.add(panel_vacio_2);
		panel_central.add(panel_central_1);
		panel_central.add(panel_central_2);
		add(panel_central, BorderLayout.CENTER);
		
	}

	private void crearPanelLateral() {
		
		
	}

	/* Crear un JPanel donde van a estar conenido otros dos JPanel con los 
	 * movimientos de cada correspondiente pokemon.
	 */
	private void crearPanelInferior() {
		JPanel panel_inf = new JPanel();
		panel_inf.setLayout(new GridLayout(0, 2, 8, 0));
		
		JPanel panel_movimientos_1 = new JPanel();
		panel_movimientos_1.setBackground(Color.GRAY);
		panel_movimientos_1.setLayout(new FlowLayout());
		JButton l;
		
		for (Movimiento m : miEquipo.get(0).getMovimientos_poke()) {
			l = new JButton(m.getNombre());
			if(m.getTipo() == Tipo.SINIESTRO  || m.getTipo() == Tipo.ROCA || m.getTipo() == Tipo.FANTASMA) { l.setForeground(Color.white);} //Cambia la letra a blanco
			l.setBackground(m.getTipo().getColor());
			l.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Me has pulsado");
				}				
			});
			panel_movimientos_1.add(l);
		}
		
		JPanel panel_movimientos_2 = new JPanel();
		panel_movimientos_2.setBackground(Color.GRAY);
		panel_movimientos_2.setLayout(new FlowLayout());
		JButton l_1;
		
		for (Movimiento m : oponente.get(0).getMovimientos_poke()) {
			l_1 = new JButton(m.getNombre());
			if(m.getTipo() == Tipo.SINIESTRO  || m.getTipo() == Tipo.ROCA || m.getTipo() == Tipo.FANTASMA) { l_1.setForeground(Color.white);} //Cambia la letra a blanco
			l_1.setBackground(m.getTipo().getColor());
			l_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Me has pulsado");
				}				
			});
			panel_movimientos_2.add(l_1);
		}
		
		panel_inf.add(panel_movimientos_1);
		panel_inf.add(panel_movimientos_2);
		add(panel_inf, BorderLayout.SOUTH);
	}
	
	/**
	 * Crea un equipo por defecto, para hacer pruebas antes de implementar el creador de equipos y la base de datos.
	 */
	public static void creaEquipoPrueba() {
		
		Movimiento movi0 = new Movimiento("TIERRA", Tipo.TIERRA, 100, 100, 100, CategoriaMov.ESPECIAL, 0);
		Movimiento movi1 = new Movimiento("FANTASMA", Tipo.FANTASMA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi2 = new Movimiento("SINIESTRO", Tipo.SINIESTRO, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi3 = new Movimiento("ROCA", Tipo.ROCA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);		
		
	
		Movimiento[] movimientos = new Movimiento[4];
		movimientos[0] = movi0;
		movimientos[1] = movi0;
		movimientos[2] = movi0;
		movimientos[3] = movi0;
		
		
		miEquipo.add(new Pokemon("Torterra", 1, 1, "Probando", 100, 1, 1, 1, 1, 1, movimientos, Tipo.TIERRA));
		miEquipo.add(new Pokemon("Pikachu", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, movimientos, Tipo.ELECTRICO));
		miEquipo.add(new Pokemon("Froslass", 1, 1, "yo", 1, 1, 1, 1, 1, 1, movimientos, Tipo.HIELO));
		
		oponente.add(new Pokemon("Lumineon", 1, 1, "Probando", 50, 1, 1, 1, 1, 1, movimientos, Tipo.AGUA));
		oponente.add(new Pokemon("Charizard", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, movimientos, Tipo.DRAGON));
		oponente.add(new Pokemon("Aegislash", 1, 1, "yo", 1, 1, 1, 1, 1, 1, movimientos, Tipo.ACERO));
		
		vj = new VentanaJuego(miEquipo, oponente);

		vj.setVisible(true);
	}
	
	public static void main(String[] args){
		VentanaJuego.creaEquipoPrueba();
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
	
	
}
