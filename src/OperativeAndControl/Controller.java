package OperativeAndControl;

import OperativeAndControl.Buttons.Button;

import java.util.ArrayList;

public interface Controller {

    // Button doit être une interface
    void sendPressedButtons(Button button);

    // Renvoie la liste de ses boutons
    ArrayList<Button> getButtonList();
}
