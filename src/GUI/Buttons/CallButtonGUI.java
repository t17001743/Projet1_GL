package GUI.Buttons;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CallButtonGUI extends AbstractAction {

    private String direction;
    private Integer floor;
    private GUI userInterface;

    /**
     * Le constructeur d'un bouton externe à la cabine
     * Il prend en entré son étage ainsi que sa direction
     * */
    public CallButtonGUI(String direction, Integer floor, GUI userInterface) {
        super(direction);
        this.direction = direction;
        this.floor = floor;
        this.userInterface = userInterface;
    }

    /**
     * Renvoie son étage correspondant
     * */
    public Integer getFloor(){ return floor; }

    /**
     * Méthode executé lorsque le bouton est enclenché
     * Il envoie un signal au panneau externe de l'interface graphique
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(direction.equals("∧")) {
            userInterface.getComputer().getExternalController().getButtonList().get(2 * floor).activate();
            userInterface.getOutsidePanel().activateFloorButton(2 * floor);
        }
        else {
            userInterface.getComputer().getExternalController().getButtonList().get(2 * floor + 1).activate();
            userInterface.getOutsidePanel().activateFloorButton(2 * floor + 1);
        }

        /*
        userInterface.getInsidePanel().setFloorNb(floor.toString());
        userInterface.getElevator().updateTextInterface("L'ascenseur est à l'étage " + floor.toString());
        userInterface.getElevator().updateGraphicalInterface(floor);
        */
    }
}
