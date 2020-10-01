package OperativeAndControl;

import OperativeAndControl.Buttons.Button;
import OperativeAndControl.Buttons.EmergencyButton;
import OperativeAndControl.Buttons.FloorButton;

import java.util.ArrayList;

public class Cabin implements Controller {

    private boolean door;
    private Computer computer;
    private double position; // si cabine entre les étages
    private ArrayList<Button> buttonList;

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
        buttonList = new ArrayList<>();

        for (int i=0; i<nbFloors; i++){
            buttonList.add(new FloorButton(i, this));
        }

        buttonList.add(new EmergencyButton(this)); // Index = nbFloor
    }

    public void openDoors(){ this.door = true; }

    public void closeDoors(){ this.door = false; }

    public double getPosition(){ return position; }

    public void setPosition(double position){ this.position = position; }

    // Lorsqu'on nous indique qu'un bouton est activé
    @Override
    public void sendPressedButtons(Button button) {
        // On le transmet à l'ordinateur
        computer.receivePressedButton(button);
    }

    @Override
    public ArrayList<Button> getButtonList() {
        return buttonList;
    }

}
