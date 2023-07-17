package RailCars;

import RailCars.RailroadCar;

public class PostOfficeRailCar extends RailroadCar {
    private int numOfReadLet;
    private int numOfUnreadLetters;
    private int numOfComplaints;
    private double grossWeight;
    private final boolean electGrid = true;

    public PostOfficeRailCar(String shipper, String securityInf, double netWeight, int numOfUnreadLetters,
                             int numOfReadLet ,int numOfComplaints, double grossWeight) {
        super(shipper, securityInf, netWeight, "Railroad Post Office");
        this.numOfReadLet = numOfReadLet;
        this.numOfUnreadLetters = numOfUnreadLetters;
        this.numOfComplaints = numOfComplaints;
        this.grossWeight = grossWeight;
    }

    public void writeComplaint(int num){
        this.numOfComplaints += num;
    }

    public void readLetter(int num){
        this.numOfReadLet += num;
        this.numOfUnreadLetters -= num;
    }

    public int getNumOfComplaints() {
        return numOfComplaints;
    }

    public int getNumOfReadLet() {
        return numOfReadLet;
    }

    public int getNumOfUnreadLetters() {
        return numOfUnreadLetters;
    }


    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public void setNumOfReadLet(int numOfReadLet) {
        this.numOfReadLet = numOfReadLet;
    }

    public void setNumOfUnreadLetters(int numOfUnreadLetters) {
        this.numOfUnreadLetters = numOfUnreadLetters;
    }

    public void setNumOfComplaints(int numOfComplaints) {
        this.numOfComplaints = numOfComplaints;
    }

    public String toString(){
        return super.toString() + "gross weight: " + this.grossWeight + "\n\t"
                + "electric grid: " + this.electGrid + "\n\t"
                + "numbers of read letters: " + this.numOfReadLet + "\n\t"
                + "numbers of unread letters: " + this.numOfUnreadLetters + "\n\t"
                + "num of complaints: " + this.numOfComplaints;

    }
}