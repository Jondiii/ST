package principal;

public enum Tipo {
	
	BICHO(1), DRAGON(2), ELECTRICO(3), HADA(4), LUCHA(5), FUEGO(6), VOLADOR(7), FANTASMA(8),
	PLANTA(9), TIERRA(10), HIELO(11), NORMAL(12), VENENO(13), PSIQUICO(14), ROCA(15), ACERO(16), 
	AGUA(17), SINIESTRO(18)

	;
	private int id;
	
	Tipo(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

};
