package Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CallButton extends AbstractAction {

    String floor;

    public CallButton(String floor) {
        super(floor);
        this.floor = floor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
