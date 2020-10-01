package OperativeAndControl;

import Buttons.Button;

public class Cabin implements Controller {

    private boolean door;
    private int numFloor;

    public Cabin(boolean door, int numFloor){
        this.door = door;
        this.numFloor = numFloor;
    }

    public Cabin(boolean door){
        this.door = door;
        numFloor = 0;
    }

    public Cabin(int numFloor){
        this.numFloor = numFloor;
        this.door = false;
    }

    public void openDoors(){ this.door = true; }

    public void closeDoors(){ this.door = false; }

    public int getNumFloor(){ return numFloor; }

    @Override
    public void sendPressedButton(Button[] buttons) {

    }

}
