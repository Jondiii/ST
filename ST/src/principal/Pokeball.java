package principal;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pokeball extends JLabel {
	
	public Pokemon poke = null;
	public boolean pokemonOculto = false;
	public Movimiento[] movsPoke;
	public String nombre;
	public boolean vacia = true;
	
	public Pokeball(Icon img, Pokemon p){
		super(img);
		this.poke = p;
		this.nombre = p.getNombre();
	}
	
	public Pokeball(Icon img) {
		super(img);
	}
	
	public Pokeball(Icon img, Movimiento[] datos) {
		super(img);
		movsPoke = datos;
	}
	
	public void mostrarPoke() {
		if (pokemonOculto) {return;}
		ImageIcon icono_p_1;
		
		try {
			ImageIcon icono_p = new ImageIcon(getClass().getResource("/img/" + poke.getNombre() + "_sprite.png" ));
			icono_p_1 = new ImageIcon(icono_p.getImage().getScaledInstance(40, 30, java.awt.Image.SCALE_DEFAULT));
		} catch (Exception e) {
			ImageIcon icono_p = new ImageIcon(getClass().getResource("/img/" + movsPoke[0] + "_sprite.png" ));
			icono_p_1 = new ImageIcon(icono_p.getImage().getScaledInstance(40, 30, java.awt.Image.SCALE_DEFAULT));
			}

		this.setIcon(icono_p_1);
		pokemonOculto = true;
	}
	
	public Pokemon getPoke() {
		return poke;
	}
	public void setPoke(Pokemon poke) {
		this.poke = poke;
		this.vacia = false;
		this.nombre = poke.getNombre();
	}
	public boolean isPokemonOculto() {
		return pokemonOculto;
	}
	public void setPokemonOculto(boolean pokemonOculto) {
		this.pokemonOculto = pokemonOculto;
	}

	public Movimiento[] getMovsPoke() {
		return movsPoke;
	}

	public void setMovsPoke(Movimiento[] movsPoke) {
		this.movsPoke = movsPoke;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isVacia() {
		return vacia;
	}

	public void setVacia(boolean vacia) {
		this.vacia = vacia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
