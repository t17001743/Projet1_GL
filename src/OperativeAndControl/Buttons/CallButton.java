package OperativeAndControl.Buttons;

public class CallButton implements Button {

    private boolean light;
    private int numFloor;

    public CallButton(int numFloor){
        light = false;
        this.numFloor = numFloor;
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
        return this.light;
    }

    @Override
    public int getFloor(){ return numFloor; }

}
