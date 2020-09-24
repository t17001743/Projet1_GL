import javax.swing.*;
import java.awt.*;

// Cette classe est responsable de la création de la fenêtre graphique pour le panneau à l'intérieur de l'ascenseur
public class InsidePanel extends JFrame {

    private int nbOfFloors;

    // Constructeur
    // Demande le nombre d'étages
    public InsidePanel(int nbOfFloors) {
        super();
        this.nbOfFloors = nbOfFloors;

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
        // On créer le panneau
        JPanel panel = new JPanel();
        // On définit son fond
        panel.setBackground(Color.WHITE);


        // On créer un JPanel qui prend la forme d'un GridLayout
        panel.setLayout(new GridLayout(10, 0));
        /*
        panel.setLayout(new GridBagLayout());
        // L'objet qui contiendra les contraintes liés au GridLayout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridheight = 10;
        constraints.insets = new Insets(50, 50, 50, 50);*/


        // On créer les boutons liés à chaque étages
        for (Integer floor = nbOfFloors - 1; floor >= 0 ; floor--) {
            JButton button = new JButton(new FloorButton(floor.toString()));
            panel.add(button);
        }

        return panel;
    }
}
