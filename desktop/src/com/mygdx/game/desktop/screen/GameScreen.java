package com.mygdx.game.desktop.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.desktop.controller.SnakeControll;
import com.mygdx.game.desktop.controller.TrapControll;
import com.mygdx.game.desktop.model.World;
import com.mygdx.game.desktop.view.Renderer;

public class GameScreen implements Screen, InputProcessor {

    private static final float CAM_WIDTH = Gdx.graphics.getWidth();
    private static final float CAM_HEIGHT = Gdx.graphics.getHeight();

    private Renderer renderer;
    private World world;

    private SnakeControll snakeControll;
    private TrapControll trapControll;

    private float sum;

    @Override
    public void show() {
        this.world = new World(CAM_WIDTH, CAM_HEIGHT);
        this.renderer = new Renderer(world, CAM_WIDTH, CAM_HEIGHT);
        snakeControll = new SnakeControll(world);
        Gdx.input.setInputProcessor(this);
        trapControll = new TrapControll(world);
        sum = 0;
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        snakeControll.update(f);
        

        if (trapControll.isHitSnake() != null && world.getTraps().isLive()) {
            snakeControll.cutSnake(trapControll.isHitSnake());
        } else if (trapControll.isHitSnakeParts() != null && world.getTraps().isLive()) {
            snakeControll.cutSnakeParts(trapControll.isHitSnakeParts());
        }
        
        if(snakeControll.isHitPart()!=null){   
            world.getSnake().addPart();
            snakeControll.cutSnakeParts(snakeControll.isHitPart());    
        }
        
        
        
        sum += f;
        if (sum > 3) {
            trapControll.update();
            sum = 0;
        }

        renderer.render(f);

    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.UP) {
            snakeControll.keyUp();
        }
        if (keycode == Keys.DOWN) {
            snakeControll.keyDown();
        }
        if (keycode == Keys.LEFT) {
            snakeControll.keyLeft();
        }
        if (keycode == Keys.RIGHT) {
            snakeControll.keyRight();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        /*if (keycode == Keys.W) {
            snakeControll.keyUp();
        }
        if (keycode == Keys.DOWN) {
            snakeControll.keyDown();
        }
        if (keycode == Keys.LEFT) {
            snakeControll.keyLeft();
        }
        if (keycode == Keys.RIGHT) {
            snakeControll.keyRight();
        }*/
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
