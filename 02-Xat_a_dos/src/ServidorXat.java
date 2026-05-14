import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Stream;

public class ServidorXat {
    
    private final int PORT = 9999;

    private final String HOST = "localhost";
    private final String MSG_SORTIR = "sortir";

    ServerSocket serverSocket;

    public void iniciarServidor() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("ServidorXat en marxa a " + HOST + ":" + PORT);
        System.out.println("Esperant connexions...");

    }

    public void pararServidor() throws IOException {
        serverSocket.close();
        System.out.println("XatServidor tancat");

    }

    public String getNom(BufferedReader br) throws IOException {
        String nom = br.readLine();
        System.out.println("Client connectat: " + nom);
        return nom;
    }

    public static void main(String[] args) throws IOException {
        
        ServidorXat servidorXat = new ServidorXat();

        servidorXat.iniciarServidor();

        Socket clientSocket = servidorXat.serverSocket.accept();

        //Inicio els streams per rebre i enviar missatges
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream objectOuputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        FilServidorXat filServidorXat = new FilServidorXat(objectInputStream);
        
        filServidorXat.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            
            String missatge = sc.nextLine();
            objectOuputStream.writeObject(missatge);
            objectOuputStream.flush();

            if (missatge.equals("sortir")) break;

        }

        try {
            filServidorXat.join();
        } catch (InterruptedException e) {
           System.out.println("Vaya liada");
        }

        clientSocket.close();
        servidorXat.pararServidor();

            

    }

}
