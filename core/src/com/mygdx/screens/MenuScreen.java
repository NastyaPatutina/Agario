/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.screens;

import AgarioHelpers.AssetLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 *
 * @author npatutina
 */
public class MenuScreen implements Screen {

    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public MenuScreen() {

        /// create stage and set it as input processor
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }

    @Override
    public void render(float delta) {
        Gdx.app.log("MenuScreen", "render");

        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("MenuScreen", "resizing");
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void show() {
        Gdx.app.log("MenuScreen", "show called");
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.top();

        // temporary until we have asset manager in

        //create buttons
        TextButton newUserGame = new TextButton("New User Game", AssetLoader.skin);        
        TextButton newBotGame = new TextButton("New Bot Game", AssetLoader.skin);

        TextButton exit = new TextButton("Exit", AssetLoader.skin);

        //Add listeners to buttons
        newUserGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(false));
            }
        });
        
        //Add listeners to buttons
        newBotGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(true));
            }
        });
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //add buttons to table
        table.row();
        table.add(newUserGame).fillX().uniformX();
        table.row();
        table.add(newBotGame).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        stage.addActor(table);
    }

    @Override
    public void hide() {
        Gdx.app.log("MenuScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("MenuScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("MenuScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
        stage.dispose();

    }
}
