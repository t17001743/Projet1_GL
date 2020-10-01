package OperativeAndControl;

import Buttons.Button;

public class ExternalController implements Controller {

    Cabin cabin;
    Button buttonUp, buttonDown;

    public ExternalController(Cabin cabin, Button buttonUp, Button buttonDown){
        this.cabin = cabin;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
    }


    @Override
    public void sendPressedButton(Button[] buttons) {

    }

}
