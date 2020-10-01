package OperativeAndControl.Buttons;

import OperativeAndControl.Cabin;

public class EmergencyButton implements Button {

    private boolean light;
    private Cabin cabin;

    public EmergencyButton(Cabin cabin){
        light = false;
        this.cabin = cabin;
    }

    // Si un bouton est activé
    @Override
    public void activate() {
        // On l'allume
        this.light = true;

        // On indique à la cabine qu'il a été activé
        cabin.sendPressedButtons(this);
    }

    @Override
    public void deactivate() {
        light = false;
    }

    @Override
    public boolean getLight() {
        return light;
    }

    @Override
    public int getFloor(){ return -1; }

    @Override
    public String getDirection() {return null;}

}
