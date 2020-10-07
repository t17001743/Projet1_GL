/**
 * <h1>GL - Projet 1 (21/09/2020) M1 Informatique, Luminy</h1>
 *
 * @author CLERC Billy, COZZOLINO Christine, DE JAHAM Charles, TAPIN Léah, THIEL Samantha
 */

import GUI.*;
import OperativeAndControl.Computer;

public class Simulation {

    // Le nombre d'étage dans le bâtiment
    private static final int nbOfFloors = 20;

    /**
     * Le main du programme. C'est ici qu'on lance l'exécution de la simulation.
     * Cette dernière se fait par la création de deux threads, un qui gère l'ordinateur et un qui gère l'interface graphique
     * */
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
