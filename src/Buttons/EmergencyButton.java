package Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmergencyButton extends AbstractAction implements Button {

    String floor;
    private boolean light;

    public EmergencyButton(String floor) {
        super(floor);
        this.floor = floor;
        this.light = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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