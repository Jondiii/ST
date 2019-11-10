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
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		
		VentanaInicio vi = new VentanaInicio(c);
	}
	
	public static void creaEquiposPorDefecto() {
		Movimiento movi = new Movimiento("FUEGO", Tipo.FUEGO, 100, 100, 100, CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.ELEGIDO, null, 0, null);
		Movimiento movi2 = new Movimiento("PLANTA", Tipo.PLANTA, 100, 100, 100,CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.ELEGIDO, EstadosAlterados.CONGELADO, 20, null);
		Movimiento movi3 = new Movimiento("AGUA", Tipo.AGUA, 100, 100, 100,CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.ELEGIDO, null, 0, null);
		Movimiento movi4 = new Movimiento("ROCA", Tipo.ROCA, 100, 100, 100,CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.ELEGIDO, EstadosAlterados.ENVENENADO, 20, null);		
		
		ArrayList<Movimiento> m = new ArrayList<>();
		m.add(movi); m.add(movi2); m.add(movi3); m.add(movi4);
		
		miEquipo.add(new Pokemon("Gallade", 1, 1, "Probando", 100, 2, 2, 2, 2, 2, 2, m, Tipo.LUCHA, Tipo.PSIQUICO));
		miEquipo.add(new Pokemon("Raichu-alola", 1, 1, "Probando", 100, 1, 1, 1, 1, 1, 1, m, Tipo.ELECTRICO, Tipo.PSIQUICO));
		miEquipo.add(new Pokemon("Metagross", 1, 1, "yo", 100, 1, 1, 1, 1, 1, 1, m, Tipo.ACERO, Tipo.PSIQUICO));
		
		oponente.add(new Pokemon("Serperior", 1, 1, "Probando", 100, 1, 1, 1, 1, 3, 3, m, Tipo.PLANTA));
		oponente.add(new Pokemon("Dragonite", 1, 1, "Probando", 100, 1, 1, 1, 1, 1, 1, m, Tipo.DRAGON, Tipo.VOLADOR));
		oponente.add(new Pokemon("Aegislash", 1, 1, "yo", 100, 1, 1, 1, 1, 1, 1, m, Tipo.ACERO, Tipo.FANTASMA));
	}
	

}
