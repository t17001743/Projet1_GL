package OperativeAndControl;

public class Engine {

    CurrentState currentState;

    public Engine(){
        stop();
    }

    public void goUp(){ currentState = CurrentState.UP; }

    public void goDown(){ currentState = CurrentState.DOWN; }

    public void stop(){ currentState = CurrentState.STOP; }

}
