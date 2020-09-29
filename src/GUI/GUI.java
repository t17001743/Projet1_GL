package GUI;

// Cette classe sert à regrouper les divers éléments constituants le GUI pour une utilisation simplifié par les boutons
public class GUI {

    private InsidePanel insidePanel;
    private OutsidePanel outsidePanel;
    private Elevator elevator;

    public GUI(int nbOfFloors) {
        // On crée une instance de notre fenêtre qui représente le panneau intérieur
        insidePanel = new InsidePanel(nbOfFloors, this);
        // On la rend visible
        insidePanel.setVisible(true);

        // On crée une instance de notre fenêtre qui représente le panneau extérieur
        outsidePanel = new OutsidePanel(nbOfFloors, this);
        // On la rend visible
        outsidePanel.setVisible(true);

        // On crée une instance de notre fenêtre qui représente l'ascenseur
        elevator = new Elevator(nbOfFloors, this);
        // On la rend visible
        elevator.setVisible(true);
    }

    public InsidePanel getInsidePanel() {
        return insidePanel;
    }

    public OutsidePanel getOutsidePanel() {
        return outsidePanel;
    }

    public Elevator getElevator() {
        return elevator;
    }
}
