package RailCars;

import RailCars.RailroadCar;

public class ResturantRailCar extends RailroadCar {
    private int numOfMeals;
    private int eatenMeals;
    private boolean vegetarianMenu;
    private final boolean electGrid = true;
    private double grossWeight;
    public ResturantRailCar(String shipper, String securityInf, double netWeight, double grossWeight,int numOfMeals,
                            int eatenMeals, boolean vegetarianMenu){
      super(shipper, securityInf, netWeight, "Railroad Restaurant");
      this.numOfMeals = numOfMeals;
      this.grossWeight = grossWeight;
      this.vegetarianMenu = vegetarianMenu;
      this.eatenMeals = eatenMeals;
    }


    public void serveMeals(int num){
        eatenMeals += num;
        numOfMeals -= num;
    }

    public void cookMeal(int num){
        numOfMeals += num;
    }

    public int getEatenMeals() {
        return eatenMeals;
    }

    public void setEatenMeals(int eatenMeals) {
        this.eatenMeals = eatenMeals;
    }

    public int getNumOfMeals() {
        return numOfMeals;
    }

    public void setNumOfMeals(int numOfMeals) {
        this.numOfMeals = numOfMeals;
    }

    public boolean isVegetarianMenu() {
        return vegetarianMenu;
    }

    public void setVegetarianMenu(boolean vegetarianMenu) {
        this.vegetarianMenu = vegetarianMenu;
    }

    public boolean isElectGrid() {
        return electGrid;
    }



    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String toString(){
        return super.toString() +
                "Gross weight: " + this.grossWeight + "\n\t"+
                "Number of meals: "  + this.numOfMeals + "\n\t"+
                "Number of eaten meals: " + this.eatenMeals + "\n\t"+
                "Vegetarian menu available: " + this.vegetarianMenu;
    }
}
