package com.mygdx.game.desktop.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.desktop.model.World;
import java.util.Iterator;
import java.util.LinkedList;

public class SnakeControll {

    enum Directions {
        UP, DOWN, LEFT, RIGHT
    }

    private Directions currentDir;

    private World world;

    public SnakeControll(World world) {

        this.currentDir = Directions.LEFT;
        this.world = world;
    }

    public void update(float delta) {
        
        System.out.println(world.getSnake().getSnakeBody().size()+"-"+world.getSnake().getSnakeParts().size());
        if (!foundWall() && !foundSnake()) {
            move();
        }

    }

    public boolean foundWall() {
        for (Rectangle temp : world.getSnake().getSnakeBody()) {
            if (world.getMapBorder().contains(temp)) {
                //  System.out.println("Wall");
                return true;
            }
        }
        return false;
    }

    public boolean foundSnake() {
            for (int y = 1; y < world.getSnake().getSnakeBody().size(); y++) {
                if (world.getSnake().getSnakeBody().getFirst().equals(world.getSnake().getSnakeBody().get(y))) {
                    //    System.out.println("Snake");
                    return true;
                }
            }
        
        return false;
    }

    public Rectangle isHitPart() {
        for (Rectangle part : world.getSnake().getSnakeParts()) {
            // System.out.println(world.getSnake().getSnakeBody().getFirst()+"-"+part);
            if (world.getSnake().getSnakeBody().getFirst().equals(part)) {
                return part;
            }
        }
        return null;
    }

    public void move() {
        LinkedList<Rectangle> newBody = world.getSnake().getSnakeBody();
        switch (currentDir) {
            case UP:
                newBody.removeLast();
                newBody.addFirst(new Rectangle(world.getSnake().getSnakeBody().get(0).x, world.getSnake().getSnakeBody().get(0).y + 5, 5f, 5));
                world.getSnake().setSnakeBody(newBody);
                break;
            case DOWN:
                newBody.removeLast();
                newBody.addFirst(new Rectangle(world.getSnake().getSnakeBody().get(0).x, world.getSnake().getSnakeBody().get(0).y - 5, 5f, 5));
                world.getSnake().setSnakeBody(newBody);
                break;
            case LEFT:
                newBody.removeLast();
                newBody.addFirst(new Rectangle(world.getSnake().getSnakeBody().get(0).x - 5, world.getSnake().getSnakeBody().get(0).y, 5f, 5));
                world.getSnake().setSnakeBody(newBody);
                break;
            case RIGHT:
                newBody.removeLast();
                newBody.addFirst(new Rectangle(world.getSnake().getSnakeBody().get(0).x + 5, world.getSnake().getSnakeBody().get(0).y, 5f, 5));
                world.getSnake().setSnakeBody(newBody);
                break;
        }

    }

    public void keyUp() {
        if (currentDir != Directions.DOWN) {
            currentDir = Directions.UP;
        }

    }

    public void keyDown() {
        if (currentDir != Directions.UP) {
            currentDir = Directions.DOWN;
        }
    }

    public void keyLeft() {
        if (currentDir != Directions.RIGHT) {
            currentDir = Directions.LEFT;
        }
    }

    public void keyRight() {
        if (currentDir != Directions.LEFT) {
            currentDir = Directions.RIGHT;
        }
    }

    public void cutSnake(Rectangle it) {
        if (!world.getSnake().getSnakeParts().containsAll(world.getSnake().getSnakeBody().subList(world.getSnake().getSnakeBody().indexOf(it), world.getSnake().getSnakeBody().size()))) {
            world.getSnake().getSnakeParts().addAll(world.getSnake().getSnakeBody().subList(world.getSnake().getSnakeBody().indexOf(it), world.getSnake().getSnakeBody().size()));
        }
        world.getSnake().getSnakeParts().remove(it);
 
        world.getSnake().getSnakeBody().subList(world.getSnake().getSnakeBody().indexOf(it), world.getSnake().getSnakeBody().size()).clear() ;
    }

    public void cutSnakeParts(Rectangle it) {
        world.getSnake().getSnakeParts().remove(it);
    }

}
