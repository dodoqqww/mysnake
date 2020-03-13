package com.mygdx.game.desktop.model;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;



public class Trap {

    private ArrayList<Point> plantedTraps;
    private ArrayList<ArrayList<Point>> lineTraps;
    private boolean live;

    public Trap() {
        this.lineTraps=new ArrayList<> ();
        this.plantedTraps = new ArrayList<>();
        this.live = false;
    }

    public ArrayList<ArrayList<Point>> getLineTraps() {
        return lineTraps;
    }

    public void addLineToTraps(ArrayList<Point> line){
        lineTraps.add(line);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void addTraps(Point traps) {
        this.plantedTraps.add(traps);
    }

}
