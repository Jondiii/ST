package principal;

/**
 * Algunos movimientos pueden cambiar el clima del campo. Estos cambios de clima son beneficiosos para algunos Pokemon y movimientos
 * de ciertos tipos concretos y pueden ser perjudiciales para el resto. Duran 5 turnos.
 * 
 * Soleado: Potencia los ataques de tipo fuego y debilita los ataques de tipo agua. Además de ayudar a algunos movimientos.
 * LLuvioso: Potencia los ataques de tipo agua y debilita los ataques de tipo fuego. Además de ayudar a algunos movimientos.
 * Tormenta de arena: Los Pokemon que no sean de tipo Acero, Roca o Tierra recibirán daño porcentual por turno. Además, los Pokemon
 * de tipo Roca verán su defensa especial aumentada.
 * Granizo: Los Pokemon que no sean tipo Hielo recibirán daño porcentual por turno. Además, potencia algunos movimientos.
 * 
 * @author detoroalvaro
 *
 */

public enum Clima {
	SOLEADO, LLUVIOSO, TORMENTA_ARENA, GRANIZO;
	
	}

