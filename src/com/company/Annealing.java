package com.company;

import java.util.*;


public class Annealing {
    private static double TEMPERATURE = 1000000000;
    private static double COOLING_TEMP = 0.5;

    public static void main(String[] args) {
        ArrayList<Point> cities = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        System.out.print("Wprowadz wspolrzedne: ");
        String coordinates = scan.nextLine();

        List<String> coordList = Arrays.asList(coordinates.split(","));
        List<List<Integer>> coordsGrouped = new ArrayList<>();
        for (int i = 0; i < (coordList.size())/2; i++) {
            List<Integer> bufferList = new ArrayList<>();
            bufferList.add(Integer.valueOf(coordList.get(2 * i)));
            bufferList.add(Integer.valueOf(coordList.get(2 * i + 1)));
            coordsGrouped.add(bufferList);
        }
        System.out.println(coordsGrouped);

        int iterator = 0;
        for (List<Integer> currList: coordsGrouped) {
            cities.add(new Point(currList.get(0), currList.get(1), iterator++));
        }

        Path curr = new Path(cities);
        Path best = new Path(curr.getPoints());


        while (TEMPERATURE > 0.01) {
            Path neighbor = curr.duplicate();
            randomSwap(neighbor, curr.numberOfPoints());

            int currentDistance   = curr.getPathLength();
            int neighbourDistance = neighbor.getPathLength();

            if (probability(currentDistance, neighbourDistance, TEMPERATURE) > Math.random()) {
                curr = new Path(neighbor.getPoints());
            }

            if (curr.getPathLength() < best.getPathLength()) {
                best = new Path(curr.getPoints());
            }

            TEMPERATURE *= COOLING_TEMP;
        }
        System.out.println("Droga komiwojazera: " + best);
        new Visualizer(best.getPoints());
    }
    public static double probability(int currentDistance, int newDistance, double temperature) {
        if (newDistance < currentDistance) {
            return 1.0;
        }
        return Math.exp((currentDistance - newDistance) / temperature);
    }

    public static void randomSwap(Path neighbor, int numberOfPoints){
        int rndPointIndex1 = randomInt(0, numberOfPoints);
        int rndPointIndex2 = randomInt(0, numberOfPoints);

        while(rndPointIndex1 == rndPointIndex2) {
            rndPointIndex2 = randomInt(0 , numberOfPoints);
        }


        Point pointSwap1 = neighbor.getPoint(rndPointIndex1);
        Point pointSwap2 = neighbor.getPoint(rndPointIndex2);

        neighbor.setPoint(rndPointIndex2, pointSwap1);
        neighbor.setPoint(rndPointIndex1, pointSwap2);
    }

    public static int randomInt(int min , int max) {
        Random r = new Random();
        double d = min + r.nextDouble() * (max - min);
        return (int)d;
    }
}
