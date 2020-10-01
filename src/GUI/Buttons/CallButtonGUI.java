package GUI.Buttons;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CallButtonGUI extends AbstractAction {

    private String direction;
    private Integer floor;
    private GUI userInterface;

    public CallButtonGUI(String direction, Integer floor, GUI userInterface) {
        super(direction);
        this.direction = direction;
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
