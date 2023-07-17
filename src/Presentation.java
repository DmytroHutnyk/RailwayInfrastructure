import RailCars.*;

import java.util.ArrayList;
import java.util.List;

public class Presentation {
    public static void main(String[] args) {




        ResturantRailCar rest1 = new ResturantRailCar("shipper", "security", 300, 3333.0,12, 5,true);

//        System.out.println(rest1.getEatenMeals());
//        System.out.println(rest1.getNumOfMeals());
//        rest1.serveMeals(3);
//        System.out.println(rest1.getEatenMeals());
//        System.out.println(rest1.getEatenMeals());
//
//        System.out.println(rest1.getNumOfMeals());
//        rest1.cookMeal(5);
//        System.out.println(rest1.getNumOfMeals());
//        System.out.println(rest1);

        PostOfficeRailCar post1 = new PostOfficeRailCar("someone", "secured", 444, 333, 44, 11, 500);

//        System.out.println(post1.getNumOfComplaints());
//        post1.writeComplaint(4);
//        System.out.println(post1.getNumOfComplaints());
//
//        System.out.println(post1.getNumOfReadLet());
//        System.out.println(post1.getNumOfUnreadLetters());
//        post1.readLetter(5);
//        System.out.println(post1.getNumOfReadLet());
//        System.out.println(post1.getNumOfUnreadLetters());
//        System.out.println(post1);
 //

        PassengerRailCar pas1 = new PassengerRailCar("shipper", "secured", 4444, 44, true);
        PassengerRailCar pas2 = new PassengerRailCar("shipper", "security", 1.25, 30,false, 12); // exception

//        System.out.println(pas2.getNumOfPassengers());
//        pas2.setNumOfPassengers(44);
//        System.out.println(pas2.getNumOfPassengers());
//
//        System.out.println(pas1.isToiletAvailability());
//        pas1.setToiletAvailability(false);
//        System.out.println(pas1.isToiletAvailability());
//        System.out.println(pas2);

        BaggageMailRailCar bag1 = new BaggageMailRailCar("shipper", "insecured", 444, 55, 44);
//        System.out.println(bag1.getBagWeight());
//        System.out.println(bag1.getNumOfBags());
//        bag1.addBag(33);
//        System.out.println(bag1.getNumOfBags());
//        bag1.removeBag(3);
//        System.out.println(bag1.getNumOfBags());
//        System.out.println(bag1);


        BasicFreightRailCar base = new BasicFreightRailCar("shipper", "secured", 44, 55, "BasicFreightRailCar" );
      //  System.out.println(base.getMaxCargoWeight());

//        System.out.println(base.getCargoWeight());
//        System.out.println(base.getPercentOfFreeSpace());
//        base.incrementCargoWeight(40000);
//        System.out.println(base.getCargoWeight());
//        System.out.println(base.getPercentOfFreeSpace());
//        base.reduceCargoWeight(10000);
//        System.out.println(base.getCargoWeight());
//        System.out.println(base);

        HeavyFreightCar hev = new HeavyFreightCar("shipper" ,"secured", 4000, 44444, "HeavyFreightCar");

     //   System.out.println(hev.getMaxCargoWeight());

//        System.out.println(hev.getCargoWeight());
//        System.out.println(hev.getPercentOfOccupiedSpace());
//        hev.incrementCargoWeight(4000000);
//        System.out.println(hev.getCargoWeight());
//        System.out.println(hev.getPercentOfOccupiedSpace());
//        hev.reduceCargoWeight(10000);
//        System.out.println(hev.getCargoWeight());
//        System.out.println(hev);


        RefrigeratedRailCar ref = new RefrigeratedRailCar("shipper", "secured", 444, 444, 55);
      //   extends BasicFreightRailCar
//        System.out.println(ref.getTemperature());
//        ref.decreaseTemp(44);
//        System.out.println(ref.getTemperature());
//        ref.increaseTemp(33);
//        System.out.println(ref.getTemperature());
//        ref.decreaseTemp(444);
//        System.out.println(ref.getTemperature());
//        System.out.println(ref);

        LiquidRailCar liq = new LiquidRailCar("shipper", "insecured", 444, 555, 66, 90);
        // extends BasicFreightRailCar
//        System.out.println(liq.getTemperature());
//        System.out.println(liq.getVolume());
//        liq.decreaseTemp(44);
//        System.out.println(liq.getTemperature());
//        System.out.println(liq.getVolume());
//        liq.increaseTemp(55);
//        System.out.println(liq.getTemperature());
//        System.out.println(liq.getVolume());
//        System.out.println(liq);

        GaseousRailCar gas = new GaseousRailCar("shipper", "secured", 444, 5445, 400);
//        System.out.println(gas.getMaxPressure());
//        System.out.println(gas.getPressure());
//        gas.decreasePressure(450);
//        System.out.println(gas.getPressure());
//        gas.increasePressure(6000);
//        System.out.println(gas.getPressure());
//        System.out.println(gas);


       ExplosiveRailCar ex = new ExplosiveRailCar("shipper", "secured", 444, 555, 4);
//        System.out.println(ex.getMaxNumOfExtinguishers());
//        System.out.println(ex.getNumOfExtinguishers());
//        System.out.println(ex.getTimeToPutOutFire());
//        ex.decreaseNumOfExtinguishers(3);
//        System.out.println(ex.getTimeToPutOutFire());
//        ex.increaseNumOfExtinguishers(44);
//        System.out.println(ex);





        ToxicRailCar tox = new ToxicRailCar("secured", " insecure", 444, 55, 6);
//        System.out.println(tox.getMaxSafetyLevel());
//        System.out.println(tox.getMinSafetyLevel());
//        System.out.println(tox.getSafetyLevel());
//        tox.decreaseSafety(4);
//        tox.increaseSafety(2);
//        System.out.println(tox.getSafetyLevel());
//        System.out.println(tox);

        LiquidToxicRailCar l = new LiquidToxicRailCar("shipper", " secured" , 55, 555, 66 ,33);
//        System.out.println(l.getMaxTemperature());
//        System.out.println(l.getMinTemperature());
//        System.out.println(l.getTemperature());
//        l.decreaseTemp(38);
//        System.out.println(l.getTemperature());
//        l.increaseTemp(60);
//        System.out.println(l.getTemperature());
//        l.decreaseTemp(1000);
//        l.increaseTemp(6000);
//        System.out.println(l);

//----------------------------------------------------------------------------------------------------------------------


        RailMap railMap = RailMap.createMap();
        RailwayStation r1 = new RailwayStation("Berlin");
        RailwayStation r2 = new RailwayStation("Kyiv");
        RailwayStation r3 = new RailwayStation("Lviv");
        RailwayStation r4 = new RailwayStation("Warsaw");
        RailwayStation r5 = new RailwayStation("Dublin");
        RailwayStation r6 = new RailwayStation("Paris");

        RailwayStation r7 = new RailwayStation("London");
        railMap.addStation(r7);
        railMap.deleteStation("London");


        railMap.addStation(r1);
        railMap.addStation(r2);
        railMap.addStation(r3);
        railMap.addStation(r4);
        railMap.addStation(r5);
        railMap.addStation(r6);
        railMap.addConnection("Berlin", "Kyiv", 1100);
        railMap.addConnection("Lviv", "Kyiv", 200);
        railMap.addConnection("Lviv", "Warsaw", 200);
        railMap.addConnection("Warsaw", "Dublin", 500);
        railMap.addConnection("Lviv", "Dublin", 100);
        railMap.addConnection("Paris", "Lviv", 600);
        railMap.addConnection("Paris", "Dublin", 1000);
        railMap.addConnection("Berlin", "Paris", 300);

        railMap.removeConnection("Berlin", "Paris");

//        List<RailwayStation> route = RailMap.findRoute("Berlin", "Warsaw");
//        System.out.println("route is: ");
//        for(RailwayStation station : route){
//            System.out.println(station);
//        }
//
//        double distance = RailMap.findRouteDistance("Berlin", "Warsaw");
//        System.out.println("distance is: " + distance);







        Locomotive l1 = new Locomotive(333, 333323, 200, "railCar", 150, r4, r6, r1);
        TrainSet trainSet = new TrainSet(l1);
        trainSet.addRailCar(tox);
        trainSet.addRailCar(ex);
        trainSet.addRailCar(pas1);
//        ArrayList<Object> a = trainSet.getTrainSet();
//        for(int i = 0; i < a.size(); i++){
//            System.out.println(a.get(i));
//        }
//        System.out.println("---------------------------Remove PassengerRalCar---------------------------");
//        trainSet.removeRailRoad(3);
//        a = trainSet.getTrainSet();
//        for(int i = 0; i < a.size(); i++){
//            System.out.println(a.get(i));
//        }
//        System.out.println("-----------------------------RailRoadCar deleted-----------------------------");




        Thread thread = new Thread(trainSet);
        thread.start();



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(trainSet);
    }
}