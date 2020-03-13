package com.mygdx.game.desktop.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.desktop.model.Point;
import com.mygdx.game.desktop.model.World;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Renderer {

    private static World world;
    private OrthographicCamera cam;
    private ShapeRenderer renderer = new ShapeRenderer();
    private Iterator<Rectangle> it;
    private LinkedList<Rectangle> snakeRects;
    private LinkedList<Rectangle> snakePartRects;
    private LinkedList<Rectangle> mapRects;

    private ArrayList<Point> trapRects;
    private float time;
    private SpriteBatch spriteBatch;
    private float worldWidth, worldHeight;

    public Renderer(World world, float width, float height) {
        Renderer.world = world;
        worldWidth = width;
        worldHeight = height;
        this.cam = new OrthographicCamera(width, height);
        this.cam.position.set((width / 2), (height / 2), 0);
        this.cam.update();
        snakeRects = world.getSnake().getSnakeBody();
        snakePartRects = world.getSnake().getSnakeParts();
        mapRects = world.getMapBorder();

//        trapRects = world.getTraps().getPlantedTraps();
        time = 0;
        spriteBatch = new SpriteBatch();

    }

    public void render(float f) {
        time += f;

        drawSnake();
        drawParts();
        drawTraps(f);

        if (time > 3) {
            time = 0;
            world.getTraps().setLive(false);
        }

        drawBorder();
    }

    private void drawSnake() {
        Rectangle rect;
        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(Color.GOLD));
        it = snakeRects.iterator();

        rect = it.next();
        renderer.rect(rect.x, rect.y, rect.width, rect.height);

        renderer.setColor(new Color(Color.GREEN));
        while (it.hasNext()) {
            rect = it.next();
            renderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        renderer.end();
    }

    private void drawParts() {
        Rectangle rect;
        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeType.Filled);
        it = snakePartRects.iterator();
        renderer.setColor(new Color(Color.GREEN));
        while (it.hasNext()) {
            rect = it.next();
            renderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        renderer.end();
    }

    private void drawBorder() {
        Rectangle rect;
        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(new Color(1, 1, 1, 1)); //ustaw kolor renderu
        it = mapRects.iterator();
        while (it.hasNext()) {
            rect = it.next();
            renderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        renderer.end();
    }

    private void drawTraps(float f) {
        if (time < 2 && time > 1) {
            drawFlashLines(f);
        } else if (time < 3 && time > 2) {
            world.getTraps().setLive(true);
            drawLines();
        }
    }

    public void drawFlashLines(float f) {
        if (time < 1.3 && time > 1) {
            drawLines();
        } else if (time < 2 && time > 1.8) {
            drawLines();
        }
    }

    public void drawLines() {
        renderer.setProjectionMatrix(cam.combined);
        renderer.begin(ShapeType.Filled);

        for (int x = 0; x < world.getTraps().getLineTraps().size(); x++) {
            for (Point get : world.getTraps().getLineTraps().get(x)) {

                if (world.getTraps().isLive()) {
                    renderer.setColor(new Color(Color.RED));
                    renderer.rect(get.getX(), get.getY(), 5f, 5);
                } else {
                    renderer.setColor(new Color(Color.YELLOW));
                    renderer.rect(get.getX(), get.getY(), 5f, 5);
                }
            }

        }
        renderer.end();

    }

}
