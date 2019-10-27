package principal;

public enum AlcanceMovimiento {
	USUARIO, RIVAL, ENEMIGOS, TODOS, EQUIPO, COMBATE;
	
	/**
	 * Usuario: alcanza a quien lo usa
	 * Rival: alcanza a un oponente
	 * Enemigos: alcanza a todos los oponentes
	 * Todos: alcanza a todos salvo a quien lo usa
	 * Equipo: alcanza al equipo de quien lo usa
	 * Combate: movimientos como danza lluvia, pues no afectan a un solo pokémon concreto.
	 */
}
