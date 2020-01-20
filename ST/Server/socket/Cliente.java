package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ventanas.VentanaInicio;
import ventanas.VentanaJuego;
import ventanas.VentanaSelecion;

public class Cliente {

	    private Socket clientSocket;
	    private static ObjectOutputStream oos;
	    private ObjectInputStream ois;
	    private boolean conectado = true;
	    
	    public void startConnection(String ip, int port) throws IOException, ClassNotFoundException {
	            clientSocket = new Socket(ip, port);
	            ois = new ObjectInputStream(clientSocket.getInputStream());
	            oos= new ObjectOutputStream(clientSocket.getOutputStream());
	            while (conectado) {
	            	Object o = ois.readObject();
	            	
	            }
	    }
	    
	    public static void sendObject (Object o) throws IOException {
	    	oos.writeObject(o);
	    }
	    public void stopConnection() throws IOException {
	            oos.close();
	            ois.close();
	            clientSocket.close();
	        

	    }
	}