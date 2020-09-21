import javax.swing.*;
import java.awt.*;

// Cette classe est responsable de la création de la fenêtre graphique
public class Window extends JFrame {

    private int nbOfFloors;
    private JLabel elevatorIndicator;

    // Constructeur
    // Demande le nombre d'étages
    public Window(int nbOfFloors) {
        super();
        this.nbOfFloors = nbOfFloors;

        build();
    }

    // Cette méthode construit la fenêtre
    private void build() {
        // On donne un titre à la fenêtre
        setTitle("Elevator Simulator 2020");
        // On définit la taille de la fenêtre
        setSize(1500, 1000);
        // On centre la fenêtre sur l'écran
        setLocationRelativeTo(null);
        // On interdit le redimensionnement de la fenêtre
        setResizable(false);
        // On indique à l'application de se fermer lorsqu'on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On intègre le contenu de la fenêtre
        setContentPane(buildContentPane());
    }

    // Cette méthode construit le contenu de la fenêtre et le renvoi sous la forme d'un JPanel
    private JPanel buildContentPane() {
        /* Panneau Global *******************************
        * Ce panneau contient tous les autres panneau   *
        * sous la forme d'un GridBagLayout              *
        *************************************************/

        // On créer un JPanel qui prend la forme d'un GridBagLayout
        JPanel globalPanel = new JPanel();
        globalPanel.setBackground(Color.WHITE);
        globalPanel.setLayout(new GridBagLayout());
        // L'objet qui contiendra les contraintes liés au GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();


        /* Panneau haut gauche **************************/

        // On précise les contraintes qui définissent sa position dans le panneau global
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;

        // On ajoute le sous-panneau au panneau global en respectant les contraintes
        globalPanel.add(buildContentPaneTopLeft(), constraints);


        /* Panneau bas gauche ***************************/

        // On précise les contraintes qui définissent sa position dans le panneau global
        constraints.gridy = 1;

        // On ajoute le sous-panneau au panneau global en respectant les contraintes
        globalPanel.add(buildContentPaneBottomLeft(), constraints);


        /* Panneau haut droit ***************************/

        // On précise les contraintes qui définissent sa position dans le panneau global
        constraints.gridx = 1;
        constraints.gridy = 0;

        // On ajoute le sous-panneau au panneau global en respectant les contraintes
        globalPanel.add(buildContentPaneTopRight(), constraints);


        /* Panneau bas droit ****************************/

        // On précise les contraintes qui définissent sa position dans le panneau global
        constraints.gridy = 1;

        // On ajoute le sous-panneau au panneau global en respectant les contraintes
        globalPanel.add(buildContentPaneBottomRight(), constraints);

        return globalPanel;
    }

    /* Panneau haut gauche ***************************
     * Ce panneau montre l'ascenseur ainsi que sa    *
     * position vis-à-vis des autres étages          *
     *************************************************/
    private JPanel buildContentPaneTopLeft() {
        // On créer un JPanel qui prend la forme d'un
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        return panel;
    }

    /* Panneau bas gauche ****************************
     * Ce panneau affiche du texte décrivant les     *
     * actions réalisés par l'ascenseur              *
     *************************************************/
    private JPanel buildContentPaneBottomLeft() {
        // On créer un JPanel qui prend la forme d'un
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        elevatorIndicator = new JLabel("L'ascenseur attend à l'étage 0");
        panel.add(elevatorIndicator);

        return panel;
    }

    /* Panneau haut droit ****************************
     * Ce panneau affiche l'ensemble des commandes   *
     * internes à la cabine                          *
     *************************************************/
    private JPanel buildContentPaneTopRight() {
        // On créer un JPanel qui prend la forme d'un GridLayout
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // On créer les boutons liés à chaque étages
        for (Integer floor = 0; floor < nbOfFloors; floor++) {
            JButton button = new JButton(new FloorButton(floor.toString(), this));
            panel.add(button);
        }

        return panel;
    }

    /* Panneau bas droit *****************************
     * Ce panneau affiche l'ensemble des commandes   *
     * externes à la cabine, ce pour chaque étage    *
     *************************************************/
    private JPanel buildContentPaneBottomRight() {
        // On créer un JPanel qui prend la forme d'un
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        return panel;
    }

    public void setElevatorIndicator(String string) {
        elevatorIndicator.setText(string);
    }
}
