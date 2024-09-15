import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import models.MilitaryTypes;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public <T extends Plane> List<T> getPlanesByType(Class<T> planeClass) {
        return planes.stream()
                     .filter(planeClass::isInstance)
                     .map(planeClass::cast)
                     .collect(Collectors.toList());
    }

    public PassengerPlane findPlaneWithMaxPassengerCapacity() {
        return getPlanesByType(PassengerPlane.class)
            .stream()
            .max(Comparator.comparingInt(PassengerPlane::getPassengerCapacity))
            .orElseThrow(() -> new NoSuchElementException(
                "\nCould not find a plane with max passenger capacity because the set of passenger planes is empty."));
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryTypes type) {
        return getPlanesByType(MilitaryPlane.class)
            .stream()
            .filter(plane -> plane.getMilitaryType() == type)
            .collect(Collectors.toList());
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
