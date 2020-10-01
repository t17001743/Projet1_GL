package OperativeAndControl;

import OperativeAndControl.Buttons.Button;

public interface Controller {

    //Button doit être une interface
    void sendPressedButton(Button[] buttons);

}
