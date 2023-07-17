package RailCars;

import RailCars.HeavyFreightCar;

public class ExplosiveRailCar extends HeavyFreightCar {

    private boolean electGrid = false;
    private int numOfExtinguishers;
    private final int maxNumOfExtinguishers = 5;
    private double timeToPutOutFire;
    public ExplosiveRailCar(String shipper, String securityInf, double netWeight, double cargoWeight,
                            int numOfExtinguishers){
        super(shipper, securityInf, netWeight, cargoWeight, "Explosives Railcar");
        try {
            if(numOfExtinguishers > maxNumOfExtinguishers || numOfExtinguishers < 1)
                throw new IllegalArgumentException("maximum number of extinguishers is 5 and min is 1");
            this.numOfExtinguishers = numOfExtinguishers;
            timeToPutOutFire = 900 / (numOfExtinguishers * 60);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public void increaseNumOfExtinguishers(int num){
        try {
            if(num + numOfExtinguishers > maxNumOfExtinguishers)
                throw new IllegalArgumentException("maximum number of extinguishers is 5");
            numOfExtinguishers += num;
            timeToPutOutFire = 900 / (numOfExtinguishers * 60);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public void decreaseNumOfExtinguishers(int num){
        try{
            if(numOfExtinguishers - num < 1)
                throw new IllegalArgumentException("min number of extinguishers is 1");
            numOfExtinguishers -= num;
            timeToPutOutFire = 900 / (numOfExtinguishers * 60);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }


    public int getNumOfExtinguishers() {
        return numOfExtinguishers;
    }


    public int getMaxNumOfExtinguishers() {
        return maxNumOfExtinguishers;
    }

    public double getTimeToPutOutFire() {
        return timeToPutOutFire;
    }
    public String toString(){
        return super.toString() +
                "Number of extinguishers: " + this.numOfExtinguishers + "\n\t"+
                "Max number of extinguishers: " + this.maxNumOfExtinguishers + "\n\t"+
                "To put out the fire: " + this.timeToPutOutFire + " min";
    }
}
