import java.util.*;

public class RailMap {

    private static RailMap ourMap = null;
    public static Map<RailwayStation, ArrayList<Connection>> map;


    public Map<RailwayStation, ArrayList<Connection>> getMap(){
        return map;
    }
    // singleton class to have only one map
    public static RailMap createMap(){
        if( ourMap == null )
            ourMap = new RailMap();
        return ourMap;
    }
    private RailMap(){
        map = new HashMap<>();
    }

    public void addStation(RailwayStation railwayStation){
        map.put(railwayStation, new ArrayList<>());
    }


    public void addConnection(String stationName1, String stationName2, double distance){
        RailwayStation station1 = null;
        RailwayStation station2 = null;
        for (RailwayStation station : map.keySet()) {
            if (station.getName().equals(stationName1)) {
                station1 = station;
            } else if (station.getName().equals(stationName2)) {
                station2 = station;
            }
        }

        // check if both stations were found
        if (station1 == null || station2 == null) {
            throw new IllegalArgumentException("One or both stations not found in the map");
        }

        Connection connection = new Connection(station1, station2, distance);

        //fist station
        ArrayList<Connection> connections1 = map.get(station1);
        connections1.add(connection);
        map.put(station1, connections1);

        // second station
        ArrayList<Connection> connections2 = map.get(station2);
        connections2.add(connection);
        map.put(station2, connections2);
    }
// --------------------------------------------------------------------------------------how this works??????????
    public static List<RailwayStation> findRoute(String startName, String endName) {
        RailwayStation start = null;
        RailwayStation end = null;
        Set<RailwayStation> a = map.keySet();
        for (RailwayStation station : a) {
            if (station.getName().equals(startName)) {
                start = station;
            } else if (station.getName().equals(endName)) {
                end = station;
            }
        }

        // check if both stations were found
        if (start == null || end == null) {
            throw new IllegalArgumentException("One or both stations not found in the map");
        }

        Map<RailwayStation, Double> distances = new HashMap<>();
        Map<RailwayStation, RailwayStation> predecessors = new HashMap<>();
        Set<RailwayStation> unvisited = new HashSet<>();
        distances.put(start, 0.0);
        unvisited.add(start);

        while (!unvisited.isEmpty()) {
            RailwayStation current = getClosestStation(unvisited, distances);
            unvisited.remove(current);

            if (current.equals(end)) {
                return buildPath(predecessors, current);
            }

            for (Connection connection : map.get(current)) {
                RailwayStation next = (connection.getStation1().equals(current)) ? connection.getStation2() :
                        connection.getStation1();
                double newDistance = distances.get(current) + connection.getDistance();
                if (!distances.containsKey(next) || newDistance < distances.get(next)) {
                    distances.put(next, newDistance);
                    predecessors.put(next, current);
                    unvisited.add(next);
                }
            }
        }

        // if route is not found we return empty list
        return new ArrayList<>();
    }
 // ????????????????????????????????????????????????????????????????????????????????????????????????????????
    public static double findRouteDistance(String startName, String endName){
        RailwayStation start = null;
        RailwayStation end = null;
        Set<RailwayStation> a = map.keySet();
        for (RailwayStation station : a) {
            if (station.getName().equals(startName)) {
                start = station;
            } else if (station.getName().equals(endName)) {
                end = station;
            }
        }
        try {
            // check if both stations were found
            if (start == null || end == null) {
                throw new IllegalArgumentException("One or both stations not found in the map");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }



        Map<RailwayStation, Double> distances = new HashMap<>();
        Map<RailwayStation, RailwayStation> predecessors = new HashMap<>();
        Set<RailwayStation> unvisited = new HashSet<>();
        distances.put(start, 0.0);
        unvisited.add(start);

        while (!unvisited.isEmpty()) {
            RailwayStation current = getClosestStation(unvisited, distances);
            unvisited.remove(current);

            if (current.equals(end)) {
                return distances.get( current);
            }

            for (Connection connection : map.get(current)) {
                RailwayStation next = (connection.getStation1().equals(current)) ? connection.getStation2() :
                        connection.getStation1();
                double newDistance = distances.get(current) + connection.getDistance();
                if (!distances.containsKey(next) || newDistance < distances.get(next)) {
                    distances.put(next, newDistance);
                    predecessors.put(next, current);
                    unvisited.add(next);
                }
            }
        }

        // if route is not found we return empty list
        return -1;
    }


    private static RailwayStation getClosestStation(Set<RailwayStation> stations, Map<RailwayStation, Double> distances) {
        double minDistance = Double.MAX_VALUE;
        RailwayStation closestStation = null;
        for (RailwayStation station : stations) {
            if (distances.containsKey(station) && distances.get(station) < minDistance) {
                minDistance = distances.get(station);
                closestStation = station;
            }
        }
        return closestStation;
    }

    private static List<RailwayStation> buildPath(Map<RailwayStation, RailwayStation> predecessors, RailwayStation end) {
        List<RailwayStation> path = new ArrayList<>();
        RailwayStation current = end;
        while (predecessors.containsKey(current)) {
            path.add(0, current);
            current = predecessors.get(current);
        }
        path.add(0, current);
        return path;
    }
    // here func finds two stations by their names and cals function removeConnection1 which removes connection
    public void removeConnection (String station1Name, String station2Name){
        RailwayStation station1 = null;
        RailwayStation station2 = null;
        for (RailwayStation station : map.keySet()) {
            if (station.getName().equals(station1Name)) {
                station1 = station;
            } else if (station.getName().equals(station2Name)) {
                station2 = station;
            }
        }
        removeConnection1(station1, station2);
    }

    private void removeConnection1(RailwayStation station1, RailwayStation station2){


        ArrayList<Connection> connections1 = map.get(station1);
        ArrayList<Connection> connections2 = map.get(station2);

        if(connections1 == null && connections2 == null)
            return;

        connections1.removeIf(connection -> connection.getStation1().equals(station2)
                && connection.getStation2().equals(station1));
        connections2.removeIf(connection -> connection.getStation1().equals(station1)
                && connection.getStation2().equals(station2));
    }
    // finds two stations and cals func deleteStation1 to delete stations and connections to it
    public void deleteStation(String stationName){
        RailwayStation stationToRemove = null;
        for (RailwayStation station : map.keySet()) {
            if (station.getName().equals(stationName)) {
                stationToRemove = station;
                break;
            }
        }
        deleteStation1(stationToRemove);
    }


    private void deleteStation1(RailwayStation stationToRemove){

        if (stationToRemove != null) {
            for (ArrayList<Connection> connections : map.values()) {
                connections.removeIf(connection ->
                        connection.getStation1().equals(stationToRemove) ||
                                connection.getStation2().equals(stationToRemove));
            }

            // Remove the station from the map
            map.remove(stationToRemove);
        }
    }

}
