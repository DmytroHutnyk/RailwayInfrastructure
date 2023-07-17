package RailCars;

import java.util.ArrayList;

public class PassengerRailCar extends RailroadCar {
    private int numOfSeats;
    private boolean toiletAvailability;
    private final boolean electGrid = true;
    private ArrayList<Passenger> passengers;
    private int numOfPassengers = 0;
    private double grossWeight;

    public PassengerRailCar(String shipper, String securityInf, double netWeight, int numOfSeats,
                            boolean toiletAvailability){
        super(shipper, securityInf, netWeight, "Passenger Railcar");
        this.numOfSeats = numOfSeats;
        this.toiletAvailability = toiletAvailability;
        this.passengers = new ArrayList<>();
        grossWeight = this.passengers.size() * Passenger.getAverWeight() + getNetWeight();
    }

    public PassengerRailCar(String shipper, String securityInf, double netWeight, int numOfSeats,
                            boolean toiletAvailability, int numOfPassengers){
        super(shipper, securityInf, netWeight, "Passenger Railcar");
        this.numOfSeats = numOfSeats;
        this.toiletAvailability = toiletAvailability;

        try {
            if(numOfPassengers < 0 || numOfPassengers > numOfSeats)
                throw new IllegalArgumentException(
                        "Numbers of passengers must be bigger then -1 and smaller or equal then number of seats");
            this.numOfPassengers = numOfPassengers;
            this.passengers = new ArrayList<>(numOfPassengers);
            for(int i = 1; i <= numOfPassengers; i++){
                Passenger p = new Passenger();
                this.passengers.add(p);
            }

            grossWeight = this.passengers.size() * Passenger.getAverWeight() + getNetWeight();
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }



    }


    @Override
    public void setNetWeight(double netWeight){
        super.setNetWeight(netWeight);
        // recalculate gross weight
        this.grossWeight = this.passengers.size() * Passenger.getAverWeight() + getNetWeight();
    }

    public void setNumOfPassengers  (int numOfPassengers) {

        try {
            if(numOfPassengers < 0 || numOfPassengers > numOfSeats)
                throw new IllegalArgumentException(
                        "Numbers of passengers must be bigger then -1 and smaller or equal then number of seats");
            this.passengers = new ArrayList<>(numOfPassengers);
            for(int i = 1; i <= numOfPassengers; i++){
                Passenger p = new Passenger();
                this.passengers.add(p);
            }
            this.grossWeight = this.passengers.size() * Passenger.getAverWeight() + getNetWeight();
        } catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public double getGrossWeight(){
        return this.passengers.size() * Passenger.getAverWeight() + getNetWeight();
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public boolean isToiletAvailability() {
        return toiletAvailability;
    }

    public void setToiletAvailability(boolean toiletAvailability) {
        this.toiletAvailability = toiletAvailability;
    }

    public boolean isElectGrid() {
        return electGrid;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }


    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String toString(){
        return super.toString() +
                "Gross weight: "+ this.grossWeight + "\n\t"+
                "Number of seats: " + this.numOfSeats + "\n\t"+
                "Toilet availability: " + this.toiletAvailability + "\n\t"+
                "Electrical gird required: " + this.electGrid + "\n\t"+
                "Number of passengers: " + numOfPassengers;
    }

}
