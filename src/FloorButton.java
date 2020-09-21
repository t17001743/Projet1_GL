import javax.swing.*;
import java.awt.event.ActionEvent;

public class FloorButton extends AbstractAction {

    Window window;
    String floor;

    public FloorButton(String floor, Window window) {
        super(floor);
        this.floor = floor;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.setElevatorIndicator("L'ascenseur attend à l'étage " + floor);
    }
}
