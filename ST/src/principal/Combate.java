package principal;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase que gestiona toda la lógica de combate. *
 */
public class Combate {
	
	private VentanaJuego ventana;					
	private static ArrayList<Pokemon> aliados;		//Equipo del J1
	private static ArrayList<Pokemon> oponentes;	//Equipo del J2
	private Pokemon pActivo;						//Pokémon en primera posición del equipo del J1
	private Pokemon pEnemigo;						//Pokémon en primera posición del equipo del J2

	public Combate() { //Que se cree el combate y de ahí se creen las ventanas y no al revés (que es lo que está pasando ahora)
	
		aliados = VentanaJuego.getMiEquipo();
		oponentes = VentanaJuego.getOponente();
		
		pActivo = aliados.get(0);
		pEnemigo = oponentes.get(0);
	}
	
	public float calculaDaño(Pokemon atacante, Pokemon defensor, Movimiento mov) {
		
		if (mov.getCat() == CategoriaMov.ESTADO) {return 0;};
		
		float STAB = 1; //STAB = Same Type Attack Bonus. Si el tipo del movimiento y el del pokémon que lo realiza coincide, el daño se incrementa en un 50%
		if (atacante.getTipos().contains(mov.getTipo())) { STAB = (float)1.5; }
		
		float efectividad = TablaTipos.calculaEficacia(defensor, mov);
		
		if (mov.getCat() == CategoriaMov.FISICA) {

			float daño = (float)0.01 * STAB * efectividad * ThreadLocalRandom.current().nextInt(85, 100 + 1)
					* ( ( ((float)(0.2) * 100 + 1) * atacante.getAtaque() * mov.getPotencia()) / (25 * defensor.getDefensa()) + 2);
			
			return daño;
			
		} else {

			float daño = (float)0.01 * STAB * efectividad * ThreadLocalRandom.current().nextInt(85, 100 + 1)
					* ( ( ((float)(0.2) * 100 + 1) * atacante.getAtaque_especial() * mov.getPotencia()) / (25 * defensor.getDefensa_especial()) + 2);
			
			return daño;
		}
		
		
		
	}

	public Pokemon getpActivo() {
		return pActivo;
	}

	public void setpActivo(Pokemon pActivo) {
		this.pActivo = pActivo;
	}

	public Pokemon getpEnemigo() {
		return pEnemigo;
	}

	public void setpEnemigo(Pokemon pEnemigo) {
		this.pEnemigo = pEnemigo;
	}
}
