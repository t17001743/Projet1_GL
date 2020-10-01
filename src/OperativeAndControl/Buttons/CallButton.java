package OperativeAndControl.Buttons;

import OperativeAndControl.Cabin;
import OperativeAndControl.ExternalController;

public class CallButton implements Button {

    private boolean light;
    private int numFloor;
    private String direction;
    private ExternalController externalController;

    public CallButton(int numFloor, String direction, ExternalController externalController){
        light = false;
        this.numFloor = numFloor;
        this.direction = direction;
        this.externalController = externalController;
    }

    // Si un bouton est activé
    @Override
    public void activate() {
        // On l'allume
        this.light = true;

        // On indique à la cabine qu'il a été activé
        externalController.sendPressedButtons(this);
    }

    @Override
    public void deactivate() {
        light = false;
    }

    @Override
    public boolean getLight() {
        return this.light;
    }

    @Override
    public int getFloor(){ return numFloor; }

    public String getDirection() { return direction; }
}
