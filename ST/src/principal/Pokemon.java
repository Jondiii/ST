package principal;

import java.util.ArrayList;
import java.util.Collections;

public class Pokemon {
	
	private String nombre;
	private Tipo[] tipos = new Tipo[2]; 
	private int peso;
	private int altura;
	private String habilidad;
	private int[] ps = new int [10]; 
	private int[] ataque = new int[10];
	private int[] ataque_especial = new int [10];
	private int[] defensa = new int [10];
	private int[] defensa_especial = new int [10];
	private int[] velocidad = new int [10];
	private Movimiento [] movimientos;
	
	public Pokemon(String nombre, int peso, int altura, String habilidad, int ps, int ataque,
			int ataque_epecial, int defensa, int defensa_especial, int velocidad,
			Movimiento[] movimientos, Tipo ... tipo) {
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.habilidad = habilidad;
		this.ps[0] = ps;
		this.ataque[0] = ataque;
		this.ataque_especial[0] = ataque_epecial;
		this.defensa[0] = defensa;
		this.defensa_especial[0] = defensa_especial;
		this.velocidad[0] = velocidad;
		this.movimientos = movimientos;
		int i = 0;
		for (Tipo t : tipo) {
			tipos[i] = t;
			i++;
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Tipo[] getTipos() {
		return tipos;
	}
	public void setTipos(Tipo[] tipos) {
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
	public int[] getPs() {
		return ps;
	}
	public void setPs(int[] ps) {
		this.ps = ps;
	}
	public int[] getAtaque() {
		return ataque;
	}
	public void setAtaque(int[] ataque) {
		this.ataque = ataque;
	}
	public int[] getAtaque_especial() {
		return ataque_especial;
	}
	public void setAtaque_especial(int[] ataque_especial) {
		this.ataque_especial = ataque_especial;
	}
	public int[] getDefensa() {
		return defensa;
	}
	public void setDefensa(int[] defensa) {
		this.defensa = defensa;
	}
	public int[] getDefensa_especial() {
		return defensa_especial;
	}
	public void setDefensa_especial(int[] defensa_especial) {
		this.defensa_especial = defensa_especial;
	}
	public int[] getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int[] velocidad) {
		this.velocidad = velocidad;
	}
	public Movimiento[] getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(Movimiento[] movimientos) {
		this.movimientos = movimientos;
	}
	
}