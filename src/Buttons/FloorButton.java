package Buttons;

import GUI.GUI;
import GUI.InsidePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FloorButton extends AbstractAction {

    private Integer floor;
    private GUI userInterface;

    public FloorButton(Integer floor, GUI userInterface) {
        super(floor.toString());
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
