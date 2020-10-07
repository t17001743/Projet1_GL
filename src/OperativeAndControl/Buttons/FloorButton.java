package OperativeAndControl.Buttons;

import OperativeAndControl.Cabin;

public class FloorButton implements Button {

    private boolean light;
    private int numFloor;
    private Cabin cabin;

    /**
     * Le constructeur du bouton d'étage à l'intérieur de la cabine
     * Ce bouton possède son numéro d'étage associé
     * */
    public FloorButton(int numFloor, Cabin cabin){
        this.light = false;
        this.numFloor = numFloor;
        this.cabin = cabin;
    }

    /**
     * Active le bouton
     * Envoie un signal à la cabine
     * */
    @Override
    public void activate() {
        // On l'allume
        this.light = true;

        // On indique à la cabine qu'il a été activé
        cabin.sendPressedButtons(this);
    }

    /**
     * Désactive le bouton
     * */
    @Override
    public void deactivate() {
        this.light = false;
    }

    /**
     * Renvoie l'état de la lumière du bouton
     * */
    @Override
    public boolean getLight() {
        return light;
    }

    /**
     * Renvoie l'étage associé au bouton
     * */
    @Override
    public int getFloor() {
        return numFloor;
    }

    /**
     * Renvoie la direction associée au bouton
     * */
    @Override
    public String getDirection() {return null;}
}
