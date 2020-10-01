package OperativeAndControl;

import OperativeAndControl.Buttons.Button;
import OperativeAndControl.Buttons.CallButton;
import OperativeAndControl.Buttons.FloorButton;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class Computer {

    Cabin cabin;
    Engine engine;
    boolean emergency;
    ArrayList priorityList;

    /**
     * Créer la cabine, le moteur et les boutons*/
    public Computer(int nbFloors, int numFloor){
        cabin = new Cabin(this, nbFloors, numFloor);
        engine = new Engine();

        priorityList = new ArrayList(); //étage(s) à desservir
        emergency = false; //aucune urgence
    }

    public Computer(int nbFloors){
        cabin = new Cabin(this, nbFloors);
        engine = new Engine();

        priorityList = new ArrayList(); //étage(s) à desservir
        emergency = false; //aucune urgence
    }

    public void run(){

        setPositionCabin();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        run();
    }

    public void receivePressedButtons(Button ... buttons){

        for (int i=0; i<buttons.length; i++)
            treatmentOfButton(buttons[i]);
    }

    public void treatmentOfButton(Button button){

        if (button.getClass() == new FloorButton(0).getClass())
            wishingFloor();
        else if (button.getClass() == new CallButton(0).getClass())
            callCabin();
        else emergencyStop();
    }

    public void wishingFloor(){

    }

    public void callCabin(){

    }

    public void emergencyStop(){
        emergency = true;
        engine.stop();
        priorityList = new ArrayList<>(); //requête précédentes effacées
    }

    public void setPositionCabin(){
        double position = cabin.getPosition();
        double speed = engine.getSpeed();

        switch (engine.getCurrentState()){

            case UP:
                cabin.setPosition(position + speed);
                break;

            case DOWN:
                cabin.setPosition(position - speed);
                break;

            case STOP:
                cabin.setPosition(position);
                break;
        }
    }



    public double getPositionCabin(){ return cabin.getPosition(); }

}
