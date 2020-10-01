package OperativeAndControl;

public class Engine {

    CurrentState currentState;
    double speed;

    public Engine(){
        speed = 0.5;
        stop();
    }

    public Engine(double speed){
        this.speed = speed;
        stop();
    }

    public void goUp(){ currentState = CurrentState.UP; }

    public void goDown(){ currentState = CurrentState.DOWN; }

    public void stop(){ currentState = CurrentState.STOP; }

    public CurrentState getCurrentState(){ return currentState; }

    public double getSpeed(){ return speed; }

}
