package Buttons;

import GUI.GUI;
import GUI.InsidePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FloorButton extends AbstractAction implements Button {

    private Integer floor;
    private GUI userInterface;
    private boolean light;

    public FloorButton(Integer floor, GUI userInterface) {
        super(floor.toString());
        this.floor = floor;
        this.light = false;
        this.userInterface = userInterface;
    }

    public Integer getFloor(){ return floor; }

    @Override
    public void actionPerformed(ActionEvent e) {
        userInterface.getInsidePanel().setFloorNb(floor.toString());
        userInterface.getElevator().updateTextInterface("L'ascenseur est à l'étage " + floor.toString());
        userInterface.getElevator().updateGraphicalInterface(floor);
    }

    @Override
    public void activate() {
        light = true;
    }

    @Override
    public void deactivate() {
        light = false;
    }

    @Override
    public boolean getLight() {
        return light;
    }
}
