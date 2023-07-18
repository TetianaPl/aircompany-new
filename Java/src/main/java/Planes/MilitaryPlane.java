package Planes;

import models.MilitaryTypes;

import java.util.Objects;

public class MilitaryPlane extends Plane {

    private MilitaryTypes militaryType;

    public MilitaryPlane(String model, int speed, int flightDistance, int loadCapacity, MilitaryTypes militaryType) {
        super(model, speed, flightDistance, loadCapacity);
        this.militaryType = militaryType;
    }

    public MilitaryTypes getMilitaryType() {
        return militaryType;
    }

    @Override
    public String toString() {
        return "\nMilitary plane " + super.toString().replace("}",
                ", military type = " + militaryType +
                        "}");
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        MilitaryPlane otherPlane = (MilitaryPlane) o;
        return militaryType == otherPlane.militaryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), militaryType);
    }


}
