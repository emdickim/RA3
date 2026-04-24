import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    private final int PORT = 7777;
    private String HOST = "localhost";
    private ServerSocket srvSocket;
    private Socket clientSocket;

    
    public void connecta () throws IOException {
        System.out.println("Servidor en marxa a " + HOST + ":" + PORT);
        this.srvSocket = new ServerSocket(PORT);
        System.out.println("Esperant connexions a " + HOST + ":" + PORT);
        
        this.clientSocket = srvSocket.accept();
        System.out.println("Client connectat a: " + clientSocket.getInetAddress());

    }

    public void repDades() throws IOException {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream())
        );

        while (true) {
            String linia = br.readLine();
            if (linia == null) break;
            System.out.println("Rebut: " + linia);
        }
        br.close();
    }

    public void tanca() throws IOException {
        clientSocket.close();
        srvSocket.close();
        System.out.println("Servidor tancat.");
    }

    public static void main(String[] args) throws IOException {
                Servidor servidor = new Servidor();
        try {
            servidor.connecta();
            servidor.repDades();
        } catch (IOException e) {
            System.err.println("Error al servidor: " + e.getMessage());
        } finally {
            servidor.tanca();
        }
    }

} 