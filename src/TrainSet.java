import RailCars.PassengerRailCar;
import RailCars.RailroadCar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TrainSet implements Runnable {
    public boolean isAlive = true;
    private static List<TrainSet> allTrainSets = new ArrayList<>();                 // list of all trainSets
    private ArrayList<Object> trainSet;
    private RailMap map = RailMap.createMap();
    private int numOfElectCars = 0;
    private double loadWeight = 0;
    private int passengerCounter = 0;
    private final int id;
    private static int counter;
    private List<RailwayStation> route;
    private double routeLength = -1;


    // locomotive will be always 1(0) element of trainset
    public TrainSet(Locomotive locomotive){
        trainSet = new ArrayList<>();
        trainSet.add(locomotive);
        locomotive.setItsTrainset(this);
        this.id = counter;
        ++counter;
        allTrainSets.add(this);
        allTrainSets.sort(Comparator.comparingDouble(TrainSet::getRouteLength).reversed());
        route = RailMap.findRoute(locomotive.getStartingRalStation().getName(), locomotive.getDestinationRalStation().getName());
        routeLength = RailMap.findRouteDistance(locomotive.getStartingRalStation().getName(),
                locomotive.getDestinationRalStation().getName());

    }

    public static List<TrainSet> getAllTrainSets() {
        return allTrainSets;
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public ArrayList<Object> getTrainSet() {
        return trainSet;
    }

    public void addRailCar(RailroadCar railroadCar){

        Object locomotive = trainSet.get(0);

        if(this.numOfElectCars  >= ((Locomotive) locomotive).getMaxElectRailCars()){
            throw new IllegalArgumentException("max number of electrical cars is: " +
                    ((Locomotive) locomotive).getMaxElectRailCars());
        } else if(this.loadWeight + railroadCar.getGrossWeight() > ((Locomotive) locomotive).getMaxWeight()){
            throw new IllegalArgumentException("mac weight is: " + ((Locomotive) locomotive).getMaxWeight());
        } else{
            trainSet.add(railroadCar);
            if(railroadCar.isElectGrid())
                this.numOfElectCars += 1;
            if(railroadCar.getSecurityInf().equals("Passenger Railcar"))
                passengerCounter+=((PassengerRailCar)railroadCar).getNumOfPassengers();
            this.loadWeight += railroadCar.getGrossWeight();

        }



    }

    public void removeRailRoad(int id){
        for(int i = 1; i < trainSet.size(); i++){
            Object railCar = trainSet.get(i);

            if(((RailroadCar) railCar).getId() == id){
                this.loadWeight -= ((RailroadCar) railCar).getGrossWeight();
                if(((RailroadCar) railCar).isElectGrid())
                    this.numOfElectCars -= 1;
                trainSet.remove(i);
            }
        }
    }


    //  here we simulate moving of trainSet along the route

    public void run(){
        Locomotive l1 =  (Locomotive) (trainSet.get(0));
        l1.changeSpeed();                                   // change speed
        RailwayStation stationNext = null;
        RailwayStation station = null;
        // here we travel through all stations
        for(int i = 0; i< route.size()-1 && Main.appIsAlive && isAlive; i++) {
            stationNext = travelToNextStation(l1, route, i);
        }
        station = stationNext;
        stationNext = null;

        ((Locomotive)trainSet.get(0)).setGonePartOfCurrentConnection(0);
        ((Locomotive)trainSet.get(0)).setPercentageOfCurrentConnection(0);
        ((Locomotive)trainSet.get(0)).setFullConnectionsDistancesGone(0);
        // here we wait on the end of route 30 sec and go backwards
        int t = 0;
        while (Main.appIsAlive && t<30 && isAlive)
            try {
                Thread.sleep(1000);
                t++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        // here we determine new route to get back
        route = RailMap.findRoute(l1.getDestinationRalStation().getName(), l1.getStartingRalStation().getName());
        for(int i = 1; i< route.size()-1 && Main.appIsAlive && isAlive; i++) {
            stationNext = travelToNextStation(l1, route, i);
        }

        ((Locomotive)trainSet.get(0)).setGonePartOfCurrentConnection(0);
        ((Locomotive)trainSet.get(0)).setPercentageOfCurrentConnection(0);
        ((Locomotive)trainSet.get(0)).setFullConnectionsDistancesGone(RailMap.findRouteDistance(l1.getDestinationRalStation().getName(), l1.getStartingRalStation().getName()));
        station = stationNext;
        l1.stop();

    }
    // here we travel from one station to another
    private RailwayStation travelToNextStation(Locomotive l1, List<RailwayStation> route, int i) {
        RailwayStation station;
        RailwayStation stationNext;
        station = route.get(i);
        stationNext = route.get(i+1);
        l1.setStation(station);
        try {                        // here we wait 2 sec on each station
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Connection> aConnections = map.getMap().get(station);         // here we get list of all connections to current station

        // check if there is such connections between two stations. e.g. Warsaw - Lviv or Lviv - Warsaw
        for (Connection connection : aConnections) {
            if ((connection.getStation1().equals(station) && connection.getStation2().equals(stationNext)) ||
                    (connection.getStation1().equals(stationNext) && connection.getStation2().equals(station))) {
                //travel on this station
                connection.travel(l1);
            }
        }
        return stationNext;
    }

    @Override
    public String toString() {
        StringBuilder toStringTrainset = new StringBuilder();
        double partOfWholeRoute = ((Locomotive)trainSet.get(0)).getFullConnectionsDistancesGone()+((Locomotive)trainSet.get(0)).getGonePartOfCurrentConnection();
        double percentage = partOfWholeRoute/routeLength*100;
        if(percentage > 100)
            percentage = 0;
        double percentageOfConnection = ((Locomotive)trainSet.get(0)).getPercentageOfCurrentConnection();
        List<RailroadCar> a = new ArrayList<>();
        for (int i = 1; i < trainSet.size(); i++) {
            a.add((RailroadCar)trainSet.get(i));
        }
        // here we sort in descending order
        a.sort(Comparator.comparingDouble(RailroadCar::getGrossWeight));
        toStringTrainset.append(trainSet.get(0)).append("\n  ");
        for (Object o: a)
            toStringTrainset.append(o).append("\n  ");
        toStringTrainset.replace(toStringTrainset.length()-3, toStringTrainset.length()-1, "");
        return "\nTrainSet:\n" +
                " ID number: " + id +"\n"+
                " Length of route: "+routeLength+"\n"+
                " Number of electric railcars: " + numOfElectCars +"\n"+
                " Load of weight: " + loadWeight +"\n"+
                " Number of Passengers: "+passengerCounter+"\n"+
                " Part of route completed: "+percentage+"%\n"+
                (percentageOfConnection == 0? " Train is currently staying on station: "+ ((Locomotive)trainSet.get(0)).getStation().getName():" Part of route completed to the nearest next station: "+ percentageOfConnection+"%")
                +"\n"+
                " Consists of:\n" + toStringTrainset+"\n";
    }

    public String status(){
        double partOfWholeRoute = ((Locomotive)trainSet.get(0)).getFullConnectionsDistancesGone()+((Locomotive)trainSet.get(0)).getGonePartOfCurrentConnection();
        double percentage = partOfWholeRoute/routeLength*100;
        if(percentage > 100)
            percentage = 0;
        double percentageOfConnection = ((Locomotive)trainSet.get(0)).getPercentageOfCurrentConnection();
        return "\nTrainSet:\n" +
                " ID number: " + id +"\n"+
                " Length of route: "+ this.routeLength+"\n"+
                " Number of electric railcars: " + numOfElectCars +"\n"+
                " Load of weight: " + loadWeight +"\n"+
                " Number of Passengers: "+passengerCounter+"\n"+
                " Part of route completed: "+percentage+"%\n"+
                (percentageOfConnection == 0? " Train is currently staying on station: "+ ((Locomotive)trainSet.get(0)).getStation().getName():" Part of route completed to the nearest next station: "+ percentageOfConnection+"%")
                +"\n";
    }

}
