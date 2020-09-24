import javax.swing.*;
import java.awt.*;

// Cette classe est responsable de la création de la fenêtre graphique pour les panneaux à l'extérieur de l'ascenseur
public class Elevator extends JFrame {

    private int nbOfFloors;

    // Constructeur
    // Demande le nombre d'étages
    public Elevator(int nbOfFloors) {
        super();
        this.nbOfFloors = nbOfFloors;

        build();
    }

    // Cette méthode construit la fenêtre
    private void build() {
        // On donne un titre à la fenêtre
        setTitle("Elevator Simulator 2020");

        // On récupère la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // On définit la taille de la fenêtre
        // Sa longueur correspond à environ 33 % de la fenêtre et sa hauteur correspond à environ 91 % de la fenêtre
        setSize(screenSize.width/3, (2 * screenSize.height)/3 + screenSize.height/4);

        // Le x du coin haut-gauche est positionné à environ 12.5 % de la longueur de la fenêtre
        int x = (screenSize.width/4)/2;
        // Le y du coin haut-gauche est positionné à environ 2.5 % de la longueur de la fenêtre
        int y = screenSize.height/40;
        // On positionne la fenêtre à l'écran
        setLocation(x, y);

        // On interdit le redimensionnement de la fenêtre
        setResizable(false);
        // On indique à l'application de se fermer lorsqu'on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}