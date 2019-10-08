package principal;

import main.EstadosJuego;
import main.Main;

public class MiHilo implements Runnable {
	
	private boolean running;
	private Thread game;
	private Combate c;
	private VentanaJuego v;
	
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
		
		
		//tenemos que pasarle el ppokemon quue este actualmente en el campo 
		//(en la ventana crear un metodo que lo devuelva) y el moviento que se ha pulsado
		// tambien en la ventana
		
		// solo es de prueba
		c.getpEnemigo().setPs((int)(c.getpEnemigo().getPs() - c.calculaDaño(c.getpActivo(), c.getpEnemigo(), c.getpActivo().getMovimientos_poke().get(1))));
		v.getVida_2().setValue(c.getpEnemigo().calcuPsPorcentaje());
		v.revalidate();
			
		c.getpActivo().setPs((int)(c.getpActivo().getPs() - c.calculaDaño( c.getpEnemigo(),c.getpActivo(), c.getpEnemigo().getMovimientos_poke().get(1))));
		v.getVida_1().setValue(c.getpActivo().calcuPsPorcentaje());
		v.revalidate();
		
		
		VentanaJuego.esperar = 0;
		VentanaJuego.estado = EstadosJuego.ESPERANDO;
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
