import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static final String HOST = "localhost";
    static final int PORT = 7777;
    static Socket socket;

    static PrintWriter out;

    public static void connecta() throws IOException {
        socket = new Socket(HOST, PORT);
        out = new PrintWriter(socket.getOutputStream());
        System.out.println("Connectat al servidor en " + HOST+ ":" + PORT);
    }
    

    public static void tanca() throws IOException {
        out.close();
        socket.close();
        System.out.println("Client tancat");
    }

    public static void envia(String text) throws IOException {
        out.println(text);
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        String[] proves = {"Prova d'enviament 1", 
                           "Prova d'enviament 2","Adeu!"};
        Scanner sc = new Scanner(System.in);


        connecta();

        for (String s : proves) {
            envia(s);
        }
        
        System.out.println("Prem ENTER per tancar el client");
        sc.nextLine();

        tanca();

        

    }

     
}
