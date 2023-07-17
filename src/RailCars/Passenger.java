package RailCars;

public class Passenger {
    private static double averWeight;

    public Passenger(){
        this.averWeight = 70;
    }

    public static double getAverWeight() {
        return averWeight;
    }


    public void setAverWeight(double averWeight) {
        this.averWeight = averWeight;
    }
}
