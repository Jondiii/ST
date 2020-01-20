package principal;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

import main.EstadosJuego;
import main.Main;
import ventanas.VentanaJuego;

public class HiloJuego implements Runnable {
	
	private boolean running;
	private Thread game;
	private Combate c;
	private VentanaJuego v;
	private Pokemon primero;//guarda las priodidades del pokemon 
	private Pokemon segundo;
	private Movimiento prim_mov;
	private Movimiento seg_mov;
	public static int turno = 0;
	
	public HiloJuego(Combate c, VentanaJuego v) {
		this.c = c;
		this.v = v;
		
	}
	@Override
	public void run() {
		while(running) { 
			if (Main.cerrada) {
				stop();
			}
			if (VentanaJuego.estado == EstadosJuego.CALCULANDO 
					|| (VentanaJuego.estado == EstadosJuego.POKE_DEBILITADO && c.isJ1_cambia()&&!c.isJ2_accion_hecha())
					|| (VentanaJuego.estado == EstadosJuego.POKE_DEBILITADO && c.isJ2_cambia()&&!c.isJ1_accion_hecha()))
			
				actualizar();
			try {	
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void actualizar() {
		
		turno ++;
		v.getHc().añadirTurno("Turno  " + turno);
		
		if (c.isJ1_cambia()) {
			ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpActivo().getNombre() +"_espaldas.png"));
			ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(290, 290, java.awt.Image.SCALE_DEFAULT));
			v.getPoke_1().setIcon(icono_2);
		
			
				
				v.cambiaPanelInfo("Cambia a " + c.getpActivo().getNombre() + ".");
			
			
		}
		
		if (c.isJ2_cambia()) {
			ImageIcon iconoo_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpEnemigo().getNombre() +"_frente.png"));
			ImageIcon iconoo_2 = new ImageIcon(iconoo_1.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT));
			v.getPoke_2().setIcon(iconoo_2);
