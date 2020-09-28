package GUI;

import javax.swing.*;
import java.awt.*;

// Cette classe est responsable de la création de la fenêtre graphique pour les panneaux à l'extérieur de l'ascenseur
public class OutsidePanel extends JFrame {

    private int nbOfFloors;

    // Constructeur
    // Demande le nombre d'étages
    public OutsidePanel(int nbOfFloors) {
        super();
        this.nbOfFloors = nbOfFloors;

        build();
    }

    // Cette méthode construit la fenêtre
    private void build() {
        // On donne un titre à la fenêtre
        setTitle("Elevator's outside panels");

        // On récupère la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // On définit la taille de la fenêtre
        // Sa longueur correspond à 25 % de la fenêtre et sa hauteur correspond à environ 25 % de la fenêtre
        setSize(screenSize.width/4, screenSize.height/4);

        // Le x du coin haut-gauche est positionné à environ 60 % de la longueur de la fenêtre
        int x = screenSize.width/2 + screenSize.width/10;
        // Le y du coin haut-gauche est positionné à environ 2.5 % de la longueur de la fenêtre
        int y = screenSize.height/40 + (2 * screenSize.height)/3;
        // On positionne la fenêtre à l'écran
        setLocation(x, y);

        // On interdit le redimensionnement de la fenêtre
        setResizable(false);
        // On indique à l'application de se fermer lorsqu'on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On intègre le contenu de la fenêtre
        setContentPane(buildContentPane());
    }

    /* Panneau bas droit *****************************
     * Ce panneau affiche l'ensemble des commandes   *
     * externes à la cabine, ce pour chaque étage    *
     *************************************************/
    // Cette méthode construit le contenu de la fenêtre et le renvoi sous la forme d'un JPanel
    private JPanel buildContentPane() {
        JPanel panel = new JPanel();

        return panel;
    }
}