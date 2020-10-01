package GUI.Buttons;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FloorButtonGUI extends AbstractAction {

    private Integer floor;
    private GUI userInterface;

    public FloorButtonGUI(Integer floor, GUI userInterface) {
        super(floor.toString());
        this.floor = floor;
        this.userInterface = userInterface;
    }

    public Integer getFloor(){ return floor; }

    @Override
    public void actionPerformed(ActionEvent e) {
        userInterface.getInsidePanel().setFloorNb(floor.toString());
        userInterface.getElevator().updateTextInterface("L'ascenseur est à l'étage " + floor.toString());
        userInterface.getElevator().updateGraphicalInterface(floor);
    }
}