//				if (VentanaJuego.estado == EstadosJuego.POKE_DEBILITADO && c.isJ2_accion_hecha()) {
				
				v.cambiaPanelInfo("Cambia a " + c.getpEnemigo().getNombre() + ".");
						
		}
		
		v.cambiaMovimientos();
		
		actualizar_progress_bar();
		//tenemos que pasarle el pokemon que este actualmente en el campo 
		//(en la ventana crear un metodo que lo devuelva) y el moviento que se ha pulsado
		// tambien en la ventana
		
		if (c.isJ1_cambia()&& VentanaJuego.estado == EstadosJuego.CALCULANDO) { //Lo que ocurre si el J1 cambia y el J2 no.
			if(!c.isJ2_cambia()) {
				primero = c.getpEnemigo();
				prim_mov = c.getMovEnemigo();
				segundo = c.getpActivo();
				seg_mov = null;
				actualizar_est(c.getpActivo(), c.getpActivo().getEstado());
				actualizar_est(c.getpEnemigo(), c.getpEnemigo().getEstado());
				
			} else { //Lo que ocurre si ambos cambian
				prim_mov = null;
				seg_mov = null;
				
				actualizar_est(c.getpActivo(), c.getpActivo().getEstado());
				actualizar_est(c.getpEnemigo(), c.getpEnemigo().getEstado());
			}
		} else if (c.isJ2_cambia()) { //Lo que ocurre si el J2 cambia y el J1 no.
			primero = c.getpActivo();
			prim_mov = c.getMovActivo();
			segundo = c.getpEnemigo();
			seg_mov = null;
			
			actualizar_est(c.getpActivo(), c.getpActivo().getEstado());
			actualizar_est(c.getpEnemigo(), c.getpEnemigo().getEstado());
			
		} else { //Lo que ocurre si nadie cambia.
			if (c.getMovActivo().getPrioridad() > c.getMovEnemigo().getPrioridad()) {
				primero = c.getpActivo();
				segundo = c.getpEnemigo();
				prim_mov = c.getMovActivo();
				seg_mov = c.getMovEnemigo();
			}else if(c.getMovActivo().getPrioridad() < c.getMovEnemigo().getPrioridad()) {
				primero = c.getpEnemigo();
				segundo = c.getpActivo();
				prim_mov = c.getMovEnemigo();
				seg_mov = c.getMovActivo();
			}else if (c.getMovActivo().getPrioridad() == c.getMovEnemigo().getPrioridad()) {
				if (c.getpActivo().getVelocidadStat() > c.getpEnemigo().getVelocidadStat()) {
					primero = c.getpActivo();
					segundo = c.getpEnemigo();
					prim_mov = c.getMovActivo();
					seg_mov = c.getMovEnemigo();
				}else if (c.getpActivo().getVelocidadStat() < c.getpEnemigo().getVelocidadStat()) {
					primero = c.getpEnemigo();
					segundo = c.getpActivo();
					prim_mov = c.getMovEnemigo();
					seg_mov = c.getMovActivo();
				}else if (c.getpActivo().getVelocidadStat() == c.getpEnemigo().getVelocidadStat()) {
					Random aleatorio = new Random();
					int intAletorio = aleatorio.nextInt(2);
					if (intAletorio == 0) {
						primero = c.getpActivo();
						segundo = c.getpEnemigo();
						prim_mov = c.getMovActivo();
						seg_mov = c.getMovEnemigo();
					}else {
						primero = c.getpEnemigo();
						segundo = c.getpActivo();
						prim_mov = c.getMovEnemigo();
						seg_mov = c.getMovActivo();
					}
				}
			}
		}
		
		if (VentanaJuego.estado == EstadosJuego.CALCULANDO) {
		
		boolean primero_inmune = false;
		boolean segundo_inmune = false;
		if (seg_mov != null && seg_mov.getCat() == CategoriaMov.ESTADO) {
			if (segundo.equals(c.getpActivo())) {
				if (EfectoSecundario.seProtege(seg_mov, 1)) {
					c.setJ1_inmune(true);
					segundo_inmune = true;
					System.out.println("Estoy protegidísimo loco J1");
				}		
			} if (segundo.equals(c.getpEnemigo())) {
				if(EfectoSecundario.seProtege(seg_mov, 2)) {
					c.setJ2_inmune(true);
					segundo_inmune = true;
					System.out.println("Estoy protegidísimo loco J2");
				}		
			}
		}
//		
//		if (prim_mov != null) { //prim_mov solo será null si ambos han cambiado, en cuyo caso no habría cambios en la vida de los poke.
//			actualizar_daño();
//			//actualizar_progress_bar();
//		}
		
		if (prim_mov != null ) {
			if (primero.equals(c.getpActivo())) {
				if(EfectoSecundario.seProtege(prim_mov, 1)) {
					c.setJ1_inmune(true);
					primero_inmune = true;
				}			
			}
			if (primero.equals(c.getpEnemigo())) {
				if(EfectoSecundario.seProtege(prim_mov, 2)) {
					c.setJ2_inmune(true);
					primero_inmune = true;
				}		
			}
			actualizar_daño_individual(primero, segundo, prim_mov, segundo_inmune);

			if (segundo.getEstado() == EstadosAlterados.DEBILITADO) {
				//Si está debilitado no puede atacar
			}else { 
				if (seg_mov != null) {
					actualizar_daño_individual(segundo, primero, seg_mov, primero_inmune);
				}	
			}
			if (primero.getEstado() != EstadosAlterados.DEBILITADO ) {
				int dañoEstado = EstadosAlterados.calcularEstadoFinTurno(primero);
				if (primero == c.getpActivo())	actualizar_progress_bar_1a1(primero, v.getVida_1(), dañoEstado);
				if (primero == c.getpEnemigo()) actualizar_progress_bar_1a1(primero, v.getVida_2(), dañoEstado);
			}
			if (segundo.getEstado() != EstadosAlterados.DEBILITADO ) {
				int dañoEstado = EstadosAlterados.calcularEstadoFinTurno(segundo);
				if (segundo == c.getpActivo())	actualizar_progress_bar_1a1(segundo, v.getVida_1(), dañoEstado);
				if (segundo == c.getpEnemigo()) actualizar_progress_bar_1a1(segundo, v.getVida_2(), dañoEstado);
			}	
		}
		}
		//me he dado cuenta de que esto lo habia hecho mal,lo cambio para que se actualica genericamente
	
		//que compruebe si el pokemon esta debilitado
		v.revalidate();
		
		//Los siguientes dos bucles sirven para reactivar los botones una vez ambos jugadores han seleccionado su movimiento.
//		for (Component boton : VentanaJuego.panel_movimientos_1.getComponents()) {
//			((JButton)boton).setEnabled(true);
//		}
//		for (Component boton : VentanaJuego.panel_movimientos_2.getComponents()) {
//			((JButton)boton).setEnabled(true);
//		}
		
