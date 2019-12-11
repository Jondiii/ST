package principal;

import java.awt.Color;

public class Movimiento {
	
	private String nombre;
	private Tipo tipo;		//Cada movimiento pertenece a un único tipo.
	private int potencia;	//Potencia del ataque.
	private int precisión;	//Probabilidad de que el movimiento acierte.
	private int pp;			//Puntos de Poder. Número máximo de veces que se puede usar un movimiento.
	private int ppRestantes;//Número de veces restante que se puede usar el movimiento.
	private CategoriaMov cat;//Categoría a la que pertenece el movimiento.
	private int prioridad; //Varía desde -6 a +6. Los movimientos con prioridad ignoran la velocidad al ejecutarse.
	private AlcanceMovimiento alcance;
	private EstadosAlterados estadoAlt;
	private EfectoSecundario efecto;
	private int prob_efecto;
	
	/**
	 * Las estadísticas de Ataque, Defensa, Ataque especial, Defensa especial y velocidad se pueden cambiar durante el combate.
	 * Algunos movimientos producen estos cambios (positivos o negativos), y hacen que las estadísticas se "multipliquen".
	 * Pueden ir desde -6 (x0,25) hasta +6 (x4)
	 * 0 - Ataque, 1 - Defensa, 2 - Ata. especial, 3 - Def. especial y 4 - Velocidad.
	 */
	// chicos tiene que estar a 5 , sino la velocidad no se almacena
	private Integer[] cambiosEstadisticas = new Integer[5];
	private boolean cambiaStatsAEnemigo = false; //Algunos movimientos cambian los stats del enemigos, otros los del usuario. Solo es true en el primer caso
	
	public Movimiento(String nombre, Tipo tipo, int potencia, int precisión, int pp, CategoriaMov cat,
			int prio, AlcanceMovimiento alcance, EstadosAlterados estadoAlt, int probEstado, EfectoSecundario efecto) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precisión = precisión;
		this.pp = pp;
		this.ppRestantes = pp;
		this.cat = cat;
		this.prioridad = prio;
		this.alcance = alcance;
		this.estadoAlt = estadoAlt;
		this.prob_efecto = probEstado;
		this.efecto = efecto;

		
		//Por defecto los movimientos no producen cambios de estadísticas.
		for (int i = 0; i < cambiosEstadisticas.length; i++) {
			cambiosEstadisticas[i] = 0;
		}
	}
	
	//TODO TODO Hay que añadir las probabilidades de los estados, de bajar stats...
	public Movimiento(String nombre, Tipo tipo, int potencia, int precisión, int pp, CategoriaMov cat,
			int prio, AlcanceMovimiento alcance, EstadosAlterados estadoAlt, int probEstado, EfectoSecundario efecto, int probEfecto) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precisión = precisión;
		this.pp = pp;
		this.ppRestantes = pp;
		this.cat = cat;
		this.prioridad = prio;
		this.alcance = alcance;
		this.estadoAlt = estadoAlt;
		this.prob_efecto = probEstado;
		this.efecto = efecto;

		
		//Por defecto los movimientos no producen cambios de estadísticas.
		for (int i = 0; i < cambiosEstadisticas.length; i++) {
			cambiosEstadisticas[i] = 0;
		}
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	public AlcanceMovimiento getAlcance() {
		return alcance;
	}
	public void setAlcance(AlcanceMovimiento alcance) {
		this.alcance = alcance;
	}
	
	public int getProb_efecto() {
		return prob_efecto;
	}
	public void setProb_efecto(int prob_efecto) {
		this.prob_efecto = prob_efecto;
	}
	public Integer[] getCambiosEstadisticas() {
		return cambiosEstadisticas;
	}

	public EstadosAlterados getEstadoAlt() {
		return estadoAlt;
	}

	public void setEstadoAlt(EstadosAlterados estadoAlt) {
		this.estadoAlt = estadoAlt;
	}



	public void setEfecto(EfectoSecundario efecto) {
		this.efecto = efecto;
	}

	public EfectoSecundario getEfecto() {
		return efecto;
	}



	public void setCambiosEstadisticas(Integer[] cambiosEstadisticas) {
		this.cambiosEstadisticas = cambiosEstadisticas;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isCambiaStatsAEnemigo() {
		return cambiaStatsAEnemigo;
	}



	public void setCambiaStatsAEnemigo(boolean cambiaStatsAEnemigo) {
		this.cambiaStatsAEnemigo = cambiaStatsAEnemigo;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getPrecisión() {
		return precisión;
	}

	public void setPrecisión(int precisión) {
		this.precisión = precisión;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public int getPpRestantes() {
		return ppRestantes;
	}

	public void setPpRestantes(int ppRestantes) {
		this.ppRestantes = ppRestantes;
	}


	public CategoriaMov getCat() {
		return cat;
	}

	public void setCat(CategoriaMov cat) {
		this.cat = cat;
	}


	public int getPrioridad() {
		return prioridad;
	}


	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

}
