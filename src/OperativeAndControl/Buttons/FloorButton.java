package OperativeAndControl.Buttons;

import OperativeAndControl.Cabin;

public class FloorButton implements Button {

    private boolean light;
    private int numFloor;
    private Cabin cabin;

    public FloorButton(int numFloor, Cabin cabin){
        this.light = false;
        this.numFloor = numFloor;
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
        this.light = false;
    }

    @Override
    public boolean getLight() {
        return light;
    }

    @Override
    public int getFloor() {
        return numFloor;
    }

    @Override
    public String getDirection() {return null;}
}
