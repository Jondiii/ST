package principal;

import java.awt.Color;

public enum Tipo {
	
	BICHO(1, new Color(132,214,68)), DRAGON(2, new Color (70,33,129)), ELECTRICO(3, Color.YELLOW), HADA(4, Color.PINK), LUCHA(5, new Color (240,131,40)), FUEGO(6, Color.RED), VOLADOR(7, new Color (170,220,227)), FANTASMA(8, new Color(93,38,118)),
	PLANTA(9, Color.GREEN), TIERRA(10, new Color(184,119,65)), HIELO(11, new Color (23,220,249)), NORMAL(12, new Color(201,200,200)), VENENO(13, new Color (147,21,180)), PSIQUICO(14, new Color(204,10,156)), ROCA(15, new Color (144,86,29)), ACERO(16, Color.GRAY), 
	AGUA(17, Color.BLUE), SINIESTRO(18, new Color(66,64,62));
	
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
