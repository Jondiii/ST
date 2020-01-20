package principal;

import java.util.ArrayList;
import java.util.Random;

public class EquiposPorDefecto {

	static ArrayList<Pokemon> e1 = new ArrayList<Pokemon>();
	static ArrayList<Pokemon> e2 = new ArrayList<Pokemon>();
	static ArrayList<Pokemon> e3 = new ArrayList<Pokemon>();
	static ArrayList<Pokemon> e4 = new ArrayList<Pokemon>();

	static ArrayList<ArrayList<Pokemon>> listaEquipos = new ArrayList<ArrayList<Pokemon>>();
	
	public EquiposPorDefecto() {
		listaEquipos.add(e1);
		listaEquipos.add(e2);
		listaEquipos.add(e3);
		listaEquipos.add(e4);
		
		Movimiento brilloMagico = new Movimiento("Brillo magico", Tipo.HADA, 80, 100, 10, CategoriaMov.ESPECIAL, 0,
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
		
		Movimiento psiquico = new Movimiento("Psiquico", Tipo.PSIQUICO, 90, 100, 10, CategoriaMov.ESPECIAL, 0,
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
		
		Movimiento maquinacion = new Movimiento("Maquinacion", Tipo.SINIESTRO, 0, 100, 10, CategoriaMov.ESTADO, 0,
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
		
		Movimiento ventisca = new Movimiento("Ventisca", Tipo.HIELO, 120, 80, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ENEMIGOS, EstadosAlterados.CONGELADO,
		EfectoSecundario.NULL, 0, 0, 30, false, 0, 0, 0, 0, 0);
		
		Movimiento gigadrenado = new Movimiento("Gigadrenado", Tipo.PLANTA, 80, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.CURACION, 100, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento energibola = new Movimiento("Energibola", Tipo.PLANTA, 90, 100, 10, CategoriaMov.ESPECIAL, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 10, 0, true, 0, 0, 0, -1, 0);
		
		Movimiento pulsoUmbrio = new Movimiento("Pulso Umbrio", Tipo.SINIESTRO, 80, 100, 10, CategoriaMov.ESPECIAL, 0,
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
		
		Movimiento defensaFerrea = new Movimiento("Defensa Ferrea", Tipo.ACERO, 0, 100, 10, CategoriaMov.ESTADO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 100, 0, false, 0, +2, 0, 0, 0);
		
		Movimiento gigaimpacto = new Movimiento("Gigaimpacto", Tipo.NORMAL, 100, 90, 5, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.NULL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento dobleFilo = new Movimiento("Doble Filo", Tipo.DRAGON, 100, 100, 10, CategoriaMov.FISICO, 0,
		AlcanceMovimiento.ELEGIDO, EstadosAlterados.NULL,
		EfectoSecundario.RECOIL, 0, 0, 0, false, 0, 0, 0, 0, 0);
		
		Movimiento golpeAereo = new Movimiento("Golpe Aereo", Tipo.VOLADOR, 80, 100, 20, CategoriaMov.FISICO, 0,
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
		
		Movimiento explosion = new Movimiento("Explosion", Tipo.NORMAL, 170, 100, 10, CategoriaMov.ESPECIAL, 0,
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

		Movimiento protec = new Movimiento("Proteccion", Tipo.NORMAL, 0, 100, 10, CategoriaMov.ESTADO, 3, AlcanceMovimiento.ELEGIDO, null, 0, EfectoSecundario.INMUNIDAD);
		Movimiento besoDrenaje = new Movimiento("Beso drenaje", Tipo.HADA, 65, 100, 20,CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.ELEGIDO, null, 0, null);
		Movimiento escaldar = new Movimiento("Escaldar", Tipo.AGUA, 80, 100, 15,CategoriaMov.ESPECIAL, 0, AlcanceMovimiento.ELEGIDO,  EstadosAlterados.QUEMADO, 30, null);
		Movimiento toxico = new Movimiento("Tóxico", Tipo.VENENO, 0, 90, 10,CategoriaMov.ESTADO, 0, AlcanceMovimiento.ELEGIDO, EstadosAlterados.ENVENENADO, 50, null);


		ArrayList<Movimiento> m = new ArrayList<>();
		ArrayList<Movimiento> m_ab = new ArrayList<>();
		ArrayList<Movimiento> m_se = new ArrayList<>();
		ArrayList<Movimiento> m_sp = new ArrayList<>();
		ArrayList<Movimiento> m_dr = new ArrayList<>();
		ArrayList<Movimiento> m_ae = new ArrayList<>();
		ArrayList<Movimiento> m_ch = new ArrayList<>();
		ArrayList<Movimiento> m_va = new ArrayList<>();
		ArrayList<Movimiento> m_ty = new ArrayList<>();
		ArrayList<Movimiento> m_to = new ArrayList<>();
		ArrayList<Movimiento> m_mil = new ArrayList<>();
		ArrayList<Movimiento> m_ma = new ArrayList<>();
		ArrayList<Movimiento> m_ge = new ArrayList<>();
		ArrayList<Movimiento> m_ga = new ArrayList<>();
		ArrayList<Movimiento> m_gl = new ArrayList<>();
		ArrayList<Movimiento> m_ag = new ArrayList<>();
		ArrayList<Movimiento> m_be = new ArrayList<>();
		ArrayList<Movimiento> m_mim = new ArrayList<>();
		ArrayList<Movimiento> m_sa = new ArrayList<>();
		ArrayList<Movimiento> m_re = new ArrayList<>();
		ArrayList<Movimiento> m_sce = new ArrayList<>();
		ArrayList<Movimiento> m_sco = new ArrayList<>();
		ArrayList<Movimiento> m_um = new ArrayList<>();
		ArrayList<Movimiento> m_ly = new ArrayList<>();
		ArrayList<Movimiento> m_lu = new ArrayList<>();

		m.add(protec); m.add(besoDrenaje); m.add(escaldar); m.add(toxico);
		m_ab.add(ventisca); m_ab.add(mazazo); m_ab.add(gigadrenado); m_ab.add(protec);
		m_se.add(energibola); m_se.add(toxico); m_se.add(protec); m_se.add(gigadrenado);
		m_sp.add(pulsoUmbrio); m_sp.add(bolaSombra); m_sp.add(maquinacion); m_sp.add(rayoConfuso);
		m_dr.add(cometaDraco); m_dr.add(garraDragon); m_dr.add(golpeAereo); m_dr.add(danzaEspada);
		m_ae.add(cabezaHierro); m_ae.add(sombraVil); m_ae.add(protec); m_ae.add(danzaEspada);
		m_ch.add(llamarada); m_ch.add(golpeAereo); m_ch.add(puñoFuego); m_ch.add(garraDragon);
		m_va.add(escaldar); m_va.add(toxico); m_va.add(protec); m_va.add(ventisca);
		m_ty.add(rocaAfilada); m_ty.add(triturar); m_ty.add(terremoto); m_ty.add(gigaimpacto);
		m_to.add(brilloMagico); m_to.add(golpeAereo); m_to.add(ondaTrueno); m_to.add(psicocarga);
		m_mil.add(surf); m_mil.add(rayoHielo); m_mil.add(protec); m_mil.add(trueno);
		m_ma.add(focoResplandor); m_ma.add(trueno); m_ma.add(ondaTrueno); m_ma.add(explosion);
		m_ge.add(bolaSombra); m_ge.add(bomba_lodo); m_ge.add(protec); m_ge.add(brilloMagico);
		m_ga.add(psicocarga); m_ga.add(brilloMagico); m_ga.add(maquinacion); m_ga.add(hipnosis);
		m_gl.add(ventisca); m_ag.add(bolaSombra); m_be.add(trueno); m_mim.add(triturar);
		m_sa.add(llamarada); m_sa.add(toxico); m_sa.add(bomba_lodo); m_sa.add(protec);
		m_re.add(psiquico); m_re.add(bolaSombra); m_re.add(abocajarro); m_re.add(energibola);
		m_sce.add(energibola); m_sce.add(terremoto); m_sce.add(tijeraX); m_sce.add(puyaN);
		m_sco.add(tijeraX); m_sco.add(megacuerno); m_sco.add(terremoto); m_sco.add(puyaN);
		m_um.add(protec); m_um.add(gigaimpacto); m_um.add(pulsoUmbrio); m_um.add(rayoConfuso);
		m_ly.add(rocaAfilada); m_ly.add(triturar); m_ly.add(danzaEspada); m_ly.add(dobleFilo);
		m_lu.add(trueno); m_lu.add(triturar); m_lu.add(dobleFilo); m_lu.add(danzaEspada);
		m_ag.add(rocaAfilada); m_ag.add(defensaFerrea); m_ag.add(cabezaHierro); m_ag.add(terremoto);
		m_be.add(abocajarro); m_be.add(dobleFilo); m_be.add(golpeAereo); m_be.add(garraDragon);
		m_mim.add(sombraVil); m_mim.add(abocajarro); m_mim.add(ataqueAla); m_mim.add(tijeraX);

		e1.add(new Pokemon("Abomasnow", 1, 1, "Probando", 384, 283, 249, 283, 269, 219, 3, m_ab, Tipo.PLANTA, Tipo.HIELO));
		e1.add(new Pokemon("Dragonite", 1, 1, "Probando", 386, 367, 243, 299, 299, 259, 1, m_dr, Tipo.DRAGON, Tipo.VOLADOR));
		e1.add(new Pokemon("Aegislash", 1, 1, "yo", 324, 199, 399, 199, 399, 219, 1, m_ae, Tipo.ACERO, Tipo.FANTASMA));
		e1.add(new Pokemon("Charizard", 1, 1, "yo", 360, 267, 243, 317, 269, 299, 1, m_ch, Tipo.VOLADOR, Tipo.FUEGO));
		e1.add(new Pokemon("Vaporeon", 1, 1, "yo", 464, 229, 243, 319, 289, 229, 1, m_va, Tipo.AGUA));
		e1.add(new Pokemon("Tyranitar", 1, 1, "yo", 404, 367, 243, 289, 299, 221, 1, m_ty, Tipo.ROCA, Tipo.SINIESTRO));
		for (Pokemon p : e1) {
		p.setEstado(EstadosAlterados.NULL);
		}
		

			e2.add(new Pokemon("Serperior", 1, 1, "Probando", 354, 249, 243, 249, 289, 325, 3, m_se, Tipo.PLANTA));
			e2.add(new Pokemon("Spiritomb", 1, 1, "Probando", 304, 283, 243, 283, 315, 169, 1, m_sp, Tipo.FANTASMA, Tipo.SINIESTRO));
			e2.add(new Pokemon("Togekiss", 1, 1, "yo", 374, 199, 339, 329, 329, 259, 1, m_to, Tipo.VOLADOR, Tipo.HADA));
		e2.add(new Pokemon("Milotic", 1, 1, "yo", 394, 219, 243, 299, 349, 261, 1, m_mil, Tipo.AGUA));
		e2.add(new Pokemon("Magnezone", 1, 1, "yo", 344, 239, 243, 359, 279, 219, 1, m_ma, Tipo.ELECTRICO, Tipo.ACERO));
		e2.add(new Pokemon("Gengar", 1, 1, "yo", 324, 229, 243, 359, 249, 319, 1, m_ge, Tipo.FANTASMA, Tipo.VENENO));
		for (Pokemon p : e2) {
		p.setEstado(EstadosAlterados.NULL);
		}

			e3.add(new Pokemon("Gardevoir", 1, 1, "Probando", 340, 229, 243, 349, 329, 259, 3, m_ga, Tipo.HADA, Tipo.PSIQUICO));
			e3.add(new Pokemon("Glaceon", 1, 1, "Probando", 334, 219, 243, 359, 289, 229, 1, m_gl, Tipo.HIELO));
			e3.add(new Pokemon("Aggron", 1, 1, "yo", 334, 319, 459, 219, 219, 199, 1, m_ag, Tipo.ACERO, Tipo.ROCA));
		e3.add(new Pokemon("Bewear", 1, 1, "yo", 444, 349, 243, 209, 219, 219, 1, m_be, Tipo.NORMAL, Tipo.LUCHA));
		e3.add(new Pokemon("Mimikyu", 1, 1, "yo", 314, 279, 243, 199, 309, 291, 1, m_mim, Tipo.HADA, Tipo.FANTASMA));
		e3.add(new Pokemon("Salazzle", 1, 1, "yo", 340, 227, 243, 321, 219, 333, 1, m_sa, Tipo.FUEGO, Tipo.VENENO));
		for (Pokemon p : e3) {
		p.setEstado(EstadosAlterados.NULL);
		}
		
		e4.add(new Pokemon("Reuniclus", 1, 1, "Probando", 424, 229, 243, 349, 269, 169, 3, m_re, Tipo.PSIQUICO));
		e4.add(new Pokemon("Sceptile", 1, 1, "Probando", 344, 269, 243, 309, 269, 339, 1, m_sce, Tipo.PLANTA));
		e4.add(new Pokemon("Scolipede", 1, 1, "yo", 324, 299, 243, 209, 237, 323, 1, m_sco, Tipo.VENENO, Tipo.BICHO));
		e4.add(new Pokemon("Umbreon", 1, 1, "yo", 394, 229, 243, 219, 359, 229, 1, m_um, Tipo.SINIESTRO));
		e4.add(new Pokemon("Lycanroc", 1, 1, "yo", 464, 229, 243, 319, 289, 229, 1, m_ly, Tipo.ROCA));
		e4.add(new Pokemon("Luxray", 1, 1, "yo", 354, 329, 243, 209, 229, 323, 1, m_lu, Tipo.ELECTRICO));
		for (Pokemon p : e4) {
		p.setEstado(EstadosAlterados.NULL);
		}
		}
	
	public static ArrayList<Pokemon> devuelveEquipo(){
		Random ran = new Random();
		int num = ran.nextInt(5);
		
		if (num<=1) return e1;
		if (num==2) return e2;
		if (num==3) return e3;
		return e4;
	}

	}
