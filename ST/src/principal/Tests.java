package principal;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Tests {

	@Test
	public void calculaDañoTest() {
		
		Movimiento movi = new Movimiento("TIERRA", Tipo.TIERRA, 100, 100, 100, CategoriaMov.ESPECIAL, 0);
		Movimiento movi2 = new Movimiento("FANTASMA", Tipo.FANTASMA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi3 = new Movimiento("SINIESTRO", Tipo.SINIESTRO, 100, 100, 100,CategoriaMov.ESPECIAL, 0);
		Movimiento movi4 = new Movimiento("ROCA", Tipo.ROCA, 100, 100, 100,CategoriaMov.ESPECIAL, 0);		
		
		ArrayList<Movimiento> m = new ArrayList<>();
		m.add(movi); m.add(movi2); m.add(movi3); m.add(movi4);
		
		Pokemon atacante = new Pokemon("Torterra", 1, 1, "Probando", 600, 1, 1, 1, 1, 1, 1, m, Tipo.TIERRA);
		
		Pokemon defensor = new Pokemon("Lumineon", 1, 1, "Probando", 350, 1, 1, 1, 1, 1, 1, m, Tipo.AGUA);
		
		assertEquals(79.0, Combate.calculaDaño(atacante, defensor, atacante.getMovimientos_poke().get(3)), 6.5);
	}
}
