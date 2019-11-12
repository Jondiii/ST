package principal;

/**
 * Algunos movimientos pueden tener un efecto inmediato en el combate. Estos efectos son diferentes a los estados alterados
 * porque no perduran, a diferencia de los estados alterados.
 * 
 * Retroceso: El oponente tiene una pequeña probabilidad de no actuar en ese turno.
 * Inmunidad: El usuario no recibirá daño durante un turno. Tampoco puede sufrir estados alterados (aunque mantendrá el que tuviese de antes).
 * Recoil: El usuairo ataca con tanta fuerza que se daña a sí mismo tras el golpe, en un 25% de la vida que le haya quitado al objetivo.
 * @author Jon
 *
 */
public enum EfectoSecundario {
	RETROCESO, INMUNIDAD, RECOIL, DEBILITACION, CURACION, DESCANSAR;
}
