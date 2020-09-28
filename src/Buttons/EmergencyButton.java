package Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmergencyButton extends AbstractAction {

    String floor;

    public EmergencyButton(String floor) {
        super(floor);
        this.floor = floor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}