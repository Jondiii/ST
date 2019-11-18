package principal;

import java.util.Random;

/**
 * Un pokémon podrá sufrir uno de los siguientes estados alterados. Si un pokemon ya sufre un cambio de estado, 
 * este no podrá ser cambiado por otro, salvo en circunstancias especiales.
 *
 * - Envenenado y envenenamiento grave: el valor indica la cantidad de PS que el pokémon pierde al final del turno.
 *   En el caso del envenenamiento grave, el valor incrementa en cada turno (se añade 1/16 en cada turno).
 *   El envenenamiento no se puede curar automáticamente.
 *   Los pokémon de tipo veneno y/o tipo acero no pueden resultar envenenados de ninguna forma.
 *   
 * - Quemado: el valor indica la cantidad de PS que el pokemon pierde al final del turno.
 * 	 La quemadura reduce la estadística de ataque del pokémon en un 50%.
 *   Las quemaduras no se pueden curar automáticamente.
 *   Los pokémon de tipo fuego no pueden ser quemados.
 *   
 * - Paralizado: el valor indica la probabilidad que tiene el pokémon de no atacar en ese turno, devido a la parálisis.
 *   La parálisis reduce la estadística de velocidad del pokémon en un 50%.
 *   La parálisis no se puede curar automáticamente.
 *   Los pokémon de tipo eléctrico y/o tipo tierra no pueden ser paralizados.
 *   
 * - Dormido: el valor indica la probabilidad del pokémon de despertarse en un turno.
 *   Mientras dure, el pokémon no podrá actuar, salvo algunas excepciones.
 *   Tiene una duración de entre 1 y 4 turnos.
 *   
 * - Congelado: el valor indica la probabilidad del pokémon de descongelarse en ese turno.
 *   Mientras dure, el pokémon no podrá actuar.
 *   Tiene una duración máxima indeterminada.
 *   Algunos movimientos de tipo fuego pueden descongelar.
 *   Los pokémon de tipo hielo no se pueden congelar.
 *   
 * - Confuso: el valor indica la probabilidad de que el pokémon se golpee a sí mismo en lugar de efectuar el movimiento seleccionado.
 *   Dura entre 1 y 4 turnos.
 */
public enum EstadosAlterados {
	ENVENENADO(1/16, 1), ENV_GRAVE(1/16, 1), QUEMADO(1/16, 1), PARALIZADO(1/4, 1), DORMIDO(1/3, 1), CONGELADO(1/4, 1), CONFUSO(1/3, 1), DEBILITADO(0, 0);
	
	private int turno;
	private float valor;
	
	EstadosAlterados(float valor, int turno){
		this.valor = valor;
		this.turno = turno;
	}
	
	public float getValor() {
		return valor;
	}
	public void setTurno(int turno) {
		 this.turno = turno;
	}
	public int getTurno() {
		 return turno;
	}
	public static int calcularEstadoFinTurno(Pokemon poke){
		int dañoPoke = 0;
		switch (poke.getEstado()) {
			case ENVENENADO:
				
				break;
			case QUEMADO:
				dañoPoke = poke.getPs_max()/16;
				if (poke.getEstado().getTurno() == 1) {
					int ataque_disminuido = poke.getCambiosEstadisticas()[0] - 2;
					Integer[] estadisticas = {ataque_disminuido, poke.getCambiosEstadisticas()[1], 
					poke.getCambiosEstadisticas()[2],  poke.getCambiosEstadisticas()[3],  poke.getCambiosEstadisticas()[4], 
					poke.getCambiosEstadisticas()[5]};
					poke.setCambiosEstadisticas(estadisticas);
				}
				break;
			case ENV_GRAVE:
				break;
			default:
				break;
		}
		return dañoPoke;	
	}
	public static boolean calcularProbAtacar(Pokemon poke) {
		boolean atacar = true;
		Random r = new Random();
		switch (poke.getEstado()) {
			case PARALIZADO:
				int intAleatorio = r.nextInt(101);
				if (intAleatorio<=25) { atacar = false;}
				if (poke.getEstado().getTurno() == 1) {
					int vel_disminuida = poke.getCambiosEstadisticas()[5] - 2;
					Integer[] estadisticas = {poke.getCambiosEstadisticas()[0], poke.getCambiosEstadisticas()[1], 
					poke.getCambiosEstadisticas()[2],  poke.getCambiosEstadisticas()[3],  poke.getCambiosEstadisticas()[4], 
					vel_disminuida};
					poke.setCambiosEstadisticas(estadisticas);
				}
				break;
			case CONGELADO:
				int intAleatorio_1 = r.nextInt(101);
				if (intAleatorio_1<=75) { atacar = false;}//prob de que siga congelado
			case DORMIDO:
				int intAleatorio_2 = r.nextInt(101);
				if (intAleatorio_2<=75) { atacar = false;}// prob del que el pokemon siga dormido
			case CONFUSO:
				int intAleatorio_3 = r.nextInt(101);
				if (intAleatorio_3<=33) { atacar = false;}// habria que calcular el daño lo unico que eso 
				//igual en el hilo de juego
			default:
				break;
		}
		return atacar;
	}
}
