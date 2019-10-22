package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.UIManager;

import principal.*;

public class Main {
	
	public static boolean cerrada = false;
	
	static ArrayList<Pokemon>  miEquipo = new ArrayList<>();
	static ArrayList<Pokemon> oponente = new ArrayList<>();

	public static void main(String[] args) {
		
		creaEquiposPorDefecto();
		Combate c = new Combate(miEquipo, oponente);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		VentanaJuego vj = c.getVentana();
		vj.setVisible(true);
		vj.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				cerrada = true;
			}
		});
		
	//	Combate c = new Combate(); //no hace falta que se cree aqui si no cuando se pulsan los botones.
		MiHilo mh = new MiHilo(c, vj);
		mh.start();
	}
	
	public static void creaEquiposPorDefecto() {
		Movimiento movi = new Movimiento("TIERRA", Tipo.TIERRA, 100, 100, 100, CategoriaMov.ESPECIAL, 0);
		Movimiento movi2 = new Movimiento("FANTASMA", Tipo.FANTASMA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi3 = new Movimiento("SINIESTRO", Tipo.SINIESTRO, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi4 = new Movimiento("ROCA", Tipo.ROCA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);		
		
		ArrayList<Movimiento> m = new ArrayList<>();
		m.add(movi); m.add(movi2); m.add(movi3); m.add(movi4);
		
		miEquipo.add(new Pokemon("Froslass", 1, 1, "Probando", 600, 2, 2, 2, 2, 2, 2, m, Tipo.TIERRA));
		miEquipo.add(new Pokemon("Raichu-alola", 1, 1, "Probando", 1000, 1, 1, 1, 1, 1, 1, m, Tipo.ELECTRICO));
		miEquipo.add(new Pokemon("Metagross", 1, 1, "yo", 1000, 1, 1, 1, 1, 1, 1, m, Tipo.HIELO));
		
		oponente.add(new Pokemon("Serperior", 1, 1, "Probando", 350, 1, 1, 1, 1, 1, 1, m, Tipo.AGUA));
		oponente.add(new Pokemon("Dragonite", 1, 1, "Probando", 100, 1, 1, 1, 1, 1, 1, m, Tipo.DRAGON));
		oponente.add(new Pokemon("Aegislash", 1, 1, "yo", 1000, 1, 1, 1, 1, 1, 1, m, Tipo.ACERO));
	}
	

}
