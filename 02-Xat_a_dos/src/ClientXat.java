import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientXat {
    
    private final int PORT = 9999;
    private final String HOST = "localhost";
    
    static Socket socket = new Socket();

    ObjectOutputStream outputStream;
    static ObjectInputStream inputStream;

    public void connecta() throws IOException {

        socket = new Socket(HOST, PORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connectat al servidor " + HOST + ":" + PORT);

    }

    public void enviarMissatge(String missatge) throws IOException {

        outputStream.writeObject(missatge);
        outputStream.flush();

    }

    public void tancarClient() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();

        System.out.println("Client tancat");

    }

    public static void main(String[] args) throws IOException {
        ClientXat clientXat = new ClientXat();

        clientXat.connecta();
        Scanner sc = new Scanner(System.in);
        String missatge;
        FilLectorCX filLectorCX = new FilLectorCX(inputStream);

        filLectorCX.start();
        
        while (!(missatge = sc.nextLine()).equals("sortir"))  {
            
            clientXat.enviarMissatge(missatge);

        }

        try {
            filLectorCX.join();
        } catch (InterruptedException e) {
           System.out.println("Vaya liada");
        }

        clientXat.tancarClient();
    }




}
