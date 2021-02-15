package ObjectOrientedDesign;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private Level[] levels;
    private final int  NUM_LEVEL = 5;

    public ParkingLot(){...}

    // Park a Vehicle in a spot. Return false if failed
    public boolean parkVehicle(Vehicle vehicle){...}


    public class Level{
        private int floorLevel;
        private ParkingSpot[] spots;
        private int availableSpots = 0;
        private static final int SPOTS_PER_ROW = 10;

        public Level(int floorLevel, int availableSports) {
            this.floorLevel = floorLevel;
            this.availableSpots = availableSports;
        }

        public int getAvailableSpots() {
            return availableSpots;
        }

        // Park a vehicle in available spot. Return false if failed
        public boolean parkVehicle(Vehicle vehicle){
            // make use parkStartingAtSpot() and findAvailableSpots()
        }
        private boolean parkStartingAtSpot(int num, Vehicle vehicle){}
        private int findAvailableSpots(Vehicle vehicle){}

        public void removedOneSpot(){availableSpots++;}
    }

    public class ParkingSpot{
        private int id;
        private int row;
        private Level level;
        private int spotNumber;
        private Vehicle currentVehicle;
        private VehicleSize vehicleSize;

        public ParkingSpot(int id, int row, Level level, int spotNumber, VehicleSize vehicleSize) {
            this.id = id;
            this.row = row;
            this.level = level;
            this.spotNumber = spotNumber;
            this.vehicleSize = vehicleSize;
        }

        public boolean isAvailable(){ return currentVehicle == null; }

        public boolean canFitVehicle(Vehicle vehicle){...}
        // park vehicle in this spot
        public boolean park(Vehicle vehicle){...}

        public int getRow() {
            return row;
        }

        public int getSpotNumber() {
            return spotNumber;
        }

        public void removeVehicle(){ currentVehicle = null; }
    }

    public enum VehicleSize{ Motorcycle, Compact, Large}

    public abstract class Vehicle {
        private Owner owner;
        protected ArrayList<ParkingSpot> parkingSpots =  new ArrayList<ParkingSpot>();
        protected String licensePlate;
        protected int spotNeeded;
        protected VehicleSize vehicleSize;

        public int getSportNeeded() {
            return spotNeeded;
        }

        public VehicleSize getVehicleSize() {
            return vehicleSize;
        }

        // parking vehicle in the spot
        public void parkInSpot(ParkingSpot ps){ parkingSpots.add(ps); }

        public void clearSpot(){...}

        // check if the vehicle can fit in the parking spot or not
        public abstract boolean canFitInSpot(ParkingSpot ps);

        public Vehicle(Owner owner) {
            this.owner = owner;
        }
    }

    public class Motocycle extends Vehicle {
        public Motocycle(Owner owner) {
            super(owner);
            spotNeeded = 1;
            vehicleSize = VehicleSize.Motorcycle;
        }

        public boolean canFitInSpot(ParkingSpot ps){...}
    }

    public class Car extends Vehicle {
        public Car(Owner owner) {
            super(owner);
            spotNeeded = 1;
            vehicleSize = VehicleSize.Compact;
        }

        public boolean canFitInSpot(ParkingSpot ps){...}
    }

    public class Bus extends Vehicle {
        public Bus(Owner owner) {
            super(owner);
            spotNeeded = 5;
            vehicleSize = VehicleSize.Large;
        }

        public boolean canFitInSpot(ParkingSpot ps){...}
    }
}
