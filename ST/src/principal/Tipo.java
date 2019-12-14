package principal;

import java.awt.Color;

public enum Tipo {
	/**
	 * Ordenados los tipos para que sea más fácil copiar la tabla de tipos que está
	 * en google drive.
	 */
	ACERO(0, Color.GRAY), AGUA(1, Color.BLUE),BICHO(2, new Color(132,214,68)),
	DRAGON(3, new Color (70,33,129)), ELECTRICO(4, Color.YELLOW),FANTASMA(5, new Color(93,38,118)),
	FUEGO(6, Color.RED), HADA(7, Color.PINK), HIELO(8, new Color (23,220,249)), 
	LUCHA(9, new Color (240,131,40)), NORMAL(10, new Color(201,200,200)),
	PLANTA(11, Color.GREEN), PSIQUICO(12, new Color(250,10,156)),ROCA(13, new Color (144,86,29)),
	SINIESTRO(14, new Color(66,64,62)), TIERRA(15, new Color(184,119,65)), 
	VENENO(16, new Color (147,21,180)), VOLADOR(17, new Color (170,220,227)), NULL(18, Color.GRAY);
	
	private int id;
	private Color color;

	Tipo(int id, Color color){
		this.id = id;
		this.color = color;
	}
	
	public int getId() {
		return id;
	}

	public Color getColor() {
		return color;
	}
	
};
