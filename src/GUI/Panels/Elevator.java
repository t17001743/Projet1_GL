package GUI.Panels;

import GUI.GUI;

import javax.swing.*;
import java.awt.*;

// Cette classe est responsable de la création de la fenêtre graphique pour les panneaux à l'extérieur de l'ascenseur
public class Elevator extends JFrame {

    private int nbOfFloors;
    private JTextArea textInterface;
    private JPanel graphicalPanel;
    private JLabel graphicalElevator;
    private GUI userInterface;

    // Constructeur
    // Demande le nombre d'étages
    public Elevator(int nbOfFloors, GUI userInterface) {
        super();
        this.nbOfFloors = nbOfFloors;
        this.userInterface = userInterface;

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

        // On intègre le contenu de la fenêtre
        setContentPane(buildContentPane());
    }


    /* Panneau gauche ********************************
     * Ce panneau affiche l'état de l'ascenseur de   *
     * manière graphique et textuelle                *
     *************************************************/
    // Cette méthode construit le contenu de la fenêtre et le renvoi sous la forme d'un JPanel
    private JPanel buildContentPane() {
        // On créer le panneau global
        JPanel globalPanel = new JPanel();
        // On créer un JPanel qui prend la forme d'un GridBagLayout
        globalPanel.setLayout(new GridBagLayout());
        globalPanel.setBackground(Color.WHITE);
        // On créer l'objet qui contiendra les contraintes liés au GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();

        // Tous les boutons sont de même taille
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        // On créer des lignes pour l'affichage graphique
        graphicalPanel = new JPanel();
        graphicalPanel.setLayout(new GridBagLayout());
        graphicalPanel.setBackground(Color.WHITE);
        GridBagConstraints graphicalConstraints = new GridBagConstraints();

        graphicalConstraints.fill = GridBagConstraints.BOTH;
        graphicalConstraints.gridwidth = 1;
        graphicalConstraints.gridx = 0;
        graphicalConstraints.gridy = 0;
        graphicalConstraints.weightx = 0.5;
        graphicalConstraints.weighty = 0.5;

        // Affichage de tous les étages
        for(Integer floor = nbOfFloors - 1; floor >=0; floor--) {
            JLabel label = new JLabel();
            label.setFont(new Font(label.getFont().getName(), Font.BOLD, 25));
            if(floor < 10) {
                label.setText(floor.toString() + "  ---------------------------------------");
            }
            else {
                label.setText(floor.toString() + "  -------------------------------------");
            }
            graphicalPanel.add(label, graphicalConstraints);
            graphicalConstraints.gridy++;
        }

        // On dessine l'ascenseur à l'étage 0
        graphicalConstraints.gridy--;
        graphicalConstraints.gridx = 1;
        graphicalElevator = new JLabel();
        graphicalElevator.setFont(new Font(graphicalElevator.getFont().getName(), Font.BOLD, 25));
        graphicalElevator.setText("⬛");
        graphicalPanel.add(graphicalElevator, graphicalConstraints);

        globalPanel.add(graphicalPanel, constraints);

        // On créer le panneau pour l'affichage textuel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.setBackground(Color.GRAY);
        labelPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        textInterface = new JTextArea("L'ascenseur est à l'étage 0", 2, 22);
        textInterface.setFont(new Font(textInterface.getFont().getName(), Font.BOLD, 30));
        textInterface.setForeground(Color.WHITE);
        textInterface.setLineWrap(true);
        textInterface.setWrapStyleWord(true);
        textInterface.setOpaque(false);
        textInterface.setEditable(false);
        labelPanel.add(textInterface);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        globalPanel.add(labelPanel, constraints);

        return globalPanel;
    }

    public void updateTextInterface(String string) {
        textInterface.setText(string);
    }

    public void updateGraphicalInterface(Integer floor) {
        graphicalPanel.remove(graphicalElevator);

        GridBagConstraints graphicalConstraints = new GridBagConstraints();
        graphicalConstraints.fill = GridBagConstraints.BOTH;
        graphicalConstraints.gridwidth = 1;
        graphicalConstraints.gridx = 1;
        graphicalConstraints.gridy = nbOfFloors - floor - 1;
        graphicalConstraints.weightx = 0.5;
        graphicalConstraints.weighty = 0.5;

        graphicalPanel.add(graphicalElevator, graphicalConstraints);
    }
}