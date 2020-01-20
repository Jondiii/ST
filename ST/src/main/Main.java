package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.UIManager;

import database.BaseDatosPoke;
import principal.*;
import ventanas.VentanaInicio;

public class Main {
	
	public static boolean cerrada = false;
	
	public static ArrayList<Pokemon>  miEquipo = new ArrayList<>();
	public static ArrayList<Pokemon> oponente = new ArrayList<>();
	
	public static void main(String[] args) {
		creaEquiposPorDefecto();

		Combate c = new Combate(miEquipo, oponente); 
		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e){
			e.printStackTrace();
		}
		
		VentanaInicio vi = new VentanaInicio(c);
	}
	
	public static void creaEquiposPorDefecto() {
		EquiposPorDefecto e = new EquiposPorDefecto();
		miEquipo = e.devuelveEquipo();
		oponente = e.devuelveEquipo();
	}
	
	public static void basesDatosCargarPoke() {
//		BaseDatosPoke.crearTabla();
		try {
			
			// Leemos el fichero de pokemons e insertamos primero el pokemon en su tabla, y luego, los movimientos del pokemon en la tabla PokemonMovimiento
			BufferedReader br = new BufferedReader(new FileReader("Data/web/poke_info.csv"));
			int id = 1;
			String line = null;
			while((line = br.readLine()) != null) {
				String [] datos = line.split(";");
				String nombre = datos[0];
				int [] stats = new int[6];
				for(int i =0;i<stats.length;i++) {
					stats[i] = Integer.parseInt(datos[i+1]);
				}
				double altura = Double.parseDouble(datos[7]);
				double peso = Double.parseDouble(datos[8]);
				
				String [] ataques = new String[10];
				for(int i = 0; i < ataques.length; i++) {
					ataques[i] = datos[9+i];
				}
				
				String [] tipos = new String[2];
				for(int i = 0; i < tipos.length; i++) {
					tipos[i] = datos[i + 19];
					if (datos.length  == 20)
						break;
				}
				
				BaseDatosPoke.pokemonInsert(id , nombre, stats, altura, peso, ataques, tipos);
				id ++;	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void basesDatosCargarMov() {
		try {
			
			// Leemos el fichero de pokemons e insertamos primero el pokemon en su tabla, y luego, los movimientos del pokemon en la tabla PokemonMovimiento
			BufferedReader br = new BufferedReader(new FileReader("Data/web/mov_info.csv"));
			int id = 0;
			int dano = 0;
			int precision = 0;
			String line = null;
			while((line = br.readLine()) != null) {
				String [] datos = line.split(";");
				String nombre = datos[0];
				String alcance = datos[1];
				int prioridad = Integer.parseInt(datos[2]);
				String mov_tipo = datos[3];
				String categoria = datos[4];
				if (datos[5].equals("null")) {
					dano = 0;
				}else {
					dano = Integer.parseInt(datos[5]);
				}
				if (datos[6].equals("null")) {
					precision = 0;
				}else {
					precision = Integer.parseInt(datos[6]);
				}
				int pp = Integer.parseInt(datos[7]);
				String estado_alterno = datos[8];
				String estado_secunf = datos[9];
				
				double [] probabilidades = new double [3];
				for(int i = 0; i < probabilidades.length; i++) {
					probabilidades[i] = Double.parseDouble(datos[10+i]);
				}
				String cambioStat = datos[13];
				int [] stats = new int [5];
				for(int i = 0; i < stats.length; i++) {
					stats[i] = Integer.parseInt(datos[14+i]);
				}
				id++;
				
				BaseDatosPoke.movInsert(id,nombre, alcance, prioridad, dano, precision, pp, categoria, mov_tipo, estado_alterno, 
						estado_secunf, probabilidades, cambioStat, stats);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
