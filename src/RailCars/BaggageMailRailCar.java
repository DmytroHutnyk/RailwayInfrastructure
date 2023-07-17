package RailCars;

public class BaggageMailRailCar extends RailroadCar {
    private double grossWeight;
    private int numOfBags;
    private double bagWeight;
    private final boolean electGrid = false;


    public BaggageMailRailCar(String shipper, String securityInf, double netWeight, int numOfBags,
                              double bagWeight){
        super(shipper,securityInf,netWeight, "Baggage Mail Railcar");
        this.grossWeight = numOfBags * bagWeight + netWeight;
        this.numOfBags = numOfBags;
        this.bagWeight = bagWeight;
    }

    public int getNumOfBags() {
        return numOfBags;
    }


    public void addBag(int num) {
        this.numOfBags += num;
        grossWeight += num*bagWeight;
    }

    public void removeBag(int num) {
        this.numOfBags -= num;
        grossWeight -= num*bagWeight;;
    }

    public void setNumOfBags(int numOfBags) {
        this.numOfBags = numOfBags;
        grossWeight = numOfBags * bagWeight + this.getNetWeight();
    }

    public double getBagWeight() {
        return bagWeight;
    }

    public void setBagWeight(double bagWeight) {
        this.bagWeight = bagWeight;
        grossWeight = numOfBags * bagWeight;
    }




    public String toString(){
        return super.toString() +
                "Gross weight: " + this.grossWeight +"\n\t"+
                "Electrical grid: " + this.electGrid +"\n\t"+
                "Numbers of bags: " + this.numOfBags +"\n\t"+
                "Bag weight: " + this.bagWeight;
    }
}
