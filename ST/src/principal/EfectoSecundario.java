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
	
	private static int turnos_seguidos_proteccion1 = 0;
	private static int turnos_seguidos_proteccion2 = 0;
	
	
	public static boolean calcularProbAtacar(Pokemon poke, Movimiento mov) {
		boolean atacar = true;
		if(mov.getEfecto() == EfectoSecundario.RETROCESO) {
			Random r = new Random();
			int aleatorio = r.nextInt(101);
			if(aleatorio <= mov.getProb_efecto()) { atacar = false;}
		}
		return atacar;
	}
	
	public static double devuelvePS (Pokemon poke, Movimiento mov, double vidaInflinjida) {
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
			if(poke.getEstado() != EstadosAlterados.DORMIDO) {
				ps = poke.getPs_max() - poke.getPs();
				poke.setEstado(EstadosAlterados.DORMIDO);
			}
			break;
		case DEBILITACION:
			ps = -poke.getPs();
			break;
		default:
			break;
		}
		return ps;
	}
	
	public static boolean seProtege (Movimiento mov, int equipo) {
		boolean protegido = false;
		Random r = new Random();
		if (mov.getEfecto() == EfectoSecundario.INMUNIDAD && equipo == 1) {
			protegido = true;
				if (turnos_seguidos_proteccion1 == 1) {
					int aleatorio = r.nextInt(101);
					if(aleatorio >= 50) { protegido = false;}
				}
				if (turnos_seguidos_proteccion1 == 2) {
					int aleatorio = r.nextInt(101);
					if(aleatorio >= 25) { protegido = false;}
				}
				if (turnos_seguidos_proteccion1 >= 3) {
					int aleatorio = r.nextInt(101);
					if(aleatorio >= 12.5) { protegido = false;}
				}
				if(protegido == true) {turnos_seguidos_proteccion1 += 1;}
				if(protegido == false) {turnos_seguidos_proteccion1 = 0;}
		}
		if (mov.getEfecto() != EfectoSecundario.INMUNIDAD && equipo == 1) {
			turnos_seguidos_proteccion1 = 0;
		}
		if (mov.getEfecto() == EfectoSecundario.INMUNIDAD && equipo == 2) {
			protegido = true;
				if (turnos_seguidos_proteccion2 == 1) {
					int aleatorio = r.nextInt(101);
					if(aleatorio >= 50) { protegido = false;}
				}
				if (turnos_seguidos_proteccion2 == 2) {
					int aleatorio = r.nextInt(101);
					if(aleatorio >= 25) { protegido = false;}
				}
				if (turnos_seguidos_proteccion2 >= 3) {
					int aleatorio = r.nextInt(101);
					if(aleatorio >= 12.5) { protegido = false;}
				}
				if(protegido == true) {turnos_seguidos_proteccion2 += 1;}
				if(protegido == false) {turnos_seguidos_proteccion2 = 0;}
		}
		if (mov.getEfecto() != EfectoSecundario.INMUNIDAD && equipo == 2) {
			turnos_seguidos_proteccion2 = 0;
		}

		return protegido;
	}
	
}
