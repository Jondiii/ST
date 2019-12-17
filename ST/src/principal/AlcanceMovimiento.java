package principal;

public enum AlcanceMovimiento {
	USUARIO, ELEGIDO, ENEMIGOS, TODOS, EQUIPO, CAMPO, OPONENTE_AL_AZAR;
	/**
	 * Usuario: alcanza a quien lo usa
	 * Elegido: alcanza a un oponente
	 * Oponentes: alcanza a todos los oponentes
	 * Todos: alcanza a todos salvo a quien lo usa
	 * Equipo: alcanza al equipo de quien lo usa
	 * Campo: movimientos como danza lluvia, pues no afectan a un solo pokémon concreto.
	 * Oponente_al_azar: movimiesntos como enfado y danza petalo no se puede elegir a que oponente van a golpear
	 */
}
