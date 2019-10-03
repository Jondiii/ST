package principal;

public class Movimiento {
	
	private String nombre;
	private Tipo tipo;		//Cada movimiento pertenece a un único tipo.
	private int potencia;	//Potencia del ataque.
	private int precisión;	//Probabilidad de que el movimiento acierte.
	private int pp;			//Puntos de Poder. Número máximo de veces que se puede usar un movimiento.
	private int ppRestantes;//Número de veces restante que se puede usar el movimiento.
	
	public Movimiento(String nombre, Tipo tipo, int potencia, int precisión, int pp) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precisión = precisión;
		this.pp = pp;
		this.ppRestantes = pp;
	}

	public String getNombre() {
		return nombre;
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
	
	

}
