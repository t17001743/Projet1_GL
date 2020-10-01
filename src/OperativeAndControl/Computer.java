package OperativeAndControl;
import Buttons.*;
import GUI.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Computer {

    Cabin cabin;
    Engine engine;
    boolean emergency;
    ArrayList<Button> activatedButtons;

    //Map<idButton, idFloor> buttons;
    Map<Button, Integer> buttons;

    /**
     * Créer la cabine, le moteur et les boutons*/
    public Computer(int numFloor){
        cabin = new Cabin(numFloor);
        engine = new Engine();

        activatedButtons = new ArrayList<>(); //aucuns boutons cliqués
        emergency = false; //aucune urgence

        makeMapButtons(numFloor);
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
                cabin.setPosition(0);
                break;
        }
    }

    public void emergencyStop(){
        emergency = true;
        activatedButtons = new ArrayList<>();
    }

    /**Instantie la map des boutons
     * */
    public void makeMapButtons(int numFloor){
        buttons = new HashMap<>();

        //Les boutons du panneau interne
        for (int i=0; i<numFloor; i++){
            //Ce serait bien sans le GUI x)
            FloorButton button = new FloorButton(i, new GUI(numFloor));

            buttons.put(button, button.getFloor());
        }

        //Les boutons des panneaux externes
        for (int i=0; i<numFloor; i++){

            //Ce serait bien sans le GUI x)
            CallButton callButtonUP = new CallButton("UP", i, new GUI(numFloor));
            CallButton callButtonDOWN = new CallButton("DOWN", i, new GUI(numFloor));

            buttons.put(callButtonUP, callButtonUP.getFloor());
            buttons.put(callButtonDOWN, callButtonDOWN.getFloor());
        }

        buttons.put(new EmergencyButton("idk"), -1); //arrêt d'urgence n'est lié à aucun lvl
    }

}
