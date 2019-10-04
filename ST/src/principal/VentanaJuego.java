package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		crearPanelInferior();
		
	}
	
	/* Crear un JPanel donde van a estar conenido otros dos JPanel con los 
	 * movimientos de cada correspondiente pokemon.
	 * 
	 */
	public void crearPanelInferior() {
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
		
		Movimiento movi = new Movimiento("TIERRA", Tipo.TIERRA, 100, 100, 100);
		Movimiento movi2 = new Movimiento("FANTASMA", Tipo.FANTASMA, 100, 100, 100);
		Movimiento movi3 = new Movimiento("SINIESTRO", Tipo.SINIESTRO, 100, 100, 100);
		Movimiento movi4 = new Movimiento("ROCA", Tipo.ROCA, 100, 100, 100);		
		
		ArrayList<Movimiento> m = new ArrayList<>();
		m.add(movi); m.add(movi2); m.add(movi3); m.add(movi4);
		
		miEquipo.add(new Pokemon("Torterra", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, 1, m, Tipo.TIERRA));
		miEquipo.add(new Pokemon("Pikachu", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, 1, m, Tipo.ELECTRICO));
		miEquipo.add(new Pokemon("Froslass", 1, 1, "yo", 1, 1, 1, 1, 1, 1, 1, m, Tipo.HIELO));
		
		oponente.add(new Pokemon("Lumineon", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, 1, m, Tipo.AGUA));
		oponente.add(new Pokemon("Charizard", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, 1, m, Tipo.DRAGON));
		oponente.add(new Pokemon("Aegislash", 1, 1, "yo", 1, 1, 1, 1, 1, 1, 1, m, Tipo.ACERO));
		
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
