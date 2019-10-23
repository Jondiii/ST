package principal;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase que gestiona toda la lógica de combate.
 */
public class Combate {
	
	private VentanaJuego ventana;					
	private static ArrayList<Pokemon> aliados;		//Equipo del J1
	private static ArrayList<Pokemon> oponentes;	//Equipo del J2
	private Pokemon pActivo;						//Pokémon en primera posición del equipo del J1
	private Pokemon pEnemigo;						//Pokémon en primera posición del equipo del J2
	private Movimiento movActivo;					//movimiento del activo
	private Movimiento movEnemigo;
	private boolean J1_cambia = false;
	private boolean J2_cambia = false;
	private boolean J1_accion_hecha = false;
	private boolean J2_accion_hecha = false;


	/**
	 * Se crea un combate en función de la ventana.
	 */
	public Combate() {
	
		aliados = VentanaJuego.getMiEquipo();
		oponentes = VentanaJuego.getOponente();
		
		pActivo = aliados.get(0);
		pEnemigo = oponentes.get(0);
	}
	
	/**
	 * Se crea un combate en base a los pokémon seleccionados.
	 * @param miPokemons
	 * @param oponente
	 */
	public Combate(ArrayList<Pokemon> miPokemons, ArrayList<Pokemon> oponente) {
		aliados = miPokemons;
		oponentes = oponente;
		
		pActivo = aliados.get(0);
		pEnemigo = oponentes.get(0);
		
		ventana = new VentanaJuego(this);
	}
	
	public static float calculaDaño(Pokemon atacante, Pokemon defensor, Movimiento mov) {
		
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
	
	/**
	 * Calcula los cambios en los stats en base a los que ya tiene el pokémon.
	 * 
	 * PROBLEMAS: Si un stat tiene un +5, y el movimiento hace le da +2, el cambio no será efectivo.
	 * Es decir, en vez de quedarse con un +6, se quedará con el +5, porque si se le añade 2 se "pasa".
	 * 
	 * MEJORAS: Si añadimos un cuadro de texto que explique lo que ocurre a tiempo real se podrá
	 * hacer que saliese un mensaje del estilo "¡el ataque no puede subir más!" o algo así por cada stat.
	 * 
	 * @param estadoActual  Cambios en los stats actuales del pokémon
	 * @param move			Movimiento efectuado
	 * @return				Array de los cambios en los stats tras aplicar las alteraciones del movimiento.
	 */
	public static Integer[] calculaCambiosStats(Integer[] estadoActual, Movimiento move) {
		for (int i = 0; i < estadoActual.length; i++) {
			int cambio = estadoActual[i] + move.getCambiosEstadisticas()[i];
			
			if (cambio > -6 && cambio < 6) {
				estadoActual[i] = cambio;
			}
		}
		
		return estadoActual;
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

	public VentanaJuego getVentana() {
		return ventana;
	}

	public boolean isJ1_accion_hecha() {
		return J1_accion_hecha;
	}

	public void setJ1_accion_hecha(boolean j1_accion_hecha) {
		J1_accion_hecha = j1_accion_hecha;
	}

	public boolean isJ2_accion_hecha() {
		return J2_accion_hecha;
	}

	public void setJ2_accion_hecha(boolean j2_accion_hecha) {
		J2_accion_hecha = j2_accion_hecha;
	}

	public boolean isJ1_cambia() {
		return J1_cambia;
	}

	public void setJ1_cambia(boolean j1_cambia) {
		J1_cambia = j1_cambia;
	}

	public boolean isJ2_cambia() {
		return J2_cambia;
	}

	public void setJ2_cambia(boolean j2_cambia) {
		J2_cambia = j2_cambia;
	}

	public void setVentana(VentanaJuego ventana) {
		this.ventana = ventana;
	}

	public static ArrayList<Pokemon> getAliados() {
		return aliados;
	}

	public static void setAliados(ArrayList<Pokemon> aliados) {
		Combate.aliados = aliados;
	}

	public static ArrayList<Pokemon> getOponentes() {
		return oponentes;
	}

	public static void setOponentes(ArrayList<Pokemon> oponentes) {
		Combate.oponentes = oponentes;
	}

	public Movimiento getMovActivo() {
		return movActivo;
	}

	public void setMovActivo(Movimiento movActivo) {
		this.movActivo = movActivo;
	}

	public Movimiento getMovEnemigo() {
		return movEnemigo;
	}

	public void setMovEnemigo(Movimiento movEnemigo) {
		this.movEnemigo = movEnemigo;
	}
}
