package principal;

import java.util.ArrayList;

/**
 * Clase que gestiona toda la lógica de combate. *
 */
public class Combate {
	
	private VentanaJuego ventana;					
	private static ArrayList<Pokemon> aliados;		//Equipo del J1
	private static ArrayList<Pokemon> oponentes;	//Equipo del J2
	private Pokemon pActivo;						//Pokémon en primera posición del equipo del J1
	private Pokemon pEnemigo;						//Pokémon en primera posición del equipo del J2

	public Combate(VentanaJuego v) { //Que se cree el combate y de ahí se creen las ventanas y no al revés (que es lo que está pasando ahora)
		this.ventana = v;
		aliados = v.getMiEquipo();
		oponentes = v.getOponente();
		
		pActivo = aliados.get(0);
		pEnemigo = oponentes.get(0);
	}
	
	public void calculaDaño(Pokemon atacante, Pokemon defensor, Movimiento mov) {
		float STAB = 1;
		if (atacante.getTipos().get(0) == mov.getTipo() ) { STAB = (float)1.5; }
		
		float efectividad = 1; //FALTA HACER ALGO PARA LA TABLA DE TIPOS
		float daño = (float)0.01 * STAB * efectividad;
		
		
	}
	
	

}
