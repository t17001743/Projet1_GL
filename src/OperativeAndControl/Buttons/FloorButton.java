package OperativeAndControl.Buttons;

public class FloorButton implements Button {

    private boolean light;
    private int numFloor;

    public FloorButton(int numFloor){
        this.light = false;
        this.numFloor = numFloor;
    }

    @Override
    public void activate() {
        this.light = true;
    }

    @Override
    public void deactivate() {
        this.light = false;
    }

    @Override
    public boolean getLight() {
        return light;
    }

    @Override
    public int getFloor() {
        return numFloor;
    }


}
