package principal;

public enum Tipo {
	NORMAL(1), FUEGO(2), AGUA(3), PLANTA(4), TIERRA(5), ROCA(6), HIELO(7), LUCHA(8), VENENO(9), PSIQUICO(10), SINIESTRO(11), HADA(12), BICHO(13), 
	ACERO(14), VOLADOR(15), DRAGON(16), ELECTRICO(17), FANTASMA(18);
	
	private int id;
	
	Tipo(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
