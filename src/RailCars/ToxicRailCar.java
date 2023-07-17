package RailCars;

import RailCars.HeavyFreightCar;

public class ToxicRailCar extends HeavyFreightCar {

    private int safetyLevel = 0;
    private final int maxSafetyLevel = 7;
    private final int minSafetyLevel = 1;

    public ToxicRailCar(String shipper, String securityInf, double netWeight, double cargoWeight, int safetyLevel) {
        super(shipper, securityInf, netWeight, cargoWeight, "Railcar with Toxic Materials");
        try {
            if(safetyLevel > maxSafetyLevel || safetyLevel < minSafetyLevel)
                throw new IllegalArgumentException("min safety level is 1 and max is 7");
            else if(safetyLevel > 1){
                super.incrementCargoWeight(500 * safetyLevel);
            }
            this.safetyLevel = safetyLevel;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public void increaseSafety(int num){
        try {
            if(safetyLevel + num > maxSafetyLevel)
                throw new IllegalArgumentException("max safety level is 7");
            super.incrementCargoWeight(num * 500);
            safetyLevel += num;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public void decreaseSafety(int num){
        try {
            if(safetyLevel - num < minSafetyLevel)
                throw new IllegalArgumentException("min safety level is 1");
            super.reduceCargoWeight(num * 500);
            safetyLevel -= num;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public int getMinSafetyLevel() {
        return minSafetyLevel;
    }

    public int getMaxSafetyLevel() {
        return maxSafetyLevel;
    }

    public int getSafetyLevel() {
        return safetyLevel;
    }

    public String toString(){
        return super.toString() +
                "Safety level: " + this.safetyLevel + "\n\t"+
                "Max safety level: " + this.maxSafetyLevel + "\n\t"+
                "Min safety level" + this.minSafetyLevel;
    }
}