import java.util.ArrayList;

public class RailwayStation {
    private String name;
    public RailwayStation(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
