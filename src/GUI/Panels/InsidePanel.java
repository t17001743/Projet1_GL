package GUI.Panels;

import GUI.Buttons.EmergencyButtonGUI;
import GUI.Buttons.FloorButtonGUI;
import GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Cette classe est responsable de la création de la fenêtre graphique pour le panneau à l'intérieur de l'ascenseur
public class InsidePanel extends JFrame {

    private int nbOfFloors;
    private JLabel floorNb;
    private GUI userInterface;
    private ArrayList<JButton> buttonList;
    private JButton emergencyButton;

    /**
     * Le constructeur de l'affichage du panneau interne à l'ascenseur
     * Il lance la construction de l'affichage
     * Il prend en entré le nombre d'étages
     * */
    public InsidePanel(int nbOfFloors, GUI userInterface) {
        super();
        this.nbOfFloors = nbOfFloors;
        this.userInterface = userInterface;
        buttonList = new ArrayList<>();

        build();
    }

    /**
     * Cette méthode créer la fenêtre d'affichage du panneau interne à l'ascenseur
     * */
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

    /**
     * Cette méthode créer l'intérieur de la fenêtre d'affichage qui contient l'ensemble des commandes internes à la cabine
     * */
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
            JButton button = new JButton(new FloorButtonGUI(floor, userInterface));
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            button.setFont(new Font(button.getFont().getName(), Font.BOLD, 25));
            button.setForeground(Color.WHITE);
            button.setBackground(Color.GRAY);
            buttonPanel.add(button);
            buttonList.add(button);
        }

        globalPanel.add(buttonPanel, constraints);

        // Bouton d'arrêt d'urgence
        emergencyButton = new JButton(new EmergencyButtonGUI("Arret d'urgence", nbOfFloors, userInterface));
        emergencyButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        emergencyButton.setFont(new Font(floorNb.getFont().getName(), Font.BOLD, 25));
        emergencyButton.setForeground(Color.WHITE);
        emergencyButton.setBackground(Color.GRAY);
        constraints.gridy = 3;
        globalPanel.add(emergencyButton, constraints);

        return globalPanel;
    }

    /**
     * Cette méthode met à jour le numéro d'étage affiché dans la cabine
     * */
    public void setFloorNb(String floorNb) {
        this.floorNb.setText(floorNb);
    }

    /**
     * Cette méthode active un bouton lié à un étage
     * Elle reçoit un signal du bouton qui a été appuyé
     * */
    public void activateFloorButton(int floor) {
        JButton button = buttonList.get(nbOfFloors - 1 - floor);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
    }

    /**
     * Cette méthode désactive un bouton lié à un étage
     * */
    public void deactivateFloorButton(int floor) {
        JButton button = buttonList.get(nbOfFloors - 1 - floor);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));;
    }

    /**
     * Cette méthode active le bouton lié à l'état d'urgence
     * Elle reçoit un signal du bouton qui a été appuyé
     * */
    public void activateEmergencyButton() {
        emergencyButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
    }

    /**
     * Cette méthode désactive le bouton lié à l'état d'urgence
     * */
    public void deactivateEmergencyButton() {
        emergencyButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
    }
}