package principal;

public class Movimiento {
	
	private String nombre;
	private int potencia;  //Multiplicador de potencia del ataque
	private int precision; //Probabilidad de que el movimiento alcance el blanco.
	private Tipo tipo;     //Tipo de movimiento.
	private int pp;        //Puntos de Poder. Número de veces máximo que se puede usar el movimiento.
	private int ppRestantes = pp; //Número de veces restante que se puede usar el movimiento.
	private String descrip;//Descripción del movimiento y sus efectos.
	
	
	public Movimiento(String nombre, int potencia, int precision, Tipo tipo, int pp, String descrip) {
		this.nombre = nombre;
		this.potencia = potencia;
		this.precision = precision;
		this.tipo = tipo;
		this.pp = pp;
		this.ppRestantes = pp;
		this.descrip = descrip;
	}
	
	
	
}
