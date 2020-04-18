package com.company;

public class Point {
    private int pointIndex;
    private int x;
    private int y;


    public Point(int x, int y, int index){
        this.pointIndex = index;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPointIndex() {
        return pointIndex;
    }

    public String getCityCoords(){
        return "(" + x + "," + y + ")";
    }
    public double distanceToPoint(Point point) {
        int x = Math.abs(getX() - point.getX());
        int y = Math.abs(getY() - point.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

}
