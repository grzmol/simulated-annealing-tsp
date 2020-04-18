package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Visualizer extends JFrame {
    private List<Point> points;

    public Visualizer(List<Point> points) {
        super("Wizualizacja");
        this.points = points;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }
    public void paint(Graphics g) {
        super.paint(g);

        for(Point p : points){
            g.fillRect(p.getX(), p.getY(), 5, 5);
            g.drawString(String.valueOf(p.getPointIndex()), p.getX()-5, p.getY()-5);
        }
        for(int i = 0; i<points.size()-1; i++){
            g.drawLine(points.get(i).getX(), points.get(i).getY(),points.get(i+1).getX(), points.get(i+1).getY());
        }
        if(points.size() > 2){
            g.drawLine(points.get(0).getX(), points.get(0).getY(),points.get(points.size()-1).getX(), points.get(points.size()-1).getY());
        }

    }
}
