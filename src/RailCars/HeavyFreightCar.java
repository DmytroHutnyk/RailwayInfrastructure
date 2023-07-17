package RailCars;

import RailCars.RailroadCar;

public class HeavyFreightCar extends RailroadCar {
    private double grossWeight;
    private final double maxCargoWeight = 90000;
    private double cargoWeight;
    private final boolean electGrid = false;
    private double percentOfOccupiedSpace;

    public HeavyFreightCar(String shipper, String securityInf, double netWeight, double cargoWeight, String type){
        super(shipper, securityInf, netWeight, type);
        try {
            if(cargoWeight > maxCargoWeight || cargoWeight < 0)
                throw new IllegalArgumentException("max cargo weight is 90000 and min is 0");
            this.cargoWeight = cargoWeight;
            this.grossWeight = netWeight + cargoWeight;
            this.percentOfOccupiedSpace = cargoWeight*100/maxCargoWeight;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }


    }

    public void calcPercentOfOccupiedSpace(){
        percentOfOccupiedSpace = cargoWeight*100/maxCargoWeight;
    }
    public void reduceCargoWeight(int kg){
        try {
            if(cargoWeight - kg < 0)
                throw new IllegalArgumentException("cargo weight cant be < 0");
            cargoWeight -= kg;
            recalculateGrossWeight();
            calcPercentOfOccupiedSpace();
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }
    public void incrementCargoWeight(int kg){
        try {
            if(kg + cargoWeight > maxCargoWeight)
                throw new IllegalArgumentException("max cargo weight is 75000");
            cargoWeight += kg;
            recalculateGrossWeight();
            calcPercentOfOccupiedSpace();
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public double getPercentOfOccupiedSpace() {
        return percentOfOccupiedSpace;
    }

    private void recalculateGrossWeight(){
        grossWeight = getNetWeight() + cargoWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public boolean isElectGrid() {
        return electGrid;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public String toString(){
        return super.toString() +
                "Gross weight: " + this.grossWeight +"\n\t"+
                "Cargo weight: " + this.cargoWeight +"\n\t"+
                "Max cargo weight: " + this.maxCargoWeight +"\n\t"+
                "Occupied space: " + this.percentOfOccupiedSpace + "%" +"\n\t"+
                "Electrical grid: " + this.electGrid;
    }
}

