import javax.imageio.ImageIO;
import javax.swing.*;
import GUI.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

    // Le nombre d'étage dans le bâtiment
    private static final int nbOfFloors = 20;

    public static void main(String[] args) {
        // D'après le tutoriel Swing, c'est plus performant de créer un thread pour chaque fenêtre
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // On crée une instance de notre fenêtre qui représente le panneau intérieur
                InsidePanel insidePanel = new InsidePanel(nbOfFloors);
                // On la rend visible
                insidePanel.setVisible(true);
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // On crée une instance de notre fenêtre qui représente le panneau extérieur
                OutsidePanel outsidePanel = new OutsidePanel(nbOfFloors);
                // On la rend visible
                outsidePanel.setVisible(true);
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // On crée une instance de notre fenêtre qui représente l'ascenseur
                Elevator elevator = new Elevator(nbOfFloors);
                // On la rend visible
                elevator.setVisible(true);
            }
        });
    }
}
