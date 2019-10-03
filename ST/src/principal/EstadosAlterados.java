package principal;

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
	ENVENENADO(1/16), ENV_GRAVE(1/16), QUEMADO(1/16), PARALIZADO(1/4), DORMIDO(1/3), CONGELADO(1/4), CONFUSO(1/3);
	
	private float valor;
	
	EstadosAlterados(float valor){
		this.valor = valor;
	}
	
	public float getValor() {
		return valor;
	}
}
