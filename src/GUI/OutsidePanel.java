package GUI;

import Buttons.CallButton;
import Buttons.FloorButton;

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
        // On créer le panneau global
        JPanel globalPanel = new JPanel();
        // On définit sa couleur de fond
        globalPanel.setBackground(Color.WHITE);
        // On créer un JPanel qui prend la forme d'un GridBagLayout
        globalPanel.setLayout(new GridLayout(0, 5));

        // Pour chaque étage
        for(Integer floor = 0; floor < nbOfFloors; floor ++) {
            // On créer un panneau pour les boutons extérieurs
            JPanel floorPanel = new JPanel();
            floorPanel.setLayout(new GridLayout(3, 1));
            floorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // Numéro d'étage
            JLabel label = new JLabel(floor.toString(), SwingConstants.CENTER);
            floorPanel.add(label);

            // Bouton pour monter
            JButton upButton = new JButton(new CallButton("∧"));
            upButton.setFont(new Font(upButton.getFont().getName(), Font.BOLD, upButton.getFont().getSize()));
            upButton.setForeground(Color.WHITE);
            upButton.setBackground(Color.GRAY);
            floorPanel.add(upButton);

            // Bouton pour descendre
            JButton downButton = new JButton(new CallButton(" ∨"));
            downButton.setFont(new Font(downButton.getFont().getName(), Font.BOLD, downButton.getFont().getSize()));
            downButton.setForeground(Color.WHITE);
            downButton.setBackground(Color.GRAY);
            floorPanel.add(downButton);

            globalPanel.add(floorPanel);
        }

        return globalPanel;
    }
}