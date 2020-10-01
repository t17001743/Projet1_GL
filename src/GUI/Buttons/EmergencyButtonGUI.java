package GUI.Buttons;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmergencyButtonGUI extends AbstractAction {

    private String floor;
    private int nbOfFloors;
    private GUI userInterface;

    public EmergencyButtonGUI(String floor, int nbOfFloors, GUI userInterface) {
        super(floor);
        this.floor = floor;
        this.nbOfFloors = nbOfFloors;
        this.userInterface = userInterface;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        userInterface.getComputer().getCabin().getButtonList().get(nbOfFloors).activate();
    }
}