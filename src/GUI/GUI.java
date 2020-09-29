package GUI;

// Cette classe sert à regrouper les divers éléments constituants le GUI pour une utilisation simplifié par les boutons
public class GUI {

    private InsidePanel insidePanel;
    private OutsidePanel outsidePanel;
    private Elevator elevator;

    public void setInsidePanel(InsidePanel insidePanel) {
        this.insidePanel = insidePanel;
    }

    public void setOutsidePanel(OutsidePanel outsidePanel) {
        this.outsidePanel = outsidePanel;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public InsidePanel getInsidePanel() {
        return insidePanel;
    }

    public OutsidePanel getOutsidePanel() {
        return outsidePanel;
    }

    public Elevator getElevator() {
        return elevator;
    }
}
