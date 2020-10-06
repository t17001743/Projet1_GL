package GUI;

import GUI.Panels.Elevator;
import GUI.Panels.InsidePanel;
import GUI.Panels.OutsidePanel;
import OperativeAndControl.Computer;
import OperativeAndControl.CurrentState;

import static java.lang.Thread.sleep;

// Cette classe sert à regrouper les divers éléments constituants le GUI pour une utilisation simplifié par les boutons
public class GUI implements Runnable {

    private InsidePanel insidePanel;
    private OutsidePanel outsidePanel;
    private Elevator elevator;
    private Computer computer;
    private int nbOfFloors;
    private int lastFloorDeactivated;
    private boolean emergencyActivated;

    public GUI(int nbOfFloors, Computer computer) {
        this.computer = computer;
        this.nbOfFloors = nbOfFloors;
        lastFloorDeactivated = -1;
        emergencyActivated = false;

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
        String textInterface = "";

        // On récupère l'état du moteur de l'ascenseur
        CurrentState engineState = computer.getEngine().getCurrentState();
        if(engineState == CurrentState.STOP) {
            textInterface = "L'ascenseur est à l'arrêt";
        }
        else if (engineState == CurrentState.UP) {
            textInterface = "L'ascenseur est en train de monter";
        }
        else {
            textInterface = "L'ascenseur est en train de descendre";
        }

        // On récupère la position de la cabine de l'ascenseur
        Double position = computer.getCabin().getPosition();

        // Si on arrive à un étage, c'est-à-dire que nous ne sommes pas entre deux étages
        if (position % 1 == 0) {
            Integer integerPosition = position.intValue();
            String floor = String.valueOf(integerPosition);
            insidePanel.setFloorNb(floor);
            textInterface += "\nL'ascenseur est à l'étage " + floor;
            elevator.updateGraphicalInterface(integerPosition);
        }
        else {
            textInterface += "\nL'ascenseur est entre deux étages";
        }

        // On regarde si les portes sont ouvertes ou fermées
        boolean doors = computer.getCabin().getDoors();

        // Si elles sont ouvertes
        if(doors) {
            textInterface += "\nLes portes sont ouvertes";
        }
        else {
            textInterface += "\nLes portes sont fermées";
        }

        // On regarde si un statut d'urgence est activé
        boolean emergency = computer.getEmergency();
        if(emergency) {
            textInterface += "\nETAT URGENCE ACTIF";
            emergencyActivated = true;
        }
        else {
            // Si l'état d'urgence avait été activé
            if(emergencyActivated) {
                // On désactive l'état d'urgence
                emergencyActivated = false;
                insidePanel.deactivateEmergencyButton();
                // On désactive tous les boutons
                for(int floor = 0; floor < nbOfFloors; floor++) {
                    insidePanel.deactivateFloorButton(floor);
                    outsidePanel.deactivateFloorButton(floor);
                }
            }
        }

        // On met à jour l'interface textuel
        elevator.updateTextInterface(textInterface);

        // Si le dernier étage supprimé de la liste de priorité est différent du dernier étage désactivé
        int lastFloorRemoved = computer.getLastFloorRemoved();
        if(lastFloorRemoved != lastFloorDeactivated) {
            // On éteint le bouton
            insidePanel.deactivateFloorButton(lastFloorRemoved);
            outsidePanel.deactivateFloorButton(lastFloorRemoved);
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
