package OperativeAndControl;

import OperativeAndControl.Buttons.Button;
import OperativeAndControl.Buttons.CallButton;

import java.util.ArrayList;

public class ExternalController implements Controller {

    private Computer computer;
    private ArrayList<Button> buttonList;

    public ExternalController(Computer computer, int nbFloors){
        this.computer = computer;
        initialization(nbFloors);
    }

    public void initialization(int nbFloors){
        buttonList = new ArrayList<>();

        for (int i=0; i<nbFloors; i++){
            buttonList.add(new CallButton(i, "UP", this));
            buttonList.add(new CallButton(i, "DOWN", this));
        }
    }

    // Lorsqu'on nous indique qu'un bouton est activé
    @Override
    public void sendPressedButtons(Button button) {
        // On le transmet à l'ordinateur
        computer.receivePressedButton(button);
    }

    @Override
    public ArrayList<Button> getButtonList() {
        return buttonList;
    }

}
