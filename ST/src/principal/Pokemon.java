package principal;

import java.util.ArrayList;
import java.util.Collections;

public class Pokemon {
	
	private String nombre;
	private ArrayList<Tipo> tipos; //Crear array
	private int peso;
	private int altura;
	private String habilidad;
	private int ps; 
	private int ps_max;
	private int ataque;
	private int ataque_especial;
	private int defensa;
	private int defensa_especial;
	private int velocidad;
	private int nivel;
	private ArrayList<Movimiento> movimientos_poke;//podemos hacer tambien un array como querais
	
	/**
	 * Las estadísticas de Ataque, Defensa, Ataque especial, Defensa especial y velocidad se pueden cambiar durante el combate.
	 * Algunos movimientos producen estos cambios (positivos o negativos), y hacen que las estadísticas se "multipliquen".
	 * Pueden ir desde -6 (x0,25) hasta +6 (x4)
	 * 0 - Ataque, 1 - Defensa, 2 - Ata. especial, 3 - Def. especial y 4 - Velocidad.
	 */
	private Integer[] cambiosEstadisticas = new Integer[4];
	
	
	public Pokemon(String nombre, int peso, int altura, String habilidad, int ps, int ataque,
			int ataque_epecial, int defensa, int defensa_especial, int velocidad, int nivel,
			ArrayList<Movimiento> movimientos_poke, Tipo ... tipo) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.habilidad = habilidad;
		this.ps = ps;
		setPs_max(ps);
		this.ataque = ataque;
		this.ataque_especial = ataque_epecial;
		this.defensa = defensa;
		this.defensa_especial = defensa_especial;
		this.velocidad = velocidad;
		this.nivel = nivel;
		this.movimientos_poke = movimientos_poke;
		tipos = new ArrayList<>();
		Collections.addAll(tipos, tipo);
		
		for (int i = 0; i < cambiosEstadisticas.length; i++) {
			cambiosEstadisticas[i] = 0;
		}
	}

	/** Calcula el portentaje de vida actual que tiene el pokemon
	 */
	public int calcuPsPorcentaje() {
		return (ps*100/ps_max);
	}
	
	/**
	 * Devuelve el multiplicador de la estadística correspondiente.
	 * @param posicion Posición de al estadística en el array.
	 * 1 - PS, 2 - Ataque, 3 - Defensa, 4 - Ata. especial, 5 - Def. especial y 6 - Velocidad.
	 */
	public double getStats(int posicion) {
		int stat = cambiosEstadisticas[posicion];
		
		if (stat == 0) return 1.0;
		if (stat == 1) return 1.5;
		if (stat == 2) return 2.0;
		if (stat == 3) return 2.5;
		if (stat == 4) return 3.0;
		if (stat == 5) return 3.5;
		if (stat == 6) return 4.0;
		if (stat == -1) return 0.67;
		if (stat == -2) return 0.5;
		if (stat == -3) return 0.4;
		if (stat == -4) return 0.33;
		if (stat == -5) return 0.29;
		if (stat == -6) return 0.25;
		return 1.0;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Tipo> getTipos() {
		return tipos;
	}
	public void setTipos(ArrayList<Tipo> tipos) {
		this.tipos = tipos;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public String getHabilidad() {
		return habilidad;
	}
	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}
	public int getAtaque() {
		return ataque;
	}
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	public int getAtaque_especial() {
		return ataque_especial;
	}
	public void setAtaque_especial(int ataque_especial) {
		this.ataque_especial = ataque_especial;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public int getDefensa_especial() {
		return defensa_especial;
	}
	public void setDefensa_especial(int defensa_especial) {
		this.defensa_especial = defensa_especial;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public ArrayList<Movimiento> getMovimientos_poke() {
		return movimientos_poke;
	}
	public void setMovimientos_poke(ArrayList<Movimiento> movimientos_poke) {
		this.movimientos_poke = movimientos_poke;
	}
	public int getPs_max() {
		return ps_max;
	}
	private void setPs_max(int ps) {
		this.ps_max = ps;
	}

	
}