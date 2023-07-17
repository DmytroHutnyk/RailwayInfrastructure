package RailCars;

import RailCars.HeavyFreightCar;

public class LiquidToxicRailCar extends HeavyFreightCar {

    private double volume;      // 'm' to the 3rd power
    private double temperature;
    private final double maxTemperature = 99;
    private final double minTemperature = -10;

    public LiquidToxicRailCar(String shipper, String securityInf, double netWeight, double cargoWeight, double volume,
                         double temperature){
        super(shipper, securityInf, netWeight, cargoWeight, "RailCar with Toxic Liquids");
        try {
            if(temperature > maxTemperature || temperature < minTemperature)
                throw new IllegalArgumentException("max temp is 99 and min -10 degrees");
            this.temperature = temperature;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

        this.volume = volume;
    }

    public void increaseTemp(double deg){
        try {
            if(deg + temperature > maxTemperature)
                throw new IllegalArgumentException("max temp is 99 degrees");
            temperature += deg;
            volume += (deg * 0.7);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }
    public void decreaseTemp(double deg){
        try {
            if(temperature - deg < minTemperature)
                throw new IllegalArgumentException("min temp is -10 degrees");
            temperature -= deg;
            volume -= (deg * 0.7);
        }catch (IllegalArgumentException e) {
            System.out.println(e);
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
                "Volume: " + this.volume + "m^3" + "\n\t" +
                "Max temperature: " + maxTemperature + "\n\t" +
                "Min temperature: " + minTemperature;
    }
}
