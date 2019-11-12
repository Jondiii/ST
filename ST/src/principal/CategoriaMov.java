package principal;

public enum CategoriaMov {
	FISICO, ESPECIAL, ESTADO;
	/**
	 * Los movimientos pueden pertenecer a 3 categorías:
	 * 
	 * Físicos: se usan las características de ataque y defensa para calcular el daño.
	 * Especiales: se usan las características de ataque especial y defensa especial para calcular el daño.
	 * Estado: no causan daño, por lo que no tiene en cuenta las características de los pokémon. Tienen efectos muy variados.
	 */
}
