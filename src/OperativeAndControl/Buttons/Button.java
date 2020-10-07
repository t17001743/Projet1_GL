package OperativeAndControl.Buttons;

public interface Button {

    /**
     * Active le bouton
     * */
    void activate();
    /**
     * Désactive le bouton
     * */
    void deactivate();
    /**
     * Renvoie l'état de la lumière du bouton
     * */
    boolean getLight();
    /**
     * Renvoie l'étage associé au bouton
     * */
    int getFloor();
    /**
     * Renvoie la direction associée au bouton
     * */
    String getDirection();
}
