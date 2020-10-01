package OperativeAndControl.Buttons;

public class EmergencyButton implements Button {

    private boolean light;

    public EmergencyButton(){
        light = false;
    }

    @Override
    public void activate() {
        light = true;
    }

    @Override
    public void deactivate() {
        light = false;
    }

    @Override
    public boolean getLight() {
        return light;
    }

    @Override
    public int getFloor(){ return -1; }

}
