package OperativeAndControl;

import OperativeAndControl.Buttons.Button;
import OperativeAndControl.Buttons.EmergencyButton;
import OperativeAndControl.Buttons.FloorButton;

import java.util.ArrayList;

public class Cabin implements Controller {

    private boolean door;
    private Computer computer;
    private double position; // si cabine entre les étages
    private ArrayList<Button> buttonsIntern;

    public Cabin(Computer computer, int nbFloors, int numFloor){
        this.computer = computer;
        this.door = false;
        this.position = numFloor;
        initialization(nbFloors);
    }

    public Cabin(Computer computer, int nbFloors){
        this.computer = computer;
        this.door = false;
        this.position = 0;
        initialization(nbFloors);
    }

    public void initialization(int nbFloors){
        buttonsIntern = new ArrayList<>();

        for (int i=0; i<nbFloors; i++){
            FloorButton button = new FloorButton(i);
            buttonsIntern.add(button);
        }
        EmergencyButton button = new EmergencyButton();
        buttonsIntern.add(button); //nbFloors + 1
    }

    public void openDoors(){ this.door = true; }

    public void closeDoors(){ this.door = false; }

    public double getPosition(){ return position; }

    public void setPosition(double position){ this.position = position; }

    @Override
    public void sendPressedButtons(Button ... buttons) {
        computer.receivePressedButtons(buttons);
    }

}
