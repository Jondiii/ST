package principal;

import java.awt.Color;
import java.awt.Component;
import java.util.Random;

import javax.swing.JButton;

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
		}
	}
	private void actualizar() {
		
		
		//tenemos que pasarle el pokemon quue este actualmente en el campo 
		//(en la ventana crear un metodo que lo devuelva) y el moviento que se ha pulsado
		// tambien en la ventana
		
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
		if (prim_mov.getCat() == CategoriaMov.ESTADO) {
			actualizarEstado();
		}
		if (seg_mov.getCat() == CategoriaMov.ESTADO) {
			actualizarEstado();
		}
		actualizar_daño();//devuleve 0 si es de estado, en el calculo de daño.
		
		//que compruebe si el pokemon esta debilitado
		v.revalidate();
		
		
		//Los siguientes dos bucles sirven para reactivar los botones una vez ambos jugadores han seleccionado su movimiento.
		for (Component boton : VentanaJuego.panel_movimientos_1.getComponents()) {
			((JButton)boton).setEnabled(true);
		}
		for (Component boton : VentanaJuego.panel_movimientos_2.getComponents()) {
			((JButton)boton).setEnabled(true);
		}
		VentanaJuego.esperar = 0;
		VentanaJuego.estado = EstadosJuego.ESPERANDO;
	}
	private void actualizarEstado() {
		
		
	}
	private void actualizar_daño() {
		segundo.setPs((int)(segundo.getPs() - c.calculaDaño( primero, segundo, prim_mov)));
		v.getVida_2().setValue(segundo.calcuPsPorcentaje());
		if (segundo.getPs() < segundo.getPs_max() / 2) {
			v.getVida_2().setForeground(Color.YELLOW);
			if (segundo.getPs() < segundo.getPs_max() / 4) {
				v.getVida_2().setForeground(Color.RED);
			}
		}
		
		//si el pokemon esta debilitado despues del ataque
		primero.setPs((int)(primero.getPs() - c.calculaDaño(segundo, primero, seg_mov)));
		v.getVida_1().setValue(primero.calcuPsPorcentaje());
		if (primero.getPs() < primero.getPs_max() / 2) {
			v.getVida_1().setForeground(Color.YELLOW);
			if (primero.getPs() < primero.getPs_max() / 4) {
				v.getVida_1().setForeground(Color.RED);
			}
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
