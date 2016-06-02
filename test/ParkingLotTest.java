import exceptions.CarParkException;
import exceptions.CarRetrievalException;
import exceptions.ParkingLotFullException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingLotTest {


    public static final String CAR = "AX123345";

    @Test
    public void testParkingSuccessful() throws CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        assertNotNull(parkingLot.park("AX123345"));
    }

    @Test(expected = ParkingLotFullException.class)
    public void testCannotParkIfParkingLotHasZeroCapacity() throws CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(0);
        parkingLot.park("AX123345");
    }

    @Test (expected = ParkingLotFullException.class)
    public void testCannotParkIfParkingLotIsFull() throws CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park("AX123345");
        parkingLot.park("1231233455757");
        parkingLot.park("MH 12 skfhk");
    }

    @Test(expected = CarParkException.class)
    public void testSameCarCannotBeParkedTwice() throws CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(3);
        parkingLot.park("AX123345");
        parkingLot.park("AX123345");
    }

    @Test(expected = CarParkException.class)
    public void testSameCarCanNotBeParkedTwiceEvenWhenParkingLotIsFull() throws CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park("AX123345");
        parkingLot.park("AX123345");
    }

    @Test
    public void testRetriveParkedCar() throws CarRetrievalException, CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        String parkedCar = "AX123345";
        Object token = parkingLot.park(parkedCar);
        String retrievedCar = parkingLot.retrieve(token);
        assertThat(retrievedCar, is(parkedCar));
    }

    @Test(expected = CarRetrievalException.class)
    public void testCannotRetrieveUsingTheSameToken() throws CarRetrievalException, CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object token = parkingLot.park(CAR);
        parkingLot.retrieve(token);
        parkingLot.retrieve(token);
    }

    @Test
    public void testCanParkAfterACarIsRetrievedFromAFullParkingLot() throws CarParkException, CarRetrievalException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object token = parkingLot.park(CAR);
        parkingLot.retrieve(token);
        assertNotNull(parkingLot.park(CAR));
    }

    @Test(expected = CarRetrievalException.class)
    public void testCannotRetrieveUsingInvalidToken() throws CarRetrievalException, CarParkException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(CAR);
        parkingLot.retrieve(new Object());
    }



}
