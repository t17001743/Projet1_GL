package GUI;

import OperativeAndControl.Computer;

import static java.lang.Thread.sleep;

// Cette classe sert à regrouper les divers éléments constituants le GUI pour une utilisation simplifié par les boutons
public class GUI implements Runnable {

    private InsidePanel insidePanel;
    private OutsidePanel outsidePanel;
    private Elevator elevator;
    private Computer computer;

    public GUI(int nbOfFloors, Computer computer) {
        this.computer = computer;

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

    // Le cycle d'exécution de l'interface graphique qui consiste à mettre à jour l'interface graphique
    public void run() {
        // On récupère la position de la cabine de l'ascenseur
        Double position = computer.getCabin().getPosition();

        // Si on arrive à un étage, c'est-à-dire que nous ne sommes pas entre deux étages
        if (position % 1 == 0) {
            Integer integerPosition = position.intValue();
            String floor = String.valueOf(integerPosition);
            insidePanel.setFloorNb(floor);
            elevator.updateTextInterface("L'ascenseur est à l'étage " + floor);
            elevator.updateGraphicalInterface(integerPosition);
        }

        // On attend une seconde
        try {
            sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // On recommence le cycle
        run();
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

    public Computer getComputer() { return computer; }
}
