package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {
    private List<Point> points;
    private int distance;

    public Path(List<Point> points){
        this.points = new ArrayList<>(points);
        Collections.shuffle(this.points);
    }

    public Point getPoint(int index){
        return  points.get(index);
    }


    public int getPathLength() {
        int distance = 0;
        for (int index = 0; index < points.size(); index++) {
            Point starting = getPoint(index);
            Point destination;
            if (index + 1 < points.size()) {
                destination = getPoint(index + 1);
            } else {
                destination = getPoint(0);
            }
            distance += starting.distanceToPoint(destination);
        }
        return distance;
    }

    public List<Point> getPoints() {
        return points;
    }


    public Path duplicate() {
        return new Path(new ArrayList<>(points));
    }

    public int numberOfPoints() {
        return points.size();
    }



    public void setPoint(int index, Point point) {
        points.set(index, point);
        distance = 0;
    }
    @Override
    public String toString() {
        String s = String.valueOf(getPoint(0).getPointIndex());
        for (int i = 1; i < numberOfPoints(); i++) {
            s += "," + getPoint(i).getPointIndex();
        }
        return s;
    }
}
