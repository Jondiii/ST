package principal;

import java.awt.Color;

public class Movimiento {
	
	private String nombre;
	private Tipo tipo;		//Cada movimiento pertenece a un único tipo.
	private int potencia;	//Potencia del ataque.
	private int precisión;	//Probabilidad de que el movimiento acierte.
	private int pp;			//Puntos de Poder. Número máximo de veces que se puede usar un movimiento.
	private int ppRestantes;//Número de veces restante que se puede usar el movimiento.
	private Color color; //Color del movimiento en la ventana de ataque
	
	public Movimiento(String nombre, Tipo tipo, int potencia, int precisión, int pp) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.precisión = precisión;
		this.pp = pp;
		this.ppRestantes = pp;
	}
	
//	COLORES
//		if (tipo.ACERO) {
//			setColor(Color.GRAY);
//		}
//		if (tipo.AGUA) {
//			setColor(Color.BLUE);
//		}
//		if (tipo.BICHO) {
//			setColor(new Color (132,214,68));
//		}
//		if (tipo.DRAGON) {
//			setColor(new Color (70,33,129));
//		}
//		if (tipo.ELECTRICO) {
//			setColor(Color.YELLOW);
//		}
//		if (tipo.FANTASMA) {
//			setColor( new Color (93,38,118));
//		}
//		if (tipo.FUEGO) {
//			setColor( Color.RED);
//		}
//		if (tipo.HADA) {
//			setColor (Color.PINK);
//		}
//		if (tipo.HIELO) {
//			setColor (new Color (23,220,249));
//		}
//		if (tipo.LUCHA) {
//			setColor (new Color (240,131,40));
//		}
//		if (tipo.NORMAL) {
//			setColor (new Color (201,200,200));
//		}
//		if (tipo.PLANTA) {
//			setColor(Color.GREEN);
//		}
//		if (tipo.PSIQUICO) {
//			setColor (new Color (204,10,156));
//		}
//		if (tipo.ROCA) {
//			setColor (new Color(144,86,29));
//		}
//		if (tipo.SINIESTRO) {
//			setColor (new Color(66,64,62));
//		}
//		if (tipo.TIERRA) {
//			setColor (new Color (184,119,65));
//		}
//		if (tipo.VENENO) {
//			setColor (new Color (147,21,180));
//		}
//		if (tipo.VOLADOR) {
//			setColor (new Color (170,220,227));
//		}
//	}

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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	

}
