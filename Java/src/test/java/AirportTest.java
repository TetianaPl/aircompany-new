import Planes.ExperimentalPlane;
import models.ClassificationLevels;
import models.ExperimentalTypes;
import models.MilitaryTypes;
import org.testng.Assert;
import org.testng.annotations.Test;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {

    private PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
    private List<Plane> planes = Arrays.asList(
        new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
        new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
        planeWithMaxPassengerCapacity,
        new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
        new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
        new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
        new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
        new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
        new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryTypes.BOMBER),
        new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryTypes.BOMBER),
        new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryTypes.BOMBER),
        new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryTypes.FIGHTER),
        new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryTypes.FIGHTER),
        new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryTypes.TRANSPORT),
        new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevels.SECRET),
        new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VERTICAL_TAKEOFF_AND_LANDING,
            ClassificationLevels.TOP_SECRET)
    );

    @Test
    public void testGetTransportMilitaryPlanes() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> transportMilitaryPlanes = airport.getMilitaryPlanesByType(MilitaryTypes.TRANSPORT);
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            if ((militaryPlane.getMilitaryType() != MilitaryTypes.TRANSPORT)) {
                Assert.fail("There is a plane with " + militaryPlane.getMilitaryType()
                    + " type in the set of transport military plane.");
            }
        }
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        PassengerPlane actualPlaneWithMaxPassengerCapacity = airport.findPlaneWithMaxPassengerCapacity();
        Assert.assertEquals(actualPlaneWithMaxPassengerCapacity, planeWithMaxPassengerCapacity);
    }

    @Test
    public void testSortByLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();

        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getLoadCapacity() > nextPlane.getLoadCapacity()) {
                Assert.fail("At the " + i + " position in the list of planes LoadCapacity is " +
                    currentPlane.getLoadCapacity() + ". It is greater then at the next position: "
                    + nextPlane.getLoadCapacity() +
                    ".\nExpected: planes sorted ascending by LoadCapacity.");
            }
        }
    }

    @Test
    public void testThereIsAtLeastOneBomber() {
        Airport airport = new Airport(planes);
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getMilitaryPlanesByType(MilitaryTypes.BOMBER);
        if (bomberMilitaryPlanes.isEmpty()) {
            Assert.fail("The set of bombers is empty, expected there is at least one bomber in military planes");
        }
    }

    @Test
    public void testThereIsAtLeastOneExperimentalPlaneWithClassificationLevelHigherThanUnclassified() {
        Airport airport = new Airport(planes);
        List<ExperimentalPlane> experimentalPlanes = airport.getPlanesByType(ExperimentalPlane.class);
        boolean hasClassifiedPlanes = experimentalPlanes.stream()
                                                        .anyMatch(plane -> plane.getClassificationLevel()
                                                            != ClassificationLevels.UNCLASSIFIED);
        Assert.assertTrue(hasClassifiedPlanes,
            "The set of experimental planes with classification level higher than Unclassified is empty, "
                + "\nExpected: there is at least one classified experimental plane.");
    }
}
