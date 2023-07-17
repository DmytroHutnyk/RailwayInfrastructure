package RailCars;

public class BasicFreightRailCar extends RailroadCar {
    private double grossWeight;
    private final double maxCargoWeight = 75000;
    private double cargoWeight;
    protected boolean electGrid = false;
    private double percentOfFreeSpace;

    public BasicFreightRailCar(String shipper, String securityInf, double netWeight, double cargoWeight, String type){
        super(shipper, securityInf, netWeight, type);

        try {
            if(cargoWeight > maxCargoWeight || cargoWeight < 0)
                throw new IllegalArgumentException("max cargo weight is 75000");
            this.grossWeight = netWeight + cargoWeight;
            this.cargoWeight = cargoWeight;
            this.percentOfFreeSpace = 100 - cargoWeight*100/maxCargoWeight;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    private void calcPercentOfFreeSpace(){
        percentOfFreeSpace = 100 - cargoWeight*100/maxCargoWeight;
    }
    public void reduceCargoWeight(int kg){
        try {
            if(cargoWeight - kg < 0) throw new IllegalArgumentException("cargo weight cant be < 0");
            cargoWeight -= kg;
            recalculateGrossWeight();
            calcPercentOfFreeSpace();
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
            calcPercentOfFreeSpace();
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }


    }

    public double getPercentOfFreeSpace() {
        return percentOfFreeSpace;
    }

    private void recalculateGrossWeight(){
        grossWeight = getNetWeight() + cargoWeight;
    }


    public double getCargoWeight() {
        return cargoWeight;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public String toString(){
        return super.toString() +
                "Gross weight: " + this.grossWeight + "\n\t"+
                "Cargo weight: " + this.cargoWeight + "\n\t"+
                "Max cargo weight: " + this.maxCargoWeight + "\n\t"+
                "Available space: " + this.percentOfFreeSpace + "%" + "\n\t"+
                "Electrical grid: " + this.electGrid;
    }
}
