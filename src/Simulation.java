import GUI.*;
import OperativeAndControl.Computer;

public class Simulation {

    // Le nombre d'étage dans le bâtiment
    private static final int nbOfFloors = 20;

    public static void main(String[] args) {
        // On créer une instance de l'ordinateur
        Computer computer = new Computer(nbOfFloors);

        // On créer une instance de GUI
        GUI userInterface = new GUI(nbOfFloors, computer);

        // On créer un thread pour l'ordinateur et un pour l'interface graphique
        Thread computerThread = new Thread(computer);
        Thread userInterfaceThread = new Thread(userInterface);

        computerThread.start();
        userInterfaceThread.start();
    }
}
