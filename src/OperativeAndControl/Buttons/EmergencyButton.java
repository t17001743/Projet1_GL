package OperativeAndControl.Buttons;

import OperativeAndControl.Cabin;

public class EmergencyButton implements Button {

    private boolean light;
    private Cabin cabin;

    /**
     * Le constructeur du bouton d'arrêt d'urgence
     * */
    public EmergencyButton(Cabin cabin){
        light = false;
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
        light = false;
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
    public int getFloor(){ return -1; }

    /**
     * Renvoie la direction associée au bouton
     * */
    @Override
    public String getDirection() {return null;}

}