//		v.getPanel_j1().setEnabled(true); 
//		v.getPanel_j2().setEnabled(true);
		
		c.setJ1_accion_hecha(false);
		c.setJ2_accion_hecha(false);
		c.setJ1_cambia(false);
		c.setJ2_cambia(false);
		
		if (VentanaJuego.estado == EstadosJuego.POKE_DEBILITADO) {
			return;
		}
		//VentanaJuego.esperar = 0;
		VentanaJuego.estado = EstadosJuego.ESPERANDO;
	}
	
	private void actualizar_progress_bar() {
		v.getVida_1().setValue(c.getpActivo().calcuPsPorcentaje());
		if (c.getpActivo().getPs() < c.getpActivo().getPs_max() / 2) {
			v.getVida_1().setForeground(Color.YELLOW);
			if (c.getpActivo().getPs() < c.getpActivo().getPs_max() / 4) { //Antes en vez de c.getpActivo().getPs_max() ponía segundo.getPs_Max()
				v.getVida_1().setForeground(Color.RED);
			}
		}
		else {
			v.getVida_1().setForeground(Color.GREEN);
		}
		v.getVida_2().setValue(c.getpEnemigo().calcuPsPorcentaje());
		if (c.getpEnemigo().getPs() < c.getpEnemigo().getPs_max() / 2) {
			v.getVida_2().setForeground(Color.YELLOW);
			if (c.getpEnemigo().getPs() < c.getpEnemigo().getPs_max() / 4) { //Antes en vez de c.getpEnemigo().getPs_max() ponía segundo.getPs_Max()
				v.getVida_2().setForeground(Color.RED);
			}
		}else {
			v.getVida_2().setForeground(Color.GREEN);
		}
		v.revalidate();
	}
	
	/**
	 * Método que actualiza el estado alterado de un pokémon.
	 */
	private void actualizarEstadoAlterno(Pokemon oponente, Movimiento m) {	
		if (m.getEstadoAlt()== null) return;
		Random aleatorio = new Random();
		int intAletorio = aleatorio.nextInt(101);
		if (m.getProb_efecto() >= intAletorio) {
			if (comprobarInmunidadEstado(m.getEstadoAlt(), oponente)) {return;}
			oponente.setEstado(m.getEstadoAlt());
			v.cambiaPanelInfo(oponente.getNombre() + " ha sido " + oponente.getEstado());
			actualizar_est(oponente, oponente.getEstado());
			}
		
		
	}
	
	
	private boolean comprobarInmunidadEstado(EstadosAlterados estadoAlt, Pokemon oponente) {
		if (estadoAlt.equals(EstadosAlterados.QUEMADO)) {
			if (oponente.getTipos().get(0).equals(Tipo.FUEGO)) {return true;}
			if (oponente.getTipos().size() == 2) {if (oponente.getTipos().get(1).equals(Tipo.FUEGO)) {return true;}}
			else {return false;}
		}
		if (estadoAlt.equals(EstadosAlterados.ENVENENADO)) {
			if (oponente.getTipos().get(0).equals(Tipo.VENENO) || oponente.getTipos().get(0).equals(Tipo.ACERO)) {return true;}
			if (oponente.getTipos().size() == 2) {if (oponente.getTipos().get(1).equals(Tipo.VENENO) || oponente.getTipos().get(1).equals(Tipo.ACERO)) {return true;}}
			else {return false;}
		}
		if (estadoAlt.equals(EstadosAlterados.PARALIZADO)) {//pokemon tipo tierra??
			if (oponente.getTipos().get(0).equals(Tipo.ELECTRICO)) {return true;}
			if (oponente.getTipos().size() == 2) {if (oponente.getTipos().get(1).equals(Tipo.ELECTRICO)) {return true;}}
			else {return false;}
		}
		if (estadoAlt.equals(EstadosAlterados.CONGELADO)) {
			if (oponente.getTipos().get(0).equals(Tipo.HIELO)) {return true;}
			if (oponente.getTipos().size() == 2) {if (oponente.getTipos().get(1).equals(Tipo.HIELO)) {return true;}}
			else {return false;}
		}
		return false;
	}
	private void actualizar_progress_bar_1a1(Pokemon poke, JProgressBar barradeVida, int vidaPokeDaño) {
		if (vidaPokeDaño == 0) {
			return;
		}else {
			poke.setPs(poke.getPs() - 1);
			if (autopsia(poke)) {
				VentanaJuego.estado = EstadosJuego.POKE_DEBILITADO;
				v.cambiaPanelInfo("¡" + poke.getNombre() +  " se ha debilitado!");
					try {
						Thread.sleep(600); //Sleep para que de tiempo a leerse el mensaje de debilitación del pokémon.
						todosMuertos();	
					} catch (Exception e) {
					}
					return;
				}else {
					barradeVida.setValue(poke.calcuPsPorcentaje());
					if (poke.getPs() < poke.getPs_max() / 2) {
						barradeVida.setForeground(Color.YELLOW);
						if (poke.getPs() < poke.getPs_max() / 4) {
							barradeVida.setForeground(Color.RED);
						}
					}
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					actualizar_progress_bar_1a1(poke, barradeVida, vidaPokeDaño - 1);
				}
		}
	}
	
	//He hecho esto para que se actualice el daño de los pokes individualmente, pero algo falla. Lo arreglo/miro el lunes
	private void actualizar_daño_individual(Pokemon atacante, Pokemon defensor, Movimiento mov, boolean protegido) {
		if(protegido) {
			return;
		}
		if (EstadosAlterados.calcularProbAtacar(atacante)) {
			actualizar_est(atacante, atacante.getEstado());
			int psPokeDam =  (int)( Combate.calculaDaño( atacante, defensor, mov));
			v.getHc().añadirPanel(atacante.getNombre() + " ha quitado " + psPokeDam + " ps al Enemigo");
			v.cambiaPanelInfo(atacante.getNombre() + " ha usado " + mov.getNombre() + ".");
			actualizarEstadoAlterno(defensor, mov);
			if (defensor == c.getpActivo())	actualizar_progress_bar_1a1(defensor, v.getVida_1(), psPokeDam);
			if (defensor == c.getpEnemigo()) actualizar_progress_bar_1a1(defensor, v.getVida_2(), psPokeDam);

			if (mov.isCambiaStatsAEnemigo() == false) {
				atacante.setCambiosEstadisticas( c.calculaCambiosStats(atacante, mov));
				//TODO faltaría añadir un mensaje indicando los cambios, y también que se indiquen los cambios en la pantalla de info de los pokes.
			}
			if (mov.isCambiaStatsAEnemigo() == true) atacante.setCambiosEstadisticas( c.calculaCambiosStats(defensor, mov));
		}
		else {
			if (atacante.getEstado() == EstadosAlterados.CONFUSO ) {
				int dañoConfus =  ((int)( Combate.calculaDaño( atacante, atacante, mov))/3);
				v.cambiaPanelInfo(atacante.getNombre() + " está confuso y se ha herido a sí mismo.");
				if (atacante == c.getpActivo())	actualizar_progress_bar_1a1(atacante, v.getVida_1(), dañoConfus);
				if (atacante == c.getpEnemigo()) actualizar_progress_bar_1a1(atacante, v.getVida_2(), dañoConfus);
				
			}
		}
		
	}
	
	

	
	private void todosMuertos() {
		boolean vivoA = false;
		boolean vivoB = false;
		for(int i = 0; i < Combate.getAliados().size(); i++) {	
			ArrayList<Pokemon> listaPoke = Combate.getAliados();
			Pokemon poke = listaPoke.get(i);
			if(poke.getEstado() != EstadosAlterados.DEBILITADO) {
				vivoA = true;
			}
		}
		for(int i = 0; i < Combate.getOponentes().size(); i++) {
			ArrayList<Pokemon> listaPoke = Combate.getOponentes();
			Pokemon poke = listaPoke.get(i);
			if(poke.getEstado() != EstadosAlterados.DEBILITADO) {
				vivoB = true;
			}		
		}
		if (vivoA == false) {
			v.cambiaPanelInfo("¡Todos los Pokémon del J1 han sido debilitados, la victoria es para el J2!");
		}
		if (vivoB == false) {
			v.cambiaPanelInfo("¡Todos los Pokémon del J2 han sido debilitados, la victoria es para el J1!");
		}
		
	}
	private boolean autopsia(Pokemon poke) { //No se mueren, solo se debilitan, pobrecitos
		if (poke.getPs() == 0) {
			poke.setEstado(EstadosAlterados.DEBILITADO);
			actualizar_est(poke, poke.getEstado());
			return true;
		}
		else {
			return false;
		}
	}
	private void actualizar_est(Pokemon poke, EstadosAlterados est) {
		if (est == EstadosAlterados.NULL || est == EstadosAlterados.DEBILITADO) {
			if (poke == c.getpActivo()) {v.getEstadoAlterado1().setIcon(null);}
			if (poke == c.getpEnemigo()) {v.getEstadoAlterado2().setIcon(null);}
		}else {
			ImageIcon icono = new ImageIcon(getClass().getResource("/img/estados/"+ poke.getEstado().toString() + ".png"));
			if (poke == c.getpActivo())	{v.getEstadoAlterado1().setIcon(icono); v.revalidate();}
			if (poke == c.getpEnemigo()) {v.getEstadoAlterado2().setIcon(icono); v.revalidate();}
		}
		v.revalidate();
	}
	public void start() {
		if (running) return;
		running = true;
		game = new Thread(this, "game");
		game.start();
	}
	public void stop() {
		if (!running) return;
		running = false;
	}
	
	
	
}
