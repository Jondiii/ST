package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ventanas.VentanaInicio;
import ventanas.VentanaJuego;
import ventanas.VentanaSelecion;

public class Cliente {

	    private static Socket clientSocket;
	    private boolean conectado = true;
	    private static ObjectOutputStream oos;
	    private static ObjectInputStream in;
		
	    
	    public void startConnection(String ip, int port) throws IOException, ClassNotFoundException {
	            clientSocket = new Socket(ip, port);
	            oos = new ObjectOutputStream(clientSocket.getOutputStream());
	            in = new ObjectInputStream(clientSocket.getInputStream());
	            new readThread().start();
	            sendObject(VentanaSelecion.pokemons_para_combatir);
   
	    }

	    public static class readThread extends Thread {
	        @Override
	        public void run() {
	            boolean loop = true;
	            while (loop) {
	                    Object msg;
						try {
							msg = in.readObject();
							if (msg instanceof VentanaJuego) {
								((VentanaJuego) msg).setVisible(true);
							}
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
	            
	        }
	    }
	    }
	    
	   
	    public static void sendObject(Object o) {
	    	try {
				oos.writeObject(o);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    public void stopConnection() throws IOException {
	            clientSocket.close();
	        

	    }
	    
	}