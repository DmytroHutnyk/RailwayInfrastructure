public class RailroadHazardException extends Exception{
    public RailroadHazardException(TrainSet trainSet) {
        super(trainSet.toString());
    }
}
