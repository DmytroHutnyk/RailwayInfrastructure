import java.util.concurrent.Semaphore;
public class Connection {
    private RailwayStation station1;
    private RailwayStation station2;
    private double distance;
    private double distanceToGO;
    private Semaphore semaphore = new Semaphore(1);

    public Connection(RailwayStation station1, RailwayStation station2, double distance){
        this.station1 = station1;
        this.station2 = station2;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public RailwayStation getStation1() {
        return station1;
    }

    public RailwayStation getStation2() {
        return station2;
    }


    public synchronized void travel(Locomotive locomotive) {
        int timeToWait = 0;
        distanceToGO = distance;
        while (distanceToGO > 0 && locomotive.isGoing() && Controller.appIsAlive){
            double speed = locomotive.getSpeed();
            if(distanceToGO >= speed) {
                distanceToGO -= speed;
                locomotive.setGonePartOfCurrentConnection(locomotive.getGonePartOfCurrentConnection()+speed);
                locomotive.setPercentageOfCurrentConnection(locomotive.getGonePartOfCurrentConnection()/this.distance*100);
                timeToWait = 1000;
            }
            else {
                timeToWait = (int) (distanceToGO / locomotive.getSpeed());
                distanceToGO = 0;
                locomotive.setGonePartOfCurrentConnection(0);
                locomotive.setPercentageOfCurrentConnection(0);
                locomotive.setFullConnectionsDistancesGone(locomotive.getFullConnectionsDistancesGone()+distance);
            }
            try {
                Thread.sleep(timeToWait);
            }catch (Exception e){
                System.err.println("Travel interrupted: " + e.getMessage());
            }
        }
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            System.err.println("Travel interrupted: " + e.getMessage());
        }
        finally {
            locomotive.setFullConnectionsDistancesGone(Math.round(locomotive.getFullConnectionsDistancesGone()));
            distanceToGO = distance;
            semaphore.release();
        }
    }
}
