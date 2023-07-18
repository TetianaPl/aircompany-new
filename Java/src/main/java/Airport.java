import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;
import models.MilitaryTypes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public <T extends Plane> List<T> getPlanesByType(Class<T> planeClass) {
        List<T> result = new ArrayList<>();
        for (Plane p : planes) {
            if (planeClass.isInstance(p)) {
                result.add(planeClass.cast(p));
            }
        }
        return result;
    }

    public PassengerPlane findPlaneWithMaxPassengerCapacity() {
        List<PassengerPlane> passengerPlanes = getPlanesByType(PassengerPlane.class);
        PassengerPlane planeWithMaxCapacity = null;
        try {
            planeWithMaxCapacity = passengerPlanes.get(0);
            for (PassengerPlane plane : passengerPlanes) {
                if (plane.getPassengerCapacity() > planeWithMaxCapacity.getPassengerCapacity()) {
                    planeWithMaxCapacity = plane;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nCould not find a plane with max passenger capacity because the set of passenger planes is empty.");
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryTypes type) {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getPlanesByType(MilitaryPlane.class);
        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getMilitaryType() == type) {
                transportMilitaryPlanes.add(plane);
            }
        }
        return transportMilitaryPlanes;
    }

    public Airport sortByFlightDistance() {
        planes.sort(Comparator.comparing(Plane::getFlightDistance));
        return this;
    }


    public Airport sortBySpeed() {
        planes.sort(Comparator.comparing(Plane::getSpeed));
        return this;
    }

    public Airport sortByLoadCapacity() {
        planes.sort(Comparator.comparing(Plane::getLoadCapacity));
        return this;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

}
