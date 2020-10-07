package OperativeAndControl;

public class Engine {

    CurrentState currentState;
    double speed;

    /**
     * Le constructeur du moteur
     * Sa vitesse par défaut est de 0.5 (1 étant égal à un étage par mouvement)
     * */
    public Engine(){
        speed = 0.5;
        stop();
    }

    /**
     * Le constructeur du moteur
     * Il prend en entré sa vitesse 1 étant égal à un étage par mouvement)
     * */
    public Engine(double speed){
        this.speed = speed;
        stop();
    }

    /**
     * Indique au moteur de monter
     * */
    public void goUp(){ currentState = CurrentState.UP; }

    /**
     * Indique au moteur de descendre
     * */
    public void goDown(){ currentState = CurrentState.DOWN; }

    /**
     * Indique au moteur de s'arrêter
     * */
    public void stop(){ currentState = CurrentState.STOP; }

    /**
     * Renvoie l'état actuel du moteur
     * */
    public CurrentState getCurrentState(){ return currentState; }

    /**
     * Renvoie la vitesse du moteur
     * */
    public double getSpeed(){ return speed; }

}
