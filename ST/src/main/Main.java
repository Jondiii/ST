package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import principal.*;

public class Main {
	
	public static boolean cerrada = false;

	public static void main(String[] args) {
		
		Movimiento movi = new Movimiento("TIERRA", Tipo.TIERRA, 100, 100, 100, CategoriaMov.ESPECIAL, 0);
		Movimiento movi2 = new Movimiento("FANTASMA", Tipo.FANTASMA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi3 = new Movimiento("SINIESTRO", Tipo.SINIESTRO, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi4 = new Movimiento("ROCA", Tipo.ROCA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);		
		
		ArrayList<Movimiento> m = new ArrayList<>();
		m.add(movi); m.add(movi2); m.add(movi3); m.add(movi4);
		
		ArrayList<Pokemon> miEquipo = new ArrayList<>();
		ArrayList<Pokemon> oponente = new ArrayList<>();
		
		miEquipo.add(new Pokemon("Torterra", 1, 1, "Probando", 600, 1, 1, 1, 1, 1, 1, m, Tipo.TIERRA));
		miEquipo.add(new Pokemon("Pikachu", 1, 1, "Probando", 1000, 1, 1, 1, 1, 1, 1, m, Tipo.ELECTRICO));
		miEquipo.add(new Pokemon("Froslass", 1, 1, "yo", 1000, 1, 1, 1, 1, 1, 1, m, Tipo.HIELO));
		
		oponente.add(new Pokemon("Lumineon", 1, 1, "Probando", 350, 1, 1, 1, 1, 1, 1, m, Tipo.AGUA));
		oponente.add(new Pokemon("Charizard", 1, 1, "Probando", 100, 1, 1, 1, 1, 1, 1, m, Tipo.DRAGON));
		oponente.add(new Pokemon("Aegislash", 1, 1, "yo", 1000, 1, 1, 1, 1, 1, 1, m, Tipo.ACERO));
		
		VentanaJuego vj = new VentanaJuego(miEquipo, oponente);
		vj.setVisible(true);
		vj.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				cerrada = true;
			}
		});
		Combate c = new Combate();// no hace falta que se cree aqui si no cuando se pulsan los botones.
		MiHilo mh = new MiHilo(c, vj);
		mh.start();
	}
	

}
