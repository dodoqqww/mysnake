package com.mygdx.game.desktop.controller;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.desktop.model.Point;

import com.mygdx.game.desktop.model.World;
import java.util.ArrayList;
import java.util.Random;

public class TrapControll {

    private World world;
    private Random rand;

    public TrapControll(World world) {
        this.world = world;
    }

    public void update() {
        addRandomTraps();
    }
    
    public int randomDivFive(float max){
        int random;
        do{
            random=rand.nextInt(((int) max - 50) + 5) + 25;
            
        }while(random % 5!=0);
        return random;
        
    }

    public void addRandomTraps() {
        rand = new Random();       
        switch(rand.nextInt(4)){
            case 0:{
                addVerticalRight(new Point(randomDivFive(world.getWidth()),randomDivFive(world.getHeight())));
                break;
            }
            case 1:{
                addHorizontalUp(new Point(randomDivFive(world.getWidth()),randomDivFive(world.getHeight())));
                break;
            }
            case 2:{
                addVerticalLeft(new Point(randomDivFive(world.getWidth()),randomDivFive(world.getHeight())));
                break;
            }
            case 3:{
                addHorizontalDown(new Point(randomDivFive(world.getWidth()),randomDivFive(world.getHeight())));
                break;
            }
        }
            
       
    }

    public void addVerticalRight(Point point) {
        ArrayList line = new ArrayList<Point>();
        for (int x = point.getY(); (int) x < world.getHeight(); x = x + 5) {
            line.add(new Point(point.getX(), x));
        }
        world.getTraps().addLineToTraps(line);
    }
    
    public void addVerticalLeft(Point point) {
        ArrayList line = new ArrayList<Point>();
        for (int x = point.getY(); (int) x > 0; x = x - 5) {
            line.add(new Point(point.getX(), x));
        }
        world.getTraps().addLineToTraps(line);
    }

    public void addHorizontalUp(Point point) {
        ArrayList line = new ArrayList<Point>();
        for (int x = point.getX(); (int) x < world.getWidth(); x = x + 5) {
            line.add(new Point(x, point.getY()));
        }
        world.getTraps().addLineToTraps(line);
    }
    
    public void addHorizontalDown(Point point) {
        ArrayList line = new ArrayList<Point>();
        for (int x = point.getX(); (int) x > 0; x = x - 5) {
            line.add(new Point(x, point.getY()));
        }
        world.getTraps().addLineToTraps(line);
    }

    public Rectangle isHitSnake() {
        for (Rectangle it : world.getSnake().getSnakeBody()) {
            for (int x = 0; x < world.getTraps().getLineTraps().size(); x++) {
                for (int y = 0; y < world.getTraps().getLineTraps().get(x).size(); y++) {
                    if (world.getTraps().getLineTraps().get(x).get(y).getX() == it.x && world.getTraps().getLineTraps().get(x).get(y).getY() == it.y) {
                        return new Rectangle(it.x,it.y,it.width,it.height);
                    }
                }
            }
        }
        return null;
    }
    
    public Rectangle isHitSnakeParts() {
        for (Rectangle it : world.getSnake().getSnakeParts()) {
            for (int x = 0; x < world.getTraps().getLineTraps().size(); x++) {
                for (int y = 0; y < world.getTraps().getLineTraps().get(x).size(); y++) {
                    if (world.getTraps().getLineTraps().get(x).get(y).getX() == it.x && world.getTraps().getLineTraps().get(x).get(y).getY() == it.y) {
                        return new Rectangle(it.x,it.y,it.width,it.height);
                    }
                }
            }
        }
        return null;
    }

}
