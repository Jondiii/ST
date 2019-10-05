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
	
	
	public Pokemon(String nombre, int peso, int altura, String habilidad, int ps, int ataque,
			int ataque_epecial, int defensa, int defensa_especial, int velocidad, int nivel,
			ArrayList<Movimiento> movimientos_poke, Tipo ... tipo) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.habilidad = habilidad;
		this.ps = ps;
		this.ps_max = ps;
		this.ataque = ataque;
		this.ataque_especial = ataque_epecial;
		this.defensa = defensa;
		this.defensa_especial = defensa_especial;
		this.velocidad = velocidad;
		this.nivel = nivel;
		this.movimientos_poke = movimientos_poke;
		tipos = new ArrayList<>();
		Collections.addAll(tipos, tipo);
	}
	
	/* Calcula el portentaje de vida actual que tiene el pokemon
	 */
	public int calcuPsPorcentaje() {
		return (ps/ps_max)*100;
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
	
	
}