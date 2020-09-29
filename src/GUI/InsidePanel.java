package GUI;

import Buttons.EmergencyButton;
import Buttons.FloorButton;
import javax.swing.*;
import java.awt.*;

// Cette classe est responsable de la création de la fenêtre graphique pour le panneau à l'intérieur de l'ascenseur
public class InsidePanel extends JFrame {

    private int nbOfFloors;
    private JLabel floorNb;
    private GUI userInterface;

    // Constructeur
    // Demande le nombre d'étages
    public InsidePanel(int nbOfFloors, GUI userInterface) {
        super();
        this.nbOfFloors = nbOfFloors;
        this.userInterface = userInterface;

        build();
    }

    // Cette méthode construit la fenêtre
    private void build() {
        // On donne un titre à la fenêtre
        setTitle("Elevator's inside panel");

        // On récupère la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // On définit la taille de la fenêtre
        // Sa longueur correspond à 25 % de la fenêtre et sa hauteur correspond à environ 66 % de la fenêtre
        setSize(screenSize.width/4, (2 * screenSize.height)/3);

        // Le x du coin haut-gauche est positionné à environ 60 % de la longueur de la fenêtre
        int x = screenSize.width/2 + screenSize.width/10;
        // Le y du coin haut-gauche est positionné à environ 2.5 % de la longueur de la fenêtre
        int y = screenSize.height/40;
        // On positionne la fenêtre à l'écran
        setLocation(x, y);

        // On interdit le redimensionnement de la fenêtre
        setResizable(false);
        // On indique à l'application de se fermer lorsqu'on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On intègre le contenu de la fenêtre
        setContentPane(buildContentPane());
    }

    /* Panneau haut droit ****************************
     * Ce panneau affiche l'ensemble des commandes   *
     * internes à la cabine                          *
     *************************************************/
    // Cette méthode construit le contenu de la fenêtre et le renvoi sous la forme d'un JPanel
    private JPanel buildContentPane() {
        // On créer le panneau global
        JPanel globalPanel = new JPanel();
        // On définit sa couleur de fond
        globalPanel.setBackground(Color.LIGHT_GRAY);
        // On créer un JPanel qui prend la forme d'un GridBagLayout
        globalPanel.setLayout(new GridBagLayout());
        // On créer l'objet qui contiendra les contraintes liés au GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();

        // Tous les boutons sont de même taille
        constraints.fill = GridBagConstraints.BOTH;

        // On créer le panneau pour l'affichage d'étage
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        floorNb = new JLabel();
        floorNb.setFont(new Font(floorNb.getFont().getName(), Font.BOLD, 50));
        floorNb.setForeground(Color.RED);
        floorNb.setText("0");
        labelPanel.add(floorNb);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        globalPanel.add(labelPanel, constraints);

        // On créer le panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1, 50, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Espace autour des boutons
        constraints.insets = new Insets(10, 10, 10, 10);
        // Pour que les boutons s'étendent dans l'espace
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.gridy = 1;

        // On créer les boutons liés à chaque étages
        for (Integer floor = nbOfFloors - 1; floor >= 0 ; floor--) {

            // On place le bouton
            JButton button = new JButton(new FloorButton(floor, userInterface));
            button.setFont(new Font(button.getFont().getName(), Font.BOLD, 25));
            button.setForeground(Color.WHITE);
            button.setBackground(Color.GRAY);
            buttonPanel.add(button);
        }

        globalPanel.add(buttonPanel, constraints);

        // Bouton d'arrêt d'urgence
        JButton button = new JButton(new EmergencyButton("Arret d'urgence"));
        button.setFont(new Font(floorNb.getFont().getName(), Font.BOLD, 25));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GRAY);
        constraints.gridy = 3;
        globalPanel.add(button, constraints);

        return globalPanel;
    }

    public void setFloorNb(String floorNb) {
        this.floorNb.setText(floorNb);
    }
}