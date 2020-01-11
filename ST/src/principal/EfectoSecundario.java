package principal;

import java.util.Random;

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
	RETROCESO, INMUNIDAD, RECOIL, DEBILITACION, CURACION, DESCANSAR, NULL;
	
	public static boolean calcularProbAtacar(Pokemon poke, Movimiento mov) {
		boolean atacar = true;
		Random r = new Random();
			int aleatorio = r.nextInt(101);
			if(aleatorio <= mov.getProb_efecto()) { atacar = false;}
		return atacar;
	}
	
	public double devuelvePS (Pokemon poke, Movimiento mov, double vidaInflinjida) {
		double ps = 0.0;
		switch (mov.getEfecto()) {
		case CURACION:
		if (mov.getNombre() == "Recuperacion" || mov.getNombre() == "Sol Matinal") ps = poke.getPs() * 0.5;
		else {
			ps = vidaInflinjida * 0.5;
			}
		break;
		case RECOIL:
			ps = -(vidaInflinjida * 1/3);
			break;
		case DESCANSAR:
			ps = poke.getPs_max() - poke.getPs();
			break;
		case DEBILITACION:
			ps = -poke.getPs();
			break;
		}
		
		return ps;
	}
}
