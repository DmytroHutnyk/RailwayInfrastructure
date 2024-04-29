import java.util.ArrayList;

public class Locomotive{
    private static ArrayList<Locomotive> locomotives = new ArrayList<>();
    private boolean isGoingBrrrrrr = false;
    private int numOfRailCars;
    private double maxWeight;
    private int maxElectRailCars;
    private String name;
    private int id = 0;
    private static int counter = 1;
    private double speed;
    private RailwayStation homeRalStation;
    private RailwayStation startingRalStation;
    private RailwayStation destinationRalStation;
    private volatile RailwayStation station;
    private TrainSet itsTrainset = null;
    private volatile double fullConnectionsDistancesGone = 0;
    private volatile double gonePartOfCurrentConnection = 0;
    private volatile double  percentageOfCurrentConnection = 0;





    public Locomotive(int numOfRailCars, double maxWeight, int maxElectRailCars, String name, double speed,
                      RailwayStation homeRalStation, RailwayStation startingRalStation,
                      RailwayStation destinationRalStation){
        this.numOfRailCars = numOfRailCars;
        this.maxWeight = maxWeight;
        this.maxElectRailCars = maxElectRailCars;
        this.name = name;
        this.speed = speed;
        this.homeRalStation = homeRalStation;
        this.startingRalStation = startingRalStation;
        station = startingRalStation;
        this.destinationRalStation = destinationRalStation;
        this.id = counter;
        this.isGoingBrrrrrr = true;
        locomotives.add(this);
        counter++;
    }

    public void setItsTrainset(TrainSet itsTrainset) {
        this.itsTrainset = itsTrainset;
    }

    public boolean isGoing() {
        return isGoingBrrrrrr;
    }

    public static ArrayList<Locomotive> getLocomotives() {
        return locomotives;
    }

    public int getNumOfRailCars() {
        return numOfRailCars;
    }

    public int getId() {
        return id;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public synchronized double getSpeed() {
        return speed;
    }

    public int getMaxElectRailCars() {
        return maxElectRailCars;
    }

    public String getName() {
        return name;
    }

    public RailwayStation getHomeRalStation() {
        return homeRalStation;
    }

    public RailwayStation getStartingRalStation() {
        return startingRalStation;
    }

    public RailwayStation getDestinationRalStation() {
        return destinationRalStation;
    }

    public void setNumOfRailCars(int numOfRailCars) {
        this.numOfRailCars = numOfRailCars;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxElectRailCars(int maxElectRailCars) {
        this.maxElectRailCars = maxElectRailCars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHomeRalStation(RailwayStation homeRalStation) {
        this.homeRalStation = homeRalStation;
    }

    public synchronized void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setStartingRalStation(RailwayStation startingRalStation) {
        this.startingRalStation = startingRalStation;
    }

    public void setDestinationRalStation(RailwayStation destinationRalStation) {
        this.destinationRalStation = destinationRalStation;
    }

    public synchronized double getPercentageOfCurrentConnection() {         // why synchronized?
        return percentageOfCurrentConnection;
    }

    public synchronized  void setPercentageOfCurrentConnection(double percentageOfCurrentConnection) {
        this.percentageOfCurrentConnection = percentageOfCurrentConnection;
    }

    public void stop(){
        isGoingBrrrrrr = false;
    }

    public synchronized double getFullConnectionsDistancesGone() {
        return fullConnectionsDistancesGone;
    }

    public synchronized void setFullConnectionsDistancesGone(double fullConnectionsDistancesGone) {
        this.fullConnectionsDistancesGone = fullConnectionsDistancesGone;
    }

    public synchronized double getGonePartOfCurrentConnection() {
        return gonePartOfCurrentConnection;
    }

    public synchronized void setGonePartOfCurrentConnection(double gonePartOfCurrentConnection) {
        this.gonePartOfCurrentConnection = gonePartOfCurrentConnection;
    }
    public synchronized RailwayStation getStation() {
        return station;
    }

    public synchronized void setStation(RailwayStation station) {
        this.station = station;
    }

    // here we change speed every 1 sec, randomly by 3%
    public synchronized void changeSpeed(){
        Thread speedThread = new Thread(() -> {
            while (isGoingBrrrrrr && Main.appIsAlive){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    break;
                }

                double randomCof = Math.random() * 0.06 - 0.03;
                speed = speed + speed * randomCof;
                try {
                    if (speed > 200)
                        throw new RailroadHazardException(this.itsTrainset);
                }catch (RailroadHazardException e) {
                    isGoingBrrrrrr = false;
                    System.err.println(e.getMessage());
                    System.out.println("This trainset has stopped");
                }


            }
        });
        speedThread.start();
    }


    @Override
    public String toString() {
        return  "  Locomotive :\n" +
                "\tID number: " + id +
                "\tName: " + name + '\n' +
                "\tNumber of Railcars: " + numOfRailCars +"\n"+
                "\tMaximum weight: " + maxWeight +"\n"+
                "\tMaximum of Electric Railcars: " + maxElectRailCars +"\n"+
                "\tSpeed at current moment: " + speed +"\n"+
                "\tHome railway station: " + homeRalStation +"\n"+
                "\tStarting railway station: " + startingRalStation +"\n"+
                "\tDestination railway station: " + destinationRalStation;
    }
}
