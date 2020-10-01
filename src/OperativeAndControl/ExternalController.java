package OperativeAndControl;

import OperativeAndControl.Buttons.Button;

public class ExternalController implements Controller {

    Button buttonUp, buttonDown;

    public ExternalController(Button buttonUp, Button buttonDown){
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
    }


    @Override
    public void sendPressedButtons(Button ... buttons) {

    }

}
