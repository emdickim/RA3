import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class FilServidorXat extends Thread {
    
    ObjectInputStream objectInputStream;
    String MSG_SORTIR = "sortir";

    public FilServidorXat(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String missatge = (String) objectInputStream.readObject();
                System.out.println("Rebut: " + missatge);

                if(missatge.equals(MSG_SORTIR)) {
                    System.out.println("Client desconectat");
                    break;
                }
            }
            objectInputStream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
