package principal;

import java.awt.Color;

public class Movimiento {
	
	private String nombre;
	private Tipo tipo;		//Cada movimiento pertenece a un único tipo.
	private int potencia;	//Potencia del ataque.
	private int precision;	//Probabilidad de que el movimiento acierte.
	private int pp;			//Puntos de Poder. Número máximo de veces que se puede usar un movimiento.
	private int ppRestantes;//Número de veces restante que se puede usar el movimiento.
	private CategoriaMov cat;//Categoría a la que pertenece el movimiento.
	private int prioridad; //Varía desde -6 a +6. Los movimientos con prioridad ignoran la velocidad al ejecutarse.
	private AlcanceMovimiento alcance; //Indica si el movimiento afecta a un rival, a ambos, al usuario...
	private EstadosAlterados estadoAlt;	//Indica si el mov congela, quema...
	private EfectoSecundario efecto_secundario; //Algunos movimientos pueden proteger al usuario, hacer retroceder al rival...
	private float prob_efecto_secundario;
	private float prob_cambio_stats;
	private float prob_estado_alt;
	
	/**
	 * Las estadísticas de Ataque, Defensa, Ataque especial, Defensa especial y velocidad se pueden cambiar durante el combate.
	 * Algunos movimientos producen estos cambios (positivos o negativos), y hacen que las estadísticas se "multipliquen".
	 * Pueden ir desde -6 (x0,25) hasta +6 (x4)
	 * 0 - Ataque, 1 - Defensa, 2 - Ata. especial, 3 - Def. especial y 4 - Velocidad.
	 */
	// chicos tiene que estar a 5 , sino la velocidad no se almacena
	private Integer[] cambiosEstadisticas = new Integer[5];
	private boolean cambiaStatsAEnemigo = false; //Algunos movimientos cambian los stats del enemigos, otros los del usuario. Solo es true en el primer caso
												 //Será true también en el caso de que el estado alterado se le cause al enemigo.
	
	public Movimiento(String nombre, Tipo tipo, int potencia, int precision, int pp, CategoriaMov cat,
			int prio, AlcanceMovimiento alcance, EstadosAlterados estadoAlt, int probEstado, EfectoSecundario efecto) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precision = precision;
		this.pp = pp;
		this.ppRestantes = pp;
		this.cat = cat;
		this.prioridad = prio;
		this.alcance = alcance;
		this.estadoAlt = estadoAlt;
		this.prob_efecto_secundario = probEstado;
		this.efecto_secundario = efecto;

		
		//Por defecto los movimientos no producen cambios de estadísticas.
		for (int i = 0; i < cambiosEstadisticas.length; i++) {
			cambiosEstadisticas[i] = 0;
		}
	}
	
	//TODO TODO Hay que añadir las probabilidades de los estados, de bajar stats...
	public Movimiento(String nombre, Tipo tipo, int potencia, int precision, int pp, CategoriaMov cat,
			int prio, AlcanceMovimiento alcance, EstadosAlterados estadoAlt, int probEstado, EfectoSecundario efecto, int probEfecto) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precision = precision;
		this.pp = pp;
		this.ppRestantes = pp;
		this.cat = cat;
		this.prioridad = prio;
		this.alcance = alcance;
		this.estadoAlt = estadoAlt;
		this.prob_efecto_secundario = probEstado;
		this.efecto_secundario = efecto;

		
		//Por defecto los movimientos no producen cambios de estadísticas.
		for (int i = 0; i < cambiosEstadisticas.length; i++) {
			cambiosEstadisticas[i] = 0;
		}
	}
	
	
	//Constructor que se usará al leer desde la bd
	public Movimiento(String nombre, Tipo tipo, int potencia, int precisión, int pp, CategoriaMov cat,
			int prioridad, AlcanceMovimiento alcance, EstadosAlterados estadoAlt, EfectoSecundario efecto_secundario,
			float prob_efecto_secundario, float prob_cambio_stats, float prob_estado_alt, boolean cambiaStatsAEnemigo, int ata,
			int def, int ata_esp, int def_esp, int vel) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precision = precisión;
		this.pp = pp;
		this.cat = cat;
		this.prioridad = prioridad;
		this.alcance = alcance;
		this.estadoAlt = estadoAlt;
		this.efecto_secundario = efecto_secundario;
		this.prob_efecto_secundario = prob_efecto_secundario;
		this.prob_cambio_stats = prob_cambio_stats;
		this.prob_estado_alt = prob_estado_alt;
		this.cambiaStatsAEnemigo = cambiaStatsAEnemigo;
		
		cambiosEstadisticas[0] = ata;
		cambiosEstadisticas[1] = def;
		cambiosEstadisticas[2] = ata_esp;
		cambiosEstadisticas[3] = def_esp;
		cambiosEstadisticas[4] = vel;

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
	
	public float getProb_efecto() {
		return prob_efecto_secundario;
	}
	public void setProb_efecto(int prob_efecto) {
		this.prob_efecto_secundario = prob_efecto;
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
		this.efecto_secundario = efecto;
	}

	public EfectoSecundario getEfecto() {
		return efecto_secundario;
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

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precisión) {
		this.precision = precisión;
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
