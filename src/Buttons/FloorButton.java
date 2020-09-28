package Buttons;

import GUI.InsidePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FloorButton extends AbstractAction {

    String floor;
    InsidePanel insidePanel;

    public FloorButton(String floor, InsidePanel insidePanel) {
        super(floor);
        this.floor = floor;
        this.insidePanel = insidePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        insidePanel.setFloorNb(floor);
    }
}
