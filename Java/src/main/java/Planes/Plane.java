package Planes;

import java.util.Objects;

abstract public class Plane {
    private String model;
    private int speed;
    private int flightDistance;
    private int loadCapacity;

    public Plane(String model, int speed, int flightDistance, int loadCapacity) {
        this.model = model;
        this.speed = speed;
        this.flightDistance = flightDistance;
        this.loadCapacity = loadCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public String toString() {
        return "{" +
                "model = '" + model + '\'' +
                ", speed = " + speed +
                ", flight distance = " + flightDistance +
                ", load capacity = " + loadCapacity +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Plane otherPlane = (Plane) o;
        return speed == otherPlane.speed &&
                flightDistance == otherPlane.flightDistance &&
                loadCapacity == otherPlane.loadCapacity &&
                Objects.equals(model, otherPlane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, speed, flightDistance, loadCapacity);
    }
}
