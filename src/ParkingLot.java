import exceptions.CarParkException;
import exceptions.CarRetrievalException;
import exceptions.ParkingLotFullException;

import java.util.*;

public class ParkingLot {

    private int parkingLotCapacity;
    private Map<Object, String> carsParked = new HashMap<Object, String>() ;


    public ParkingLot(Integer parkingLotCapacity) {

        this.parkingLotCapacity = parkingLotCapacity;
    }

    private boolean isParkingAvailable() {
        return parkingLotCapacity - carsParked.size() > 0 ;
    }


    public Object park(String regNo) throws CarParkException, ParkingLotFullException {
        if(isCarAlreadyParked(regNo)) {
            throw new CarParkException("Car already parked");
        }
        if(!isParkingAvailable()) {
            throw new ParkingLotFullException();
        }
        Object token = new Object();
        carsParked.put(token,regNo);

        return token;
    }

    private boolean isCarAlreadyParked(String regNo) {
        return carsParked.containsValue(regNo);
    }

    public String retrieve(Object token) throws CarRetrievalException {
        if(!carsParked.containsKey(token)){
            throw new CarRetrievalException();
        }
        return carsParked.remove(token);
    }
}
