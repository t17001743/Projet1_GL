package Buttons;

import GUI.GUI;

import javax.jnlp.IntegrationService;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class CallButton extends AbstractAction {

    private String direction;
    private Integer floor;
    private GUI userInterface;

    public CallButton(String direction, Integer floor, GUI userInterface) {
        super(direction);
        this.direction = direction;
        this.floor = floor;
        this.userInterface = userInterface;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        userInterface.getInsidePanel().setFloorNb(floor.toString());
        userInterface.getElevator().updateTextInterface("L'ascenseur est à l'étage " + floor.toString());
        userInterface.getElevator().updateGraphicalInterface(floor);
    }
}
