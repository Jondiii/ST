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
	
	private Pokemon miPokemon;
	private Pokemon oponente;
	
	public VentanaJuego(Pokemon miPokemon, Pokemon oponente) {
		
		setTitle("POKEMON");
		setLocation(200, 100);
		setSize(800, 500);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		
		this.miPokemon = miPokemon;
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
		for (Movimiento m : miPokemon.getMovimientos_poke()) {
			l = new JButton(m.getNombre());
			panel_movimientos_1.add(l);
		}
		JPanel panel_movimientos_2 = new JPanel();
		panel_movimientos_2.setBackground(Color.GRAY);
		panel_movimientos_2.setLayout(new FlowLayout());
		JButton l_1;
		for (Movimiento m : oponente.getMovimientos_poke()) {
			l_1 = new JButton(m.getNombre());
			panel_movimientos_2.add(l_1);
		}
		
		panel_inf.add(panel_movimientos_1);
		panel_inf.add(panel_movimientos_2);
		add(panel_inf, BorderLayout.SOUTH);
	}
	public static void main(String[] args){
		
		
		Movimiento movi = new Movimiento("FUEGO", Tipo.ACERO, 100, 100, 100);
		Movimiento movi2 = new Movimiento("FUEGO", Tipo.ACERO, 100, 100, 100);
		Movimiento movi3 = new Movimiento("FUEGO", Tipo.ACERO, 100, 100, 100);
		Movimiento movi4 = new Movimiento("FUEGO", Tipo.ACERO, 100, 100, 100);
		
		
		ArrayList<Movimiento> m = new ArrayList<>();
		m.add(movi);
		m.add(movi2);
		Pokemon p = new Pokemon("Torterra", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, 1, m, Tipo.ACERO);
		
		VentanaJuego vj = new VentanaJuego(p, p);

		vj.setVisible(true);
	}
}
