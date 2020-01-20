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
                    conexones.get(conexones.size() - 1).setName(""+(conexones.size() - 1));
                    conexones.get(conexones.size() - 1).start();
                }
                ssockect.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
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
						equipoList = (ArrayList<Pokemon>) in.readObject();
					    if (conexones.size() >= 2 && Cliente.estado == EstadsCliente.ESPERANDO) {
					    	mandarventanaJuego();
					    }
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

        private void mandarventanaJuego() {
			Cliente.estado = EstadsCliente.EN_PARTIDA;
			ArrayList<Pokemon> equipoPokemons_J2 = null;
			for (RespHilos rep: conexones) {
				equipoPokemons_J2 = rep.equipoList;
			}
			Combate c = new Combate(equipoList, equipoPokemons_J2);
			VentanaJuego vj = new VentanaJuego(c);
			try {
				out.writeObject(vj);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		public final void output(Socket sock, String message) throws IOException {
            this.out.writeUTF(this.getName() + ": " + message);
        }
    }
}
