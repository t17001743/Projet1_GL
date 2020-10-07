package OperativeAndControl.Buttons;

import OperativeAndControl.Cabin;
import OperativeAndControl.ExternalController;

public class CallButton implements Button {

    private boolean light;
    private int numFloor;
    private String direction;
    private ExternalController externalController;

    /**
     * Le constructeur du bouton d'appel de l'ascenseur
     * Ce bouton possède son numéro d'étage associé ainsi que la direction vers laquelle il a été appelé
     * */
    public CallButton(int numFloor, String direction, ExternalController externalController){
        light = false;
        this.numFloor = numFloor;
        this.direction = direction;
        this.externalController = externalController;
    }

    /**
     * Active le bouton
     * Envoie un signal au contrôleur
     * */
    @Override
    public void activate() {
        // On l'allume
        this.light = true;

        // On indique à la cabine qu'il a été activé
        externalController.sendPressedButtons(this);
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
        return this.light;
    }

    /**
     * Renvoie l'étage associé au bouton
     * */
    @Override
    public int getFloor(){ return numFloor; }

    /**
     * Renvoie la direction associée au bouton
     * */
    public String getDirection() { return direction; }
}
