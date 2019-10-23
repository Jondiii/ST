package principal;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pokeball extends JLabel {
	
	public Pokemon poke;
	public boolean pokemonOculto = false;

	public Pokeball(Icon img, Pokemon p){
		super(img);
		this.poke = p;
	}
	
	public void mostrarPoke() {
		if (pokemonOculto) {return;}
		ImageIcon icono_p = new ImageIcon(getClass().getResource("/img/" + poke.getNombre() + "_sprite.png" ));
		ImageIcon icono_p_1 = new ImageIcon(icono_p.getImage().getScaledInstance(40, 30, java.awt.Image.SCALE_DEFAULT));
		this.setIcon(icono_p_1);
		pokemonOculto = true;
	}

	public Pokemon getPoke() {
		return poke;
	}
	public void setPoke(Pokemon poke) {
		this.poke = poke;
	}
	public boolean isPokemonOculto() {
		return pokemonOculto;
	}
	public void setPokemonOculto(boolean pokemonOculto) {
		this.pokemonOculto = pokemonOculto;
	}
	
}
