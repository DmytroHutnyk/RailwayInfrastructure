import RailCars.*;

import java.io.*;
import java.util.*;


public class Main {

    public static boolean appIsAlive = false;

    private static RailMap railMap;

    public static void main(String[] args) {
        try {
            appIsAlive = true;
            RailMap railMap = RailMap.createMap();
            BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream("src/data/cities.txt")));
            Set<String> cities =  new HashSet<>();
            String line;
            while ((line = br.readLine()) != null) {
                cities.add(line.replace("    ", ""));
            }
            ArrayList<String> citiesList = new ArrayList<>(cities);
            List<RailwayStation> stations = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                RailwayStation s = new RailwayStation(citiesList.remove((int) (Math.random() * citiesList.size())));
                stations.add(s);
            }

            for (RailwayStation s: stations) {
                railMap.addStation(s);
            }
            boolean key = true;

            for (int j = 0; j < stations.size()-1; j++) {
                RailwayStation s = stations.get(j);
                railMap.addConnection(s.getName(), stations.get(j+1).getName(), Math.random()*400+200);
                for (int i = 0; i < 10; i++) {
                    RailwayStation s1 = stations.get((int) (Math.random() * 100));
                    while (s1.getName().equals(s.getName()))//------------------ equals
                        s1 = stations.get((int) (Math.random() * 100));
                    railMap.addConnection(s.getName(), s1.getName(), Math.random() * 400 + 300);

                }
            }

            br.close();


            br = new BufferedReader( new InputStreamReader( new FileInputStream("src/data/trains.txt")));
            Set<String> names =  new HashSet<>();

            while ((line = br.readLine()) != null) {
                names.add(line.replace("    ", ""));
            }
            ArrayList<String> trainsNames = new ArrayList<>(names);
            List<Locomotive> locomotives = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                RailwayStation start = railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100));
                RailwayStation end = railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100));
                while (start.getName().equals(end.getName()))
                    end = railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100));

                locomotives.add(new Locomotive((int)(Math.random()*11)+5,
                                            Math.random()*3001+1500,
                                            (int)(Math.random()*6)+3,
                                            trainsNames.remove((int) (Math.random() * trainsNames.size())),
                                            Math.random()*131+50,
                                            railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100)),
                                            start,
                                            end
                        ));
            }
            br.close();
            for (Locomotive l :locomotives) {
                TrainSet t = new TrainSet(l);
                int max = (int)(Math.random() * 6 + 5);
                int i = 0;
                boolean maxWeightIsNotReached = true;
                while (i<max && maxWeightIsNotReached){
                    try {
                        switch ((int) (Math.random() * 10)) {
                            case 0 -> t.addRailCar(new PassengerRailCar("Malta", "Safe",
                                    (Math.random() * 100), (int) (Math.random() * 40),
                                    Math.random() > 0.5));
                            case 1 -> t.addRailCar(new ResturantRailCar("Malta", "Safe",
                                    (Math.random() * 100), (int) (Math.random() * 50) + 150,
                                    (int) (Math.random() * 400), (int) (Math.random() * 300),
                                    Math.random() > 0.5));
                            case 2 -> t.addRailCar(new BaggageMailRailCar("Malta", "Safe",
                                    (Math.random() * 100), (int) (Math.random() * 60), Math.random() * 10));
                            case 3 -> t.addRailCar(new ExplosiveRailCar("Reporatte", "Dangerous",
                                    (Math.random() * 100), (Math.random() * 300),
                                    (int) (Math.random() * 4) + 1));
                            case 4 -> t.addRailCar(new GaseousRailCar("Reporatte", "Not safe",
                                    (Math.random() * 100), (Math.random() * 300), (Math.random() * 50)));
                            case 5 -> t.addRailCar(new LiquidRailCar("JS Co.", "Safe",
                                    (Math.random() * 100), (Math.random() * 300), Math.random() * 500,
                                    Math.random() * 10 + 10));
                            case 6 -> t.addRailCar(new PostOfficeRailCar("JS Co.", "Safe",
                                    (Math.random() * 100), (int) (Math.random() * 400), (int) (Math.random() * 400),
                                    (int) (Math.random() * 400), (Math.random() * 150)));
                            case 7 -> t.addRailCar(new LiquidToxicRailCar("Toxins Co.", "Super Dangerous",
                                    (Math.random() * 100), (Math.random() * 100), (Math.random() * 50),
                                    (Math.random() * 10) + 10));
                            case 8 -> t.addRailCar(new RefrigeratedRailCar("Meat and Fruit co.", "Unsafe",
                                    (Math.random() * 100), (Math.random() * 100), (Math.random() * -100) + 35));
                            case 9 -> t.addRailCar(new ToxicRailCar("Toxins Co.", "Super Dangerous",
                                    (Math.random() * 100), (Math.random() * 100), (int) (Math.random() * 6) + 1));
                            default -> {
                            }
                        }
                        i++;
                    }catch (Exception e){
                        maxWeightIsNotReached = false;
                    }
                }
            }
            Thread logger = new Thread( () ->{
                try {

                BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream("src/data/AppState.txt")));
                int i = 0;
                while (appIsAlive){
                    bw.write("Log on: "+i+" seconds");
                    bw.write(TrainSet.getAllTrainSets().toString());
                    bw.newLine();
                    bw.newLine();
                    bw.flush();
                    i+=5;
                    Thread.sleep(5000);
                }
                    System.out.println("Logger no more");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            logger.start();

            ArrayList<Thread> threads = new ArrayList<>();
            for (TrainSet t : TrainSet.getAllTrainSets()) {
                Thread s = new Thread(t);
                threads.add(s);
                s.start();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }




        railMap = RailMap.createMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("To quit from application enter quit");
        String line;
        while ( !( line  = scanner.nextLine() ).equals( "quit")) {
            try {

                String[] params = line.split(" ");
                switch (params[0]) {
                    case "add":
                        switch (params[1]) {
                            case "train":
                                RailwayStation start = railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100));
                                RailwayStation end = railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100));
                                while (start.getName().equals(end.getName()))
                                    end = railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100));
                                Locomotive locomotive = new Locomotive((int)(Math.random()*11)+5,
                                        Math.random()*3001+1500,
                                        (int)(Math.random()*6)+3, params[2],
                                        Math.random()*131+50,
                                        railMap.getMap().keySet().stream().toList().get((int) (Math.random() * 100)),
                                        start, end);
                                TrainSet trainSet = new TrainSet(locomotive);
                                System.out.println(trainSet+"Created");
                                new Thread(trainSet).start();
                                break;
                            case "connection":
                                railMap.addConnection(params[2], params[3], Double.parseDouble(params[4]));
                                System.out.println("added");
                                break;
                            case "station":
                                railMap.addStation( new RailwayStation(params[2]));
                                System.out.println("added");
                                break;
                            case "railcar":
                                TrainSet t = TrainSet.getAllTrainSets().stream().filter(t1 -> t1.getId() == Integer.parseInt(params[2])).toList().get(0);
                                if( t == null ) {
                                    System.out.println("There is no such trainset");
                                    break;
                                }
                                switch (params[3]){
                                    case "PassengerRailCar" :
                                        try{
                                            t.addRailCar(new PassengerRailCar("Malta", "Safe", (Math.random() * 100), (int) (Math.random() * 40), Math.random() > 0.5));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "RestaurantRailCar"  :
                                        try{
                                            t.addRailCar(new ResturantRailCar("Malta", "Safe",
                                                    (Math.random() * 100), (int) (Math.random() * 50) + 150,
                                                    (int) (Math.random() * 400), (int) (Math.random() * 300),
                                                    Math.random() > 0.5));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "BaggageMailRailCar"  :
                                        try{
                                            t.addRailCar(new BaggageMailRailCar("Malta", "Safe",
                                                    (Math.random() * 100), (int) (Math.random() * 60),
                                                    Math.random() * 10));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "ExplosiveRailCar"  :
                                        try{
                                            t.addRailCar(new ExplosiveRailCar("Reporatte", "Dangerous",
                                                    (Math.random() * 100), (Math.random() * 300),
                                                    (int) (Math.random() * 4) + 1));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "GaseousRailCar"  :
                                        try{
                                            t.addRailCar(new GaseousRailCar("Reporatte", "Not safe",
                                                    (Math.random() * 100), (Math.random() * 300), (Math.random() * 50)));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "LiquidRailCar"  :
                                        try{
                                            t.addRailCar(new LiquidRailCar("JS Co.", "Safe",
                                                    (Math.random() * 100), (Math.random() * 300), Math.random() * 500,
                                                    Math.random() * 10 + 10));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "PostOfficeRailCar"  :
                                        try{
                                            t.addRailCar(new PostOfficeRailCar("JS Co.", "Safe",
                                                    (Math.random() * 100), (int) (Math.random() * 400), (int) (Math.random() * 400),
                                                    (int) (Math.random() * 400), (Math.random() * 150)));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "LiquidToxicRailCar"  :
                                        try{
                                            t.addRailCar(new LiquidToxicRailCar("Toxins Co.", "Super Dangerous",
                                                    (Math.random() * 100), (Math.random() * 100), (Math.random() * 50),
                                                    (Math.random() * 10) + 10));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "RefrigeratedRailCar"  :
                                        try{
                                            t.addRailCar(new RefrigeratedRailCar("Meat and Fruit co.", "Unsafe",
                                                    (Math.random() * 100), (Math.random() * 100), (Math.random() * -100) + 35));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case "ToxicRailCar"  :
                                        try{
                                            t.addRailCar(new ToxicRailCar("Toxins Co.", "Super Dangerous",
                                                    (Math.random() * 100), (Math.random() * 100), (int) (Math.random() * 6) + 1));
                                            System.out.println("added");
                                        }catch (IllegalArgumentException e){
                                            System.out.println(e);
                                        }
                                        break;
                                }

                            default:
                                System.out.println("To quit from app enter quit");
                                break;
                        }
                        break;

                    case "remove":
                        switch (params[1]) {
                            case "train":
                                TrainSet a = TrainSet.getAllTrainSets().stream().filter(t -> t.getId() == Integer.parseInt(params[2])).toList().get(0);
                                if( a == null)
                                    System.out.println("There is no such trainset");
                                else {
                                    TrainSet.getAllTrainSets().remove(a);
                                    a.isAlive = false;
                                    System.out.println("removed");
                                }
                                break;
                            case "connection":
                                railMap.removeConnection(params[2], params[3]);
                                System.out.println("removed");
                                break;
                            case "station":
                                railMap.deleteStation(params[2]);
                                System.out.println("removed");
                                break;
                            case "railCar":
                                int trainSet = Integer.parseInt(params[2]);
                                int idRailCar = Integer.parseInt(params[3]);
                                TrainSet s1 = TrainSet.getAllTrainSets().stream().filter(t -> t.getId() == trainSet).toList().get(0);
                                s1.removeRailRoad(idRailCar);
                                System.out.println("removed");
                            default:
                                System.out.println("To quit from app enter quit");
                                break;
                        }
                        break;
                    case "getInfo":
                        TrainSet a = TrainSet.getAllTrainSets().stream().filter(t -> t.getId() == Integer.parseInt(params[1])).toList().get(0);
                        if( a == null)
                            System.out.println("There is no such trainset");
                        else {
                            System.out.println(a.status());
                        }
                    default:
                        System.out.println("To quit from app enter quit");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        appIsAlive = false;
    }
}
