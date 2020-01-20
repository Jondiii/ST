package principal;

public class EquiposPorDefecto {

	
	public EquiposPorDefecto() {

		Movimiento brilloMagico = new Movimiento("Brillo mágico", Tipo.HADA, 80, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0,	0, 0);

		Movimiento llamarada = new Movimiento("Llamarada", Tipo.FUEGO, 100, 85, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.QUEMADO,
		EfectoSecundario.NULL, 30, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento rocaAfilada = new Movimiento("Roca Afilada", Tipo.ROCA, 130, 70, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0,	0, 0);
		
		Movimiento terremoto = new Movimiento("Terremoto", Tipo.TIERRA, 100, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ENEMIGOS, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0,	0, 0);
		
		Movimiento bolaSombra = new Movimiento("Bola Sombra", Tipo.FANTASMA, 90, 100, 15, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 10, 0, true, 0, 0, 0,	-1, 0);
		
		Movimiento mazazo = new Movimiento("Mazazo", Tipo.PLANTA, 120, 100, 5, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.RECOIL, 100, 0, 0, false, 0, 0, 0,	0, 0);
		
		Movimiento ondaTrueno = new Movimiento("Onda Trueno", Tipo.ELECTRICO, 0, 100, 10, CategoriaMov.ESTADO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.PARALIZADO,
		EfectoSecundario.NULL, 0, 0, 100, false, 0, 0, 0, 0, 0);
		
		Movimiento psiquico = new Movimiento("Psíquico", Tipo.PSIQUICO, 90, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 10, 0, true, 0, 0, 0,	-1, 0);
		
		Movimiento sombraVil = new Movimiento("Sombra Vil", Tipo.FANTASMA, 50, 100, 20, CategoriaMov.FISICO, 1,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0,	0, 0);
		
		Movimiento puñoFuego = new Movimiento("Puño Fuego", Tipo.FUEGO, 80, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.QUEMADO,
		EfectoSecundario.NULL, 0, 0, 30, false, 0, 0, 0, 0, 0);
		
		Movimiento cometaDraco = new Movimiento("Cometa Draco", Tipo.DRAGON, 150, 100, 5, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 100, 0, false, 0, 0, -2, 0, 0);
		
		Movimiento abocajarro = new Movimiento("A bocajarro", Tipo.LUCHA, 120, 100, 5, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 100, 0, false, 0, -1, 0, -1, 0);
		
		Movimiento surf = new Movimiento("Surf", Tipo.AGUA, 80, 100, 15, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ENEMIGOS, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0,	0, 0);
		
		Movimiento rayoHielo = new Movimiento("Rayo Hielo", Tipo.HIELO, 90, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.CONGELADO,
		EfectoSecundario.NULL, 0, 0, 10, false, 0, 0, 0, 0, 0);
		
		Movimiento trueno = new Movimiento("Trueno", Tipo.ELECTRICO, 120, 80, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.PARALIZADO,
		EfectoSecundario.NULL, 0, 0, 30, false, 0, 0, 0,	0, 0);
		
		Movimiento maquinacion = new Movimiento("Maquinación", Tipo.SINIESTRO, 0, 100, 10, CategoriaMov.ESTADO, 0,
		AlcanceMovimiento.USUARIO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 100, 0, false, 0, 0, 0, +2, 0);
		
		Movimiento puyaN = new Movimiento("Puya Nociva", Tipo.VENENO, 80, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.ENVENENADO,
		EfectoSecundario.NULL, 0, 0, 30, false, 0, 0, 0, 0, 0);
		
		Movimiento hipnosis = new Movimiento("Hipnosis", Tipo.PSIQUICO, 0, 70, 10, CategoriaMov.ESTADO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.DORMIDO,
		EfectoSecundario.NULL, 0, 0, 100, false, 0, 0, 0, 0, 0);
		
		Movimiento garraDragon = new Movimiento("Garra Dragón", Tipo.DRAGON, 85, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento ventisca = new Movimiento("Ventisca", Tipo.VENENO, 120, 80, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ENEMIGOS, EstadosAlterados.CONGELADO,
		EfectoSecundario.NULL, 0, 0, 30, false, 0, 0, 0, 0, 0);
		
		Movimiento gigadrenado = new Movimiento("Gigadrenado", Tipo.PLANTA, 80, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.CURACION, 100, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento energibola = new Movimiento("Energibola", Tipo.PLANTA, 90, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 10, 0, true, 0, 0, 0, -1, 0);
		
		Movimiento pulsoUmbrio = new Movimiento("Pulso Umbrío", Tipo.SINIESTRO, 80, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 10, 0, true, 0, 0, -1, 0, 0);
		
		Movimiento tijeraX = new Movimiento("Tijera X", Tipo.BICHO, 85, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento cabezaHierro = new Movimiento("Cabeza Hierro", Tipo.ACERO, 80, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento danzaEspada = new Movimiento("Danza Espada", Tipo.NORMAL, 0, 100, 30, CategoriaMov.ESTADO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 100, 0, false, +2, 0, 0, 0, 0);
		
		Movimiento focoResplandor = new Movimiento("Foco Resplandor", Tipo.ACERO, 80, 100, 15, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 10, 0, false, 0, 0, 0, -1, 0);
		
		Movimiento defensaFerrea = new Movimiento("Defensa Férrea", Tipo.ACERO, 0, 100, 10, CategoriaMov.ESTADO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 100, 0, false, 0, +2, 0, 0, 0);
		
		Movimiento gigaimpacto = new Movimiento("Gigaimpacto", Tipo.NORMAL, 100, 90, 5, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento dobleFilo = new Movimiento("Doble Filo", Tipo.DRAGON, 100, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.RECOIL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento golpeAereo = new Movimiento("Golpe Aéreo", Tipo.VOLADOR, 80, 100, 20, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento ataqueAla = new Movimiento("Ataque Ala", Tipo.VOLADOR, 60, 100, 30, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento triturar = new Movimiento("Triturar", Tipo.SINIESTRO, 80, 100, 15, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.RETROCESO, 30, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento rayoConfuso = new Movimiento("Rayo Confuso", Tipo.FANTASMA, 0, 90, 10, CategoriaMov.ESTADO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.CONFUSO,
		EfectoSecundario.NULL, 0, 0, 100, false, 0, 0, 0, 0, 0);
		
		Movimiento explosion = new Movimiento("Explosión", Tipo.NORMAL, 170, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.DEBILITACION, 100, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento psicocarga = new Movimiento("Psicocarga", Tipo.PSIQUICO, 85, 100, 15, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento bomba_lodo = new Movimiento("Bomba Lodo", Tipo.VENENO, 90, 100, 10,CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.ENVENENADO, 
		null, 0, 0, 30, false, 0, 0, 0, 0, 0);
		
		Movimiento megacuerno = new Movimiento("Megacuerno", Tipo.BICHO, 120, 85, 10,CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, null, null, 0, 0, 0, false, 0, 0, 0, 0, 0);  
		
		
	}

}
