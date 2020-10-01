package GUI.Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmergencyButtonGUI extends AbstractAction {

    String floor;

    public EmergencyButtonGUI(String floor) {
        super(floor);
        this.floor = floor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}