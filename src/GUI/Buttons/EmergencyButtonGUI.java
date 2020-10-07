package GUI.Buttons;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmergencyButtonGUI extends AbstractAction {

    private String floor;
    private int nbOfFloors;
    private GUI userInterface;

    /**
     * Le constructeur du bouton d'état d'urgence interne à la cabine
     * */
    public EmergencyButtonGUI(String floor, int nbOfFloors, GUI userInterface) {
        super(floor);
        this.floor = floor;
        this.nbOfFloors = nbOfFloors;
        this.userInterface = userInterface;
    }

    /**
     * Méthode executé lorsque le bouton est enclenché
     * Il envoie un signal au panneau interne de l'interface graphique
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        userInterface.getComputer().getCabin().getButtonList().get(nbOfFloors).activate();
        userInterface.getInsidePanel().activateEmergencyButton();
    }
}