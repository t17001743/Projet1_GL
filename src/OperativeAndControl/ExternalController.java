package OperativeAndControl;

import OperativeAndControl.Buttons.Button;
import OperativeAndControl.Buttons.CallButton;

import java.util.ArrayList;

public class ExternalController implements Controller {

    private Computer computer;
    private ArrayList<Button> buttonList;

    /**
     * Le constructeur deu contrôlleur externe
     * Il lance l'initialisation du contrôleur
     * Il prend en entré l'élément ordinateur et le nombre d'étages du bâtiment
     * */
    public ExternalController(Computer computer, int nbFloors){
        this.computer = computer;
        initialization(nbFloors);
    }

    /**
     * L'initialisation du contrôleur externe
     * Créer deux boutons par étage, un pour monter, un pour descendre
     * */
    public void initialization(int nbFloors){
        buttonList = new ArrayList<>();

        for (int i=0; i<nbFloors; i++){
            buttonList.add(new CallButton(i, "UP", this));
            buttonList.add(new CallButton(i, "DOWN", this));
        }
    }

    /**
     * Méthode appelé par un bouton lorsqu'il est activé
     * Transmet le signal d'activation jusqu'à l'ordinateur
     * */
    @Override
    public void sendPressedButtons(Button button) {
        // On le transmet à l'ordinateur
        computer.receivePressedButton(button);
    }

    /**
     * Renvoie la liste des boutons externes à la cabine
     * */
    @Override
    public ArrayList<Button> getButtonList() {
        return buttonList;
    }

}
