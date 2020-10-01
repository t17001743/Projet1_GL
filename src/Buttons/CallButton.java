package Buttons;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CallButton extends AbstractAction implements Button {

    private String direction;
    private Integer floor;
    private GUI userInterface;
    private boolean light;

    public CallButton(String direction, Integer floor, GUI userInterface) {
        super(direction);
        this.light = false;
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
