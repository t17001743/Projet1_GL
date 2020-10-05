package OperativeAndControl;

import OperativeAndControl.Buttons.Button;
import OperativeAndControl.Buttons.CallButton;
import OperativeAndControl.Buttons.EmergencyButton;
import OperativeAndControl.Buttons.FloorButton;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Computer implements Runnable {

    private Cabin cabin;
    private ExternalController externalController;
    private Engine engine;
    private boolean emergency;
    private ArrayList<Integer> priorityList;
    private int nbFloors;
    private int lastFloorRemoved;

    /**
     * Créer la cabine, le moteur et les boutons*/
    public Computer(int nbFloors, int numFloor){
        // On créer les éléments
        cabin = new Cabin(this, nbFloors, numFloor);
        externalController = new ExternalController(this, nbFloors);
        engine = new Engine();
        this.nbFloors = nbFloors;

        priorityList = new ArrayList(); // étage(s) à desservir
        emergency = false; //aucune urgence
        lastFloorRemoved = -1;
    }

    public Computer(int nbFloors){
        // On créer les éléments
        cabin = new Cabin(this, nbFloors);
        externalController = new ExternalController(this, nbFloors);
        engine = new Engine();
        this.nbFloors = nbFloors;

        priorityList = new ArrayList(); //étage(s) à desservir
        emergency = false; //aucune urgence
    }

    // Le cycle d'exécution de l'ordinateur
    public void run(){
        // On commence par vérifier que la liste d'étages à desservir n'est pas vide
        if(priorityList.size() > 0) {
            // Si le moteur est à l'arrêt, on le relance
            if(engine.getCurrentState() == CurrentState.STOP) startEngine();

            // On attend une seconde
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // On met à jour la position de la cabine
            setPositionCabin();

            // On vérifie si on est pas arrivé à un étage
            checkPriorityList();

            // Si une urgence a été déclaré, on l'enlève (en réalité, un technicien s'occuperait de lever l'urgence une fois le problème réglé)
            if (emergency) {
                removeEmergencyStop();
            }
        }

        // On attend une seconde
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // On recommence le cycle
        run();
    }

    public void receivePressedButton(Button button){
        // Si le bouton est un arrêt d'urgence, on le traite forcément
        if(button.getClass() == EmergencyButton.class) {
            emergencyStop();
        }

        // Sinon ce bouton correspond à une demande provenant d'un étage ou vers un étage
        // Si le bouton est déjà dans la liste, on ne fait rien
        else {
            if (!(priorityList.contains(button.getFloor()))) {
                // Si le bouton correspond à une requête pour aller vers un étage
                if (button.getClass() == FloorButton.class) floorRequest(button.getFloor());

                // Sinon, le bouton correspond à un appelle de l'ascenseur
                else callCabin(button.getFloor(), button.getDirection());
            }
        }
    }

    // Si le bouton correspond à une requête pour aller vers un étage
    public void floorRequest(int floor){
        // CAS 1 : Si la liste est vide, on ajoute simplement l'étage désiré
        if (priorityList.size() == 0) priorityList.add(floor);

        // CAS 2 : Si l'étage demandé est entre la position de la cabine et le prochaine étage à désservir
        else if ((priorityList.get(0) > floor && cabin.getPosition() < floor) || (priorityList.get(0) < floor && cabin.getPosition() > floor)) {
            // Alors je peux l'ajouter en tête de liste
            priorityList.add(0, floor);
        }

        // CAS 3 : Si l'étage demandé est plus grand que le prochain étage à désservir (et donc que cet étage n'est pas une étape intermédiaire CAS 2)
        else if (priorityList.get(0) < floor) {

            // CAS 3.1 : Si la cabine n'est pas entre l'étage à desservir est l'étage demandé
            // C'est donc qu'elle monte ou qu'elle est à l'arrêt et s'apprête à monter
            if (cabin.getPosition() < priorityList.get(0)) {
                // On créé un index et un booleen pour parcourir la liste
                int index = 1; // on a déjà comparé le 0
                boolean test = false;

                // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                while (index < priorityList.size() && !test) {
                    // Si l'étage à desservir est plus grand que l'étage demandé
                    // Ou que l'étage à desservir nécessite un changement de sens de l'ascenseur (il est plus petit que le prochain étage désservit)
                    if (priorityList.get(index) > floor || priorityList.get(index) < priorityList.get(0)) {
                        // Alors on a trouvé la position à laquelle placer notre étage demandé
                        test = true;
                    }
                    // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé et plus grand que le prochain étage desservit
                    // C'est donc un étage intermédiaire à desservir avant l'étage demandé
                    index++;
                }

                // On ajoute l'étage demandé à la position trouvé
                priorityList.add(index, floor);
            }

            // CAS 3.2 : Sinon, la cabine est entre l'étage à désservir est l'étage demandé
            // C'est donc qu'elle descend ou qu'elle est à l'arrêt et s'apprête à descendre
            else {
                // On créé un index et un booleen pour parcourir la liste
                int index = 1; // on a déjà comparé le 0
                boolean test = false;

                // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                while (index < priorityList.size() && !test) {
                    // Si l'étage à desservir est plus grand que l'étage demandé
                    // C'est que l'ascenseur aura changé de sens et veut aller plus haut que l'étage demandé
                    if (priorityList.get(index) > floor) {
                        // Alors on a trouvé la position à laquelle placer notre étage demandé
                        test = true;
                    }
                    // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé
                    // C'est donc un étage intermédiaire à desservir avant l'étage demandé
                    index++;
                }

                // On ajoute l'étage demandé à la position trouvé
                priorityList.add(index, floor);
            }
        }

        // CAS 4 : Sinon, c'est que l'étage demandé est plus petit que le prochain étage à désservir (et donc que cet étage n'est pas une étape intermédiaire CAS 2)
        else {

            // CAS 4.1 : Si la cabine n'est pas entre l'étage à desservir et l'étage demandé
            // C'est donc qu'elle descend ou qu'elle est à l'arrêt et s'apprête à descendre
            if (cabin.getPosition() > priorityList.get(0)) {
                // On créé un index et un booleen pour parcourir la liste
                int index = 1; // on a déjà comparé le 0
                boolean test = false;

                // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                while (index < priorityList.size() && !test) {
                    // Si l'étage à desservir est plus petit que l'étage demandé
                    // Ou que l'étage à desservir nécessite un changement de sens de l'ascenseur (il est plus grand que le prochain étage désservit)
                    if (priorityList.get(index) < floor || priorityList.get(index) > priorityList.get(0)) {
                        // Alors on a trouvé la position à laquelle placer notre étage demandé
                        test = true;
                    }
                    // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé et plus petit que le prochain étage desservit
                    // C'est donc un étage intermédiaire à desservir avant l'étage demandé
                    index++;
                }

                // On ajoute l'étage demandé à la position trouvé
                priorityList.add(index, floor);
            }

            // CAS 4.2 : Sinon, la cabine est entre l'étage à désservir et l'étage demandé
            // C'est donc qu'elle monte ou qu'elle est à l'arrêt et s'apprête à monter
            else {
                // On créé un index et un booleen pour parcourir la liste
                int index = 1; // on a déjà comparé le 0
                boolean test = false;

                // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                while (index < priorityList.size() && !test) {
                    // Si l'étage à desservir est plus petit que l'étage demandé
                    // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                    if (priorityList.get(index) < floor) {
                        // Alors on a trouvé la position à laquelle placer notre étage demandé
                        test = true;
                    }
                    // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                    // C'est donc une étage intermédiaire à desservir avant l'étage demandé
                    index++;
                }

                // On ajoute l'étage demandé à la position trouvé
                priorityList.add(index, floor);
            }
        }
    }

    // Si le bouton correspond à un appelle de l'ascenseur
    public void callCabin(int floor, String direction){
        // CAS 0 : Si la liste est vide, on ajoute simplement l'étage désiré
        if (priorityList.size() == 0) priorityList.add(floor);

        else {

            // CAS 1 : L'ascenseur est positionné à un étage supérieur au mien
            if (cabin.getPosition() > floor) {
                // CAS 1.1 : L'ascenseur se dirige vers un étage inférieur au mien et donc au sien
                if (priorityList.get(0) < floor) {
                    // CAS 1.1.1 : Je souhaite descendre
                    if (direction.equals("DOWN")) {
                        // Je peux ajouter mon étage comme le prochain étage à desservir
                        priorityList.add(0, floor);
                    }

                    // CAS 1.1.2 : Je souhaite monter
                    else {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus grand que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus haut que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé
                            // L'ascenseur sera donc entrain de descendre, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }
                }

                // CAS 1.2 : L'ascenseur se dirige vers un étage supérieur au mien mais inférieur au sien
                else if (priorityList.get(0) > floor && priorityList.get(0) < cabin.getPosition()) {
                    // CAS 1.2.1 : Je souhaite descendre
                    if (direction.equals("DOWN")) {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus petit que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus haut que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            // C'est donc un étage intermédiaire à desservir avant l'étage demandé
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }

                    // CAS 1.2.2 : Je souhaite monter
                    else {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus petit que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à partir de laquelle l'ascenseur nous permettra de monter
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            index++;
                        }

                        test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus grand que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus haut que l'étage demandé
                            if (priorityList.get(index) > floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé
                            // L'ascenseur sera donc entrain de descendre, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }
                }

                // CAS 1.3 : L'ascenseur se dirige vers un étage supérieur au sien et donc au mien
                else {
                    // CAS 1.3.1 : Je souhaite descendre
                    if (direction.equals("DOWN")) {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus petit que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            // L'ascenseur sera donc entrain de monter, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }

                    // CAS 1.3.2 : Je souhaite monter
                    else {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus petit que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à partir de laquelle l'ascenseur nous permettra de monter
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            index++;
                        }

                        test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus grand que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus haut que l'étage demandé
                            if (priorityList.get(index) > floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé
                            // L'ascenseur sera donc entrain de descendre, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }
                }
            }

            // CAS 2 : L'ascenseur est positionné à un étage inférieur au mien
            else {
                // CAS 1.1 : L'ascenseur se dirige vers un étage supérieur au mien et donc au sien
                if (priorityList.get(0) > floor) {
                    // CAS 1.1.1 : Je souhaite monter
                    if (direction.equals("UP")) {
                        // Je peux ajouter mon étage comme le prochain étage à desservir
                        priorityList.add(0, floor);
                    }

                    // CAS 1.1.2 : Je souhaite descendre
                    else {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus petit que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé
                            // L'ascenseur sera donc entrain de descendre, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }
                }

                // CAS 1.2 : L'ascenseur se dirige vers un étage inférieur au mien mais supérieur au sien
                else if (priorityList.get(0) < floor && priorityList.get(0) > cabin.getPosition()) {
                    // CAS 1.2.1 : Je souhaite monter
                    if (direction.equals("UP")) {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus grand que l'étage demandé
                            // C'est que l'ascenseur veut aller plus haut que l'étage demandé
                            if (priorityList.get(index) > floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            // C'est donc un étage intermédiaire à desservir avant l'étage demandé
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }

                    // CAS 1.2.2 : Je souhaite descendre
                    else {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus grand que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                            if (priorityList.get(index) > floor) {
                                // Alors on a trouvé la position à partir de laquelle l'ascenseur nous permettra de descendre
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            index++;
                        }

                        test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus petit que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            // L'ascenseur sera donc entrain de monter, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }
                }

                // CAS 1.3 : L'ascenseur se dirige vers un étage inférieur au sien et donc au mien
                else {
                    // CAS 1.3.1 : Je souhaite monter
                    if (direction.equals("UP")) {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus grand que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus haut que l'étage demandé
                            if (priorityList.get(index) > floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé
                            // L'ascenseur sera donc entrain de monter, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }

                    // CAS 1.3.2 : Je souhaite descendre
                    else {
                        // On créé un index et un booleen pour parcourir la liste
                        int index = 1; // on a déjà comparé le 0
                        boolean test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus grand que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus haut que l'étage demandé
                            if (priorityList.get(index) > floor) {
                                // Alors on a trouvé la position à partir de laquelle l'ascenseur nous permettra de descendre
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus petit que l'étage demandé
                            index++;
                        }

                        test = false;

                        // Tant que nous n'avons pas parcouru toute la liste ou que nous n'avons pas trouvé où placer l'étage demandé
                        while (index < priorityList.size() && !test) {
                            // Si l'étage à desservir est plus petit que l'étage demandé
                            // C'est que l'ascenseur aura changé de sens et veut aller plus bas que l'étage demandé
                            if (priorityList.get(index) < floor) {
                                // Alors on a trouvé la position à laquelle placer notre étage demandé
                                test = true;
                            }
                            // Sinon c'est que le prochain étage à desservir est plus grand que l'étage demandé
                            // L'ascenseur sera donc entrain de descendre, ce n'est pas ma direction
                            index++;
                        }

                        // On ajoute l'étage demandé à la position trouvé
                        priorityList.add(index, floor);
                    }
                }
            }
        }
    }

    // Si le bouton correspond à un arrêt d'urgence
    public void emergencyStop(){
        emergency = true;
        engine.stop();
        priorityList = new ArrayList<>(); //requête précédentes effacées

        // On ferme les portes si elles sont ouvertes
        if(cabin.getDoors()) {
            cabin.closeDoors();
        }
    }

    // Permet de mettre fin à l'état d'urgence
    private void removeEmergencyStop() {
        emergency = false;
        cabin.getButtonList().get(nbFloors).deactivate();
    }

    public void setPositionCabin(){
        double position = cabin.getPosition();
        double speed = engine.getSpeed();

        // On bouge la cabine
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

    public void checkPriorityList() {
        // On regarde si on est arrivé au prochain étage à desservir si la liste n'est pas vide
        if(priorityList.size() > 0) {
            if (priorityList.get(0) == cabin.getPosition()) {
                // On arrête le moteur
                engine.stop();

                // On supprime l'étage de la liste des étages à desservir
                lastFloorRemoved = priorityList.remove(0);
                // On indique que l'étage a été desservi
                cabin.getButtonList().get(lastFloorRemoved).deactivate();
                externalController.getButtonList().get(2 * lastFloorRemoved).deactivate();
                externalController.getButtonList().get(2 * lastFloorRemoved + 1).deactivate();

                // On ouvre les portes de la cabine
                cabin.openDoors();

                // On attend une seconde
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // On ferme les portes
                cabin.closeDoors();
                // On relance le moteur

            }
        }
    }

    public void startEngine() {
        // Si la liste d'étages à desservir n'est pas vide
        if(priorityList.size() > 0) {
            // Si le prochain étage à desservir est en dessous de nous, on descend
            if(priorityList.get(0) < cabin.getPosition()) {
                engine.goDown();
            }

            // Sinon, on monte
            else {
                engine.goUp();
            }

            /*
            // Si le moteur est à l'arrêt
            if(engine.getCurrentState() == CurrentState.STOP) {
                // Si le prochain étage à desservir est en dessous de nous, on descend
                if(priorityList.get(0) < cabin.getPosition()) {
                    engine.goDown();
                }

                // Sinon, on monte
                else {
                    engine.goUp();
                }
            }

            // Si le moteur n'est pas à l'arrêt
            else {
                // Si on est arrivé au dernier étage alors qu'on montait, on change de direction
                if (cabin.getPosition() == nbFloors && engine.getCurrentState() == CurrentState.UP) {
                    engine.goDown();
                }
                // Si on est arrivé au premier étage alors qu'on descendait, on change de direction
                else if (cabin.getPosition() == 0 && engine.getCurrentState() == CurrentState.DOWN) {
                    engine.goUp();
                }
                // Si on est entrain de monter alors que le prochain étage à desservir est en dessous de nous, on change de direction
                else if (engine.getCurrentState() == CurrentState.UP && priorityList.get(0) < cabin.getPosition()) {
                    engine.goDown();
                }
                // Si on est entrain de descendre alors que le prochain étage à desservir est en dessus de nous, on change de direction
                else if (engine.getCurrentState() == CurrentState.DOWN && priorityList.get(0) > cabin.getPosition()) {
                    engine.goUp();
                }
            }*/
        }
    }

    public double getPositionCabin(){ return cabin.getPosition(); }

    public Cabin getCabin() { return cabin; }

    public ExternalController getExternalController() { return externalController; }

    public Engine getEngine() { return engine; }

    public boolean getEmergency() { return emergency; }

    public int getLastFloorRemoved() { return lastFloorRemoved; }

}
