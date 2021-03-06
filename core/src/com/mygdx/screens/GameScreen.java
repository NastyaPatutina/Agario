/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.screens;

import AgarioHelpers.InputHandler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;
import modules.ModuleEngine;

/**
 *
 * @author anast
 */
public class GameScreen implements Screen {

    private GameRenderer renderer;

    public GameScreen(boolean isBot) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        GameWorld myWorld = new GameWorld(screenWidth, screenHeight, isBot);
        renderer = new GameRenderer(myWorld, (int) gameHeight);
        if (isBot) {
            ModuleEngine.main(null, renderer, myWorld);
        }
        Gdx.app.log("GameScreen", "Attached");
        Gdx.input.setInputProcessor(new InputHandler(renderer.getGameWorld().getPlayerBacterium()));

    }

    @Override
    public void render(float delta) {
        // Мы передаем delta в update метод, для того, чтобы мы могли сделать фреймо-зависимые вычисления
        renderer.getGameWorld().update(delta);// GameWorld updates 

        if (renderer.getGameWorld().isGameOver()) {
            // Обнулим все перменные, перейдем в GameState.READ
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
        }
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }

}
