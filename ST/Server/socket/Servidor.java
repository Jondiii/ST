package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	private ServerSocket serverSocket;
	private ArrayList<Socket> guarda_peticianes = new ArrayList<Socket>();
	private ArrayList<ClientHandler> guarda_hilo_cliente = new ArrayList<ClientHandler>();
	
	public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
        	Socket s = null;
            try  { 
            	s = serverSocket.accept();
            	guarda_peticianes.add(s);
            	
            	System.out.println("A new client is connected : " + s); 
 
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); 
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                
                System.out.println("Assigning new thread for this client"); 

                Thread t = new ClientHandler(s, ois, oos);
                guarda_hilo_cliente.add((ClientHandler) t);
                t.start();
            	
            }catch (Exception e) {
				s.close(); 
				e.printStackTrace(); 
			}
        }
           
    }
	public void stop() throws IOException {
        serverSocket.close();
    }
	class ClientHandler extends Thread  {
		
		final ObjectInputStream ois; 
	    final ObjectOutputStream oos; 
	    final Socket s; 

	    public ClientHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos){ 
	        this.s = s; 
	        this.ois = ois; 
	        this.oos = oos;
	    } 
	    
	    @Override
	    public void run(){ 

	        while (true){ 
	        }   
	    }
	    
	}
	public static void main(String[] args) throws IOException {
		Servidor s = new Servidor();
		s.start(5000);
	}
	
}
