package principal;

import java.awt.Color;
import java.awt.Component;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

import main.EstadosJuego;
import main.Main;

public class MiHilo implements Runnable {
	
	private boolean running;
	private Thread game;
	private Combate c;
	private VentanaJuego v;
	private Pokemon primero;//guarda las priodidades del pokemon 
	private Pokemon segundo;
	private Movimiento prim_mov;
	private Movimiento seg_mov;
	
	public MiHilo(Combate c, VentanaJuego v) {
		this.c = c;
		this.v = v;
		
	}
	@Override
	public void run() {
		while(running) { 
			if (Main.cerrada) {
				stop();
			}
			if (VentanaJuego.estado == EstadosJuego.CALCULANDO) 
				actualizar();
			try { 
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (VentanaJuego.estado == EstadosJuego.POKE_DEBILITADO) cambiaPokeDebilitado(); //El hilo nunca llega aquí. TODO
		}
	}
	
	private void actualizar() {
		
		if (c.isJ1_cambia()) {
			ImageIcon icono_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpActivo().getNombre() +"_espaldas.png"));
			ImageIcon icono_2 = new ImageIcon(icono_1.getImage().getScaledInstance(290, 290, java.awt.Image.SCALE_DEFAULT));
			v.getPoke_1().setIcon(icono_2);
			
		}
		
		if (c.isJ2_cambia()) {
			ImageIcon iconoo_1 = new ImageIcon(getClass().getResource("/img/"+ c.getpEnemigo().getNombre() +"_frente.png"));
			ImageIcon iconoo_2 = new ImageIcon(iconoo_1.getImage().getScaledInstance(220, 220, java.awt.Image.SCALE_DEFAULT));
			v.getPoke_2().setIcon(iconoo_2);
			
		}
		
		actualizar_progress_bar();
		//tenemos que pasarle el pokemon que este actualmente en el campo 
		//(en la ventana crear un metodo que lo devuelva) y el moviento que se ha pulsado
		// tambien en la ventana
		
		if (c.isJ1_cambia()) { //Lo que ocurre si el J1 cambia y el J2 no.
			if(!c.isJ2_cambia()) {
				primero = c.getpEnemigo();
				prim_mov = c.getMovEnemigo();
				segundo = c.getpActivo();
				seg_mov = null;
			} else { //Lo que ocurre si ambos cambian
				prim_mov = null;
				seg_mov = null;
			}
		} else if (c.isJ2_cambia()) { //Lo que ocurre si el J2 cambia y el J1 no.
			primero = c.getpActivo();
			prim_mov = c.getMovActivo();
			segundo = c.getpEnemigo();
			seg_mov = null;
			
		} else { //Lo que ocurre si nadie cambia.
			if (c.getpActivo().getVelocidad() > c.getpEnemigo().getVelocidad()) {
				primero = c.getpActivo();
				segundo = c.getpEnemigo();
				prim_mov = c.getMovActivo();
				seg_mov = c.getMovEnemigo();
			}
			if (c.getpActivo().getVelocidad() < c.getpEnemigo().getVelocidad()) {
				primero = c.getpEnemigo();
				segundo = c.getpActivo();
				prim_mov = c.getMovEnemigo();
				seg_mov = c.getMovActivo();
			}
			if (c.getpActivo().getVelocidad() == c.getpEnemigo().getVelocidad()) {
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
		
		if (prim_mov != null && prim_mov.getCat() == CategoriaMov.ESTADO) {
			actualizarEstado();
		}
		if (seg_mov != null &&  seg_mov.getCat() == CategoriaMov.ESTADO) {
			actualizarEstado();
		}
		
		if (prim_mov != null) { //prim_mov solo será null si ambos han cambiado, en cuyo caso no habrá cambios en la vida de los poke.
			actualizar_daño();
			//actualizar_progress_bar();
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
		
		VentanaJuego.esperar = 0;
		VentanaJuego.estado = EstadosJuego.ESPERANDO;
	}
	
	private void cambiaPokeDebilitado() { //TODO
		boolean debilitadoJ1 = false;
		boolean debilitadoJ2 = false;

		if(c.getpActivo().getEstado() == EstadosAlterados.DEBILITADO) { debilitadoJ1 = true; v.cambiaPanelInfo("Cambia a " + c.getpActivo().getNombre() + "."); }

		if(c.getpEnemigo().getEstado() == EstadosAlterados.DEBILITADO) { debilitadoJ2 = true; v.cambiaPanelInfo("Cambia a " + c.getpEnemigo().getNombre() + "."); }
		
		while (debilitadoJ1) {
			if (c.getpActivo().getEstado() != EstadosAlterados.DEBILITADO) break;
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
		
		while (debilitadoJ2) {
			
		}
		

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
	private void actualizarEstado() {
				//TODO
	}
	
	private void actualizar_progress_bar_1a1(Pokemon poke, JProgressBar barradeVida) {
		poke.setPs(poke.getPs() - 1);
		barradeVida.setValue(poke.calcuPsPorcentaje());
		System.out.println(poke.getPs());
		if (poke.getPs() < poke.getPs_max() / 2) {
			barradeVida.setForeground(Color.YELLOW);
			if (poke.getPs() < poke.getPs_max() / 4) {
				barradeVida.setForeground(Color.RED);
			}
		}
	}
	//estaba mal la actualizacion del progress bar no se podia saber cual era el 1 o el 2
	// es decir a cual sumarle el daño, asi que se actualiza al final ambos (DE MOMENTO)
	private void actualizar_daño() {
			
			int psPoke = segundo.getPs();
			int psPokeDam = ( (int)(segundo.getPs() - c.calculaDaño( primero, segundo, prim_mov)));
			v.cambiaPanelInfo(primero.getNombre() + " ha usado " + prim_mov.getNombre() + ".");
			for (int i = psPoke; i > psPokeDam; i--) {
				if (segundo == c.getpActivo()) {
					actualizar_progress_bar_1a1(c.getpActivo(),v.getVida_1() );
					if (comprobar_muerte(c.getpActivo())) {
						VentanaJuego.estado = EstadosJuego.POKE_DEBILITADO;
						v.cambiaPanelInfo("¡" + c.getpActivo().getNombre() +  " se ha debilitado!");
						try {
							Thread.sleep(200); //Sleep para que de tiempo a leerse el mensaje de debilitación del pokémon.
						} catch (Exception e) {
						}
						return;
					}
//					segundo.setPs(segundo.getPs() - 1);
//					v.getVida_1().setValue(segundo.calcuPsPorcentaje());
//					System.out.println(segundo.getPs());
//					if (segundo.getPs() < segundo.getPs_max() / 2) {
//						v.getVida_1().setForeground(Color.YELLOW);
//						if (segundo.getPs() < segundo.getPs_max() / 4) {
//							v.getVida_1().setForeground(Color.RED);
//						}
//					}	
				}else {
					actualizar_progress_bar_1a1(c.getpEnemigo(), v.getVida_2());
					if (comprobar_muerte(c.getpEnemigo())) {
						VentanaJuego.estado = EstadosJuego.POKE_DEBILITADO;
						v.cambiaPanelInfo("¡" + c.getpEnemigo().getNombre() +  " se ha debilitado!");
						try {
							Thread.sleep(200); //Sleep para que de tiempo a leerse el mensaje de debilitación del pokémon.
						} catch (Exception e) {
						}
						return;
					}
//				segundo.setPs(segundo.getPs() - 1);
//				v.getVida_2().setValue(segundo.calcuPsPorcentaje());
//				System.out.println(segundo.getPs());
//				if (segundo.getPs() < segundo.getPs_max() / 2) {
//					v.getVida_2().setForeground(Color.YELLOW);
//					if (segundo.getPs() < segundo.getPs_max() / 4) {
//						v.getVida_2().setForeground(Color.RED);
//					}
//				}	
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			
			if (seg_mov == null) return;
//			if (segundo.getPs() <= 0) { VentanaJuego.estado = EstadosJuego.POKE_DEBILITADO; return;}
			
			int psPoke2 = primero.getPs();
			int psPoke2Dam = ( (int)(primero.getPs() - c.calculaDaño( segundo, primero, seg_mov)));
			v.cambiaPanelInfo(segundo.getNombre() + " ha usado " + seg_mov.getNombre() + ".");

			for (int i = psPoke2; i > psPoke2Dam; i--) {
				if (primero == c.getpActivo()) {
					actualizar_progress_bar_1a1(c.getpActivo(), v.getVida_1());
					if (comprobar_muerte(c.getpActivo())) {
						VentanaJuego.estado = EstadosJuego.POKE_DEBILITADO;
						v.cambiaPanelInfo("¡" + c.getpActivo().getNombre() +  " se ha debilitado!");
						return;
					}
//					primero.setPs(primero.getPs() - 1);
//					v.getVida_1().setValue(primero.calcuPsPorcentaje());
//					if (primero.getPs() < primero.getPs_max() / 2) {
//						v.getVida_1().setForeground(Color.YELLOW);
//						if (primero.getPs() < primero.getPs_max() / 4) {
//							v.getVida_1().setForeground(Color.RED);
//						}
//					}
				}else {
					actualizar_progress_bar_1a1(c.getpEnemigo(), v.getVida_2());
					if (comprobar_muerte(c.getpEnemigo())) {
						VentanaJuego.estado = EstadosJuego.POKE_DEBILITADO;
						v.cambiaPanelInfo("¡" + c.getpEnemigo().getNombre() +  " se ha debilitado!");
						return;
					}
//					primero.setPs(primero.getPs() - 1);
//					v.getVida_2().setValue(primero.calcuPsPorcentaje());
//					if (primero.getPs() < primero.getPs_max() / 2) {
//						v.getVida_2().setForeground(Color.YELLOW);
//						if (primero.getPs() < primero.getPs_max() / 4) {
//							v.getVida_2().setForeground(Color.RED);
//						}
//					}
				}	
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}	
			
	}
	
	private boolean comprobar_muerte(Pokemon poke) { //No se mueren, tú, solo se debilitan, pobrecitos
		if (poke.getPs() == 0) {
			poke.setEstado(EstadosAlterados.DEBILITADO);
			return true;
		}
		else {
			return false;
		}
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
