package RailCars;

import RailCars.BasicFreightRailCar;

public class RefrigeratedRailCar extends BasicFreightRailCar {

    private double temperature;
    private final double minTemp = -75;

    public RefrigeratedRailCar(String shipper, String securityInf, double netWeight, double cargoWeight,
                               double temperature){
        super(shipper, securityInf, netWeight, cargoWeight, "Refrigerated Railcar");
        super.electGrid = true;
        try {
            if(temperature < minTemp)
                throw new IllegalArgumentException("minimal temperature is -75");
            this.temperature = temperature;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }


    public double getTemperature() {
        return temperature;
    }

    public void increaseTemp(double temp){
        this.temperature += temp;
    }

    public void decreaseTemp(double temp){
        try {
            if(this.temperature - temp < minTemp)
                throw new IllegalArgumentException("minimal temperature is -75");
            this.temperature -= temp;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public String toString(){
        return super.toString() + " temperature: " + this.temperature + "\n\t"
                + "min temperature: " + this.minTemp;
    }
}
