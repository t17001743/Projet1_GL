package OperativeAndControl;

import OperativeAndControl.Buttons.Button;

public class Cabin implements Controller {

    private boolean door;
    private int numFloor;
    private double position; // si cabine entre les étages

    public Cabin(boolean door, int numFloor){
        this.door = door;
        this.numFloor = numFloor;
    }

    public Cabin(int numFloor){
        this.numFloor = numFloor;
        this.door = false;
    }

    public void openDoors(){ this.door = true; }

    public void closeDoors(){ this.door = false; }

    public int getNumFloor(){ return numFloor; }

    public double getPosition(){ return position; }

    public void setPosition(double position){ this.position = position; }

    @Override
    public void sendPressedButton(Button[] buttons) {

    }

}
