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

    /**
     * Le constructeur de l'ordinateur
     * Il lance l'initialisation de la cabine
     * Il prend en entré l'élément ordinateur, le nombre d'étages du bâtiment et l'étage d'origine de l'ascenseur
     * */
    public Cabin(Computer computer, int nbFloors, int numFloor){
        this.computer = computer;
        this.door = false;
        this.position = numFloor;
        initialization(nbFloors);
    }

    /**
     * Le constructeur de l'ordinateur
     * Il lance l'initialisation de la cabine
     * Il prend en entré l'élément ordinateur et le nombre d'étages du bâtiment
     * */
    public Cabin(Computer computer, int nbFloors){
        this.computer = computer;
        this.door = false;
        this.position = 0;
        initialization(nbFloors);
    }

    /**
     * L'initialisation de la cabine
     * Créer un bouton par étage et un bouton pour l'arrêt d'urgence
     * Tous les boutons sont conservés dans une liste afin de povuoir y accéder
     * */
    public void initialization(int nbFloors){
        buttonList = new ArrayList<>();

        for (int i=0; i<nbFloors; i++){
            buttonList.add(new FloorButton(i, this));
        }

        buttonList.add(new EmergencyButton(this)); // Index = nbFloor
    }

    /**
     * Ouvre les portes de la cabine
     * */
    public void openDoors(){ this.door = true; }

    /**
     * Ferme les portes de la cabine
     * */
    public void closeDoors(){ this.door = false; }

    /**
     * Renvoie l'état des portes (ouvertes ou fermés)
     * */
    public boolean getDoors() { return door; }

    /**
     * Renvoie la position de la cabine
     * */
    public double getPosition(){ return position; }

    /**
     * Ajuste la position de la cabine
     * */
    public void setPosition(double position){ this.position = position; }

    /**
     * Méthode appelé par un bouton lorsqu'il est activé
     * Transmet le signal d'activation jusqu'à l'ordinateur
     * */
    @Override
    public void sendPressedButtons(Button button) {
        // On le transmet à l'ordinateur
        computer.receivePressedButton(button);
    }

    /**
     * Renvoie la liste des boutons internes à la cabine
     * */
    @Override
    public ArrayList<Button> getButtonList() {
        return buttonList;
    }

}
