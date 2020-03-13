package com.mygdx.game.desktop.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.LinkedList;

public class World {

    private Snake snake;
    private Trap traps;
    private Vector2 snakePos = new Vector2();
    private float height;
    private float width;
    private LinkedList<Rectangle> mapBorder;

    public World(float CAM_WIDTH, float CAM_HEIGHT) {
        snakePos.set(CAM_WIDTH / 2 , CAM_HEIGHT / 2);
        this.mapBorder = new LinkedList<Rectangle>();
        mapBorderRects(CAM_WIDTH,CAM_HEIGHT);
        this.snake = new Snake(snakePos,10f);
        this.traps=new Trap();
        this.height=CAM_HEIGHT;
        this.width=CAM_WIDTH;
    }

    public Snake getSnake() {
        return snake;
    }

    public LinkedList<Rectangle> getMapBorder() {
        return mapBorder;
    }

    public void mapBorderRects(float CAM_WIDTH, float CAM_HEIGHT) {
        for (int x = 5; x <= CAM_WIDTH; x = x + 5) {
            for (int y = 5; y <= CAM_HEIGHT; y = y + 5) {
                 if(x==5 || y==5 || y==CAM_HEIGHT || x==CAM_WIDTH){
                     mapBorder.offer(new Rectangle(x - 5, y - 5, 5f, 5));
                 }
            }
        }

    }

    public Trap getTraps() {
        return traps;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    

}
