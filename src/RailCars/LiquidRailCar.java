package RailCars;

import RailCars.BasicFreightRailCar;

public class LiquidRailCar extends BasicFreightRailCar {

    private double volume;      // 'm' to the 3rd power
    private double temperature;
    private final double maxTemperature = 99;
    private final double minTemperature = -10;

    public LiquidRailCar(String shipper, String securityInf, double netWeight, double cargoWeight, double volume,
                         double temperature){
        super(shipper, securityInf, netWeight, cargoWeight, "Railcar with Liquids");
        try {
            if(temperature > maxTemperature || temperature < minTemperature)
                throw new IllegalArgumentException("max temp is 99 and min -10 degrees");
            this.temperature = temperature;
            this.volume = volume;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    public void increaseTemp(double deg){
        try {
            if(deg + temperature > maxTemperature)
                throw new IllegalArgumentException("max temp is 99 degrees");
            temperature += deg;
            volume += (deg * 0.7);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
    public void decreaseTemp(double deg){
        try{
            if(temperature - deg < minTemperature)
                throw new IllegalArgumentException("min temp is -10 degrees");
            temperature -= deg;
            volume -= (deg * 0.7);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    public double getVolume() {
        return volume;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public String toString(){
        return super.toString() +
                "Volume: " + this.volume + "m^3" + "\n\t"+
                "Max temperature: " + maxTemperature + "\n\t"+
                "Min temperature: " + minTemperature;
    }

}

