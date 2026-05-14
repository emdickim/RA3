import java.io.ObjectInputStream;

public class FilLectorCX extends Thread {

    ObjectInputStream objectInputStream;

    public FilLectorCX(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run() {

        try {
            
            while (true) {
                String missatge = (String) objectInputStream.readObject();
                System.out.println("Servidor: " + missatge);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}