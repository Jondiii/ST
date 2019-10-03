package principal;

import java.util.ArrayList;
import java.util.Collections;

public class Pokemon {
	
	public String nombre;
	public ArrayList<Tipo> tipos;
	public int peso;
	public int altura;
	public String habilidad;
	public int ataque;
	public int ataque_epecial;
	public int defensa;
	public int defensa_especial;
	public int velocidad;
	public int nivel;
	public ArrayList<Movimiento> movimientos_poke;//podemos hacer tambien un array como querais
	
	
	public Pokemon(String nombre, int peso, int altura, String habilidad, int ataque,
			int ataque_epecial, int defensa, int defensa_especial, int velocidad, int nivel,
			ArrayList<Movimiento> movimientos_poke, Tipo ... tipo) {
		super();
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.habilidad = habilidad;
		this.ataque = ataque;
		this.ataque_epecial = ataque_epecial;
		this.defensa = defensa;
		this.defensa_especial = defensa_especial;
		this.velocidad = velocidad;
		this.nivel = nivel;
		this.movimientos_poke = movimientos_poke;
		tipos = new ArrayList<>();
		Collections.addAll(tipos, tipo);
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
	public int getAtaque_epecial() {
		return ataque_epecial;
	}
	public void setAtaque_epecial(int ataque_epecial) {
		this.ataque_epecial = ataque_epecial;
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
	public ArrayList<Movimiento> getMovimientos_poke() {
		return movimientos_poke;
	}
	public void setMovimientos_poke(ArrayList<Movimiento> movimientos_poke) {
		this.movimientos_poke = movimientos_poke;
	}
	public static void main(String[] args){
		ArrayList<Movimiento> m = new ArrayList<>();
		Pokemon p = new Pokemon("Torterra", 1, 1, "Probando", 1, 1, 1, 1, 1, 1, m, Tipo.ACERO);
		System.out.print(p);
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/Jondiii/ST
