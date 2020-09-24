import javax.swing.*;
import java.awt.event.ActionEvent;

public class FloorButton extends AbstractAction {

    String floor;

    public FloorButton(String floor) {
        super(floor);
        this.floor = floor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
