package RailCars;

import RailCars.BasicFreightRailCar;

public class GaseousRailCar extends BasicFreightRailCar {

    private boolean electGrid = false;

    private double pressure;  // PSI
    private final double maxPressure = 500;   // PSI
    public GaseousRailCar(String shipper, String securityInf, double netWeight, double cargoWeight, double pressure){
        super(shipper, securityInf, netWeight, cargoWeight, "Railcar with Gases");
        try{
            if(pressure > maxPressure)
                throw new IllegalArgumentException("max pressure is 500");
            this.pressure = pressure;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public void increasePressure(int psi){
        try {
            if(psi + pressure > maxPressure)
                throw new IllegalArgumentException("max pressure is 500");
            pressure += psi;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public void decreasePressure(int psi){
        pressure -= psi;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        try {
            if(pressure > maxPressure)
                throw new IllegalArgumentException("max pressure is 500");
            this.pressure = pressure;
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    public double getMaxPressure() {
        return maxPressure;
    }

    public String toString(){
        return super.toString() +
                "Pressure is: " + this.pressure + "psi" + "\n\t"+
                "Max pressure is: " + this.maxPressure + "psi";
    }
}
