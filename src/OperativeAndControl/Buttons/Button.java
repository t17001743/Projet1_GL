package OperativeAndControl.Buttons;

public interface Button {

    void activate();
    void deactivate();
    boolean getLight();
    int getFloor();
    String getDirection();
}
