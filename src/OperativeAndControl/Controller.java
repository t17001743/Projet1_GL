package OperativeAndControl;

import OperativeAndControl.Buttons.Button;

import java.util.ArrayList;

public interface Controller {

    /**
     * Méthode appelé par un bouton lorsqu'il est activé
     * Transmet le signal d'activation jusqu'à l'ordinateur
     * */
    void sendPressedButtons(Button button);

    /**
     * Renvoie la liste des boutons internes liés au contrôleur
     * */
    ArrayList<Button> getButtonList();
}
