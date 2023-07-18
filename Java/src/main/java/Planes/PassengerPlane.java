package Planes;

import java.util.Objects;

public class PassengerPlane extends Plane {

    private int passengerCapacity;

    public PassengerPlane(String model, int speed, int flightDistance, int loadCapacity, int passengerCapacity) {
        super(model, speed, flightDistance, loadCapacity);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        return "\nPassenger plane " + super.toString().replace("}",
                ", passenger capacity=" + passengerCapacity +
                        "}");
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        PassengerPlane otherPlane = (PassengerPlane) o;
        return passengerCapacity == otherPlane.passengerCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerCapacity);
    }
}
