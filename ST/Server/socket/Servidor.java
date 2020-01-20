package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import principal.Combate;
import principal.Pokemon;
import ventanas.VentanaJuego;
public class Servidor {

    public static volatile ArrayList<RespHilos> conexones = new ArrayList<>();
    public static void main(String[] args) {
        new Hilo_acp().start();
    }

    private static class Hilo_acp extends Thread {
        @Override
        public void run() {
            ServerSocket ssockect;
            try {
                ssockect = new ServerSocket(5000);
                boolean loop = true;
                while(loop) {
                    System.out.println("Esperando conexiones");
                    conexones.add(new RespHilos(ssockect.accept()));
                    System.out.println("Establecida la conexion");
                    if (conexones.size()>= 2) {
                    	mandarventanaJuego();
                    }
                }
                ssockect.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    
    private void mandarventanaJuego() {
    	ArrayList<Pokemon> equipoPokemons_J1 = conexones.get(0).getEquipoList();
		ArrayList<Pokemon> equipoPokemons_J2 = conexones.get(1).getEquipoList();
		
		
		try {
			
			Combate c = new Combate(equipoPokemons_J1, equipoPokemons_J2);
			VentanaJuego vj = new VentanaJuego(c);
			conexones.get(0).getOut().writeObject(vj);
			
			Combate c_J2 = new Combate(equipoPokemons_J2, equipoPokemons_J1);
			VentanaJuego vj_1 = new VentanaJuego(c_J2);
			conexones.get(1).getOut().writeObject(vj_1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    }
    public static class RespHilos extends Thread {
        private static Socket sock;
        private ObjectOutputStream out;
        private ArrayList<Pokemon> equipoList;
        public RespHilos(Socket newSock) {
            sock = newSock;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
                out = new ObjectOutputStream(sock.getOutputStream());

                boolean loop = true;
                while(loop) {
                  try {
					if( in.readObject() instanceof ArrayList ) {
						ArrayList<Pokemon> readObject = (ArrayList<Pokemon>) in.readObject();
						equipoList = readObject;
					    
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
                }

                in.close();
            } catch (SocketException ex) {
                //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Conexion terminada.");
                this.setName(this.getName() + "T");
                this.interrupt();
                System.out.println(this.getName() + " I se ha interrumpido");
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


		public final void output(Socket sock, String message) throws IOException {
            this.out.writeUTF(this.getName() + ": " + message);
        }

		public static Socket getSock() {
			return sock;
		}

		public ObjectOutputStream getOut() {
			return out;
		}

		public ArrayList<Pokemon> getEquipoList() {
			return equipoList;
		}
    }
}
