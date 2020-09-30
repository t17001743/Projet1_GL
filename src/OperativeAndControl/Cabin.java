package OperativeAndControl;

public class Cabin implements Controller {

    boolean door;
    int numFloor;

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

    //Il manque la méthode de l'interface

}
