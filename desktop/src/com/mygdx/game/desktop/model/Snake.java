package com.mygdx.game.desktop.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Rectangle> snakeBody;
    private LinkedList<Rectangle> snakeParts;
    private float Velocity;

    public Snake(Vector2 snakePos,float speed) {
        this.snakeBody = new LinkedList<Rectangle>();
        this.snakeParts = new LinkedList<Rectangle>();
        this.Velocity=speed;
        snakeRectsDraw(snakePos.x, snakePos.y,30*5);
    }

    
    public LinkedList<Rectangle> getSnakeParts() {
        return snakeParts;
    }
    
    public void addPart(){
        snakeBody.offer(snakeBody.getLast());
    }

    public LinkedList<Rectangle> getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(LinkedList<Rectangle> snakeBody) {
        this.snakeBody = snakeBody;
    }
    

    public void snakeRectsDraw(float x, float y,float size) {
        for (float z = x; z <= x+size; z=z+5) {
            snakeBody.offer(new Rectangle(z, y, 5f,5));
        }

    }

    
    
    

}
