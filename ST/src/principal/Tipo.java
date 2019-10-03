package principal;

public enum Tipo {

	BICHO, DRAGON, ELECTRICO, HADA, LUCHA, FUEGO, VOLADOR, FANTASMA,
	PLANTA, TIERRA, HIELO, NORMAL, VENENO, PSIQUICO, ROCA, ACERO, AGUA

	
	private int id;
	
	Tipo(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

}
