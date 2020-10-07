package GUI.Buttons;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FloorButtonGUI extends AbstractAction {

    private Integer floor;
    private GUI userInterface;

    /**
     * Le constructeur d'un bouton interne à la cabine
     * Il prend en entré son étage correspondant
     * */
    public FloorButtonGUI(Integer floor, GUI userInterface) {
        super(floor.toString());
        this.floor = floor;
        this.userInterface = userInterface;
    }


    /**
     * Renvoie son étage correspondant
     * */
    public Integer getFloor(){ return floor; }


    /**
     * Méthode executé lorsque le bouton est enclenché
     * Il envoie un signal au panneau interne de l'interface graphique
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        userInterface.getComputer().getCabin().getButtonList().get(floor).activate();
        userInterface.getInsidePanel().activateFloorButton(floor);

        /*
        userInterface.getInsidePanel().setFloorNb(floor.toString());
        userInterface.getElevator().updateTextInterface("L'ascenseur est à l'étage " + floor.toString());
        userInterface.getElevator().updateGraphicalInterface(floor);
        */
    }
}
