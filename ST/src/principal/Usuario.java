package principal;

import java.util.ArrayList;
import java.util.TreeMap;

public class Usuario {
	private String nombre;
	private String contra;
	private String nickname;
	private int puntuacion;
	private int partidasGanadas;
	private int partidasPerdidas;
	private TreeMap<Integer, ArrayList<Pokemon>> pokes_entrenador; // he puesto que los equipos esten
	// ordenados por numero pero se puede cambiar
	
	public Usuario(String nombre, String contra, String nickname, int puntuacion, int partidasGanadas,
			int partidasPerdidas) {
		this.nombre = nombre;
		this.contra = contra;
		this.nickname = nickname;
		this.puntuacion = puntuacion;
		this.partidasGanadas = partidasGanadas;
		this.partidasPerdidas = partidasPerdidas;
		pokes_entrenador = new TreeMap<Integer, ArrayList<Pokemon>>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getPartidasGanadas() {
		return partidasGanadas;
	}
	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}
	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}
	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}
	public void anyadirEquipo(ArrayList<Pokemon> nuevo_equipo) {
		int numero = pokes_entrenador.lastKey();
		pokes_entrenador.put(numero ++, nuevo_equipo);
	}
	
	/* Devuelve el equipo pokemon asociado al int que se pasa como parametro. 
	 * devuelve null si no se puede encontrar 
	 * @param int numero_equipo 
	 */
	public ArrayList<Pokemon> sacarEquipo(int numero_equipo){
		return pokes_entrenador.get(numero_equipo);	
	}
}
