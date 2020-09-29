import javax.swing.*;
import GUI.*;

public class Main {

    // Le nombre d'étage dans le bâtiment
    private static final int nbOfFloors = 20;

    public static void main(String[] args) {
        // On crée une instance de GUI
        GUI userInterface = new GUI();

        // On crée une instance de notre fenêtre qui représente le panneau intérieur
        InsidePanel insidePanel = new InsidePanel(nbOfFloors, userInterface);
        // On la rend visible
        insidePanel.setVisible(true);
        // On l'ajoute à notre GUI
        userInterface.setInsidePanel(insidePanel);

        // On crée une instance de notre fenêtre qui représente le panneau extérieur
        OutsidePanel outsidePanel = new OutsidePanel(nbOfFloors, userInterface);
        // On la rend visible
        outsidePanel.setVisible(true);
        // On l'ajoute à notre GUI
        userInterface.setOutsidePanel(outsidePanel);

        // On crée une instance de notre fenêtre qui représente l'ascenseur
        Elevator elevator = new Elevator(nbOfFloors, userInterface);
        // On la rend visible
        elevator.setVisible(true);
        // On l'ajoute à notre GUI
        userInterface.setElevator(elevator);
    }
}
