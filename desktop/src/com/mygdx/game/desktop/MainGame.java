/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.desktop;

import com.badlogic.gdx.Application;
import com.mygdx.game.desktop.screen.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


/**
 *
 * @author dodo
 */
public class MainGame extends Game {

    @Override
    public void create() {
        
        setScreen(new GameScreen());
    }
    
    
    
}
