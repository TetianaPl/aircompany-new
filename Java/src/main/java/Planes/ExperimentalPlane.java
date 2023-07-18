package Planes;

import models.ClassificationLevels;
import models.ExperimentalTypes;

import java.util.Objects;

public class ExperimentalPlane extends Plane {

    private ExperimentalTypes experimentalType;
    private ClassificationLevels classificationLevel;

    public ExperimentalPlane(String model, int speed, int flightDistance, int loadCapacity, ExperimentalTypes experimentalType, ClassificationLevels classificationLevel) {
        super(model, speed, flightDistance, loadCapacity);
        this.experimentalType = experimentalType;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevels getClassificationLevel() {
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevels classificationLevel) {
        this.classificationLevel = classificationLevel;
    }

    @Override
    public String toString() {
        return "\nExperimental plane " + super.toString().replace("}",
                ", experimental type = " + experimentalType +
                        ", classification level = " + classificationLevel +
                        "}");
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        ExperimentalPlane otherPlane = (ExperimentalPlane) o;
        return experimentalType == otherPlane.experimentalType &&
                classificationLevel == otherPlane.classificationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalType, classificationLevel);
    }

}
