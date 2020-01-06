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
        private DataOutputStream out;

        public RespHilos(Socket newSock) {
            sock = newSock;
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(sock.getInputStream());
                out = new DataOutputStream(sock.getOutputStream());

                boolean loop = true;
                while(loop) {
                    String msg = in.readUTF();
                    System.out.println(msg);
                    for (RespHilos thread : conexones) {
                        if (!thread.getName().contains("T")) thread.output(sock, msg);
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
    }
}
