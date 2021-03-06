/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import AgarioHelpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gameobjects.*;
import com.mygdx.gameworld.areas.Area;
import java.util.ArrayList;
import modules.Module;
import modules.ModuleEngine;

/**
 *
 * @author anast
 */
public class GameRenderer {

    private GameWorld myWorld;

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;
    private int gameHeight;
    private Module module;

    public GameRenderer(GameWorld world, int gameHeight) {
        myWorld = world;
        this.gameHeight = gameHeight;
        myWorld.setGameHeight(gameHeight);

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public GameWorld getGameWorld() {
        return myWorld;
    }

    public void render() {
        // мы уберем это из цикла далее, для улучшения производительности
        ArrayList<PrimaryBacterium> bacteriums = myWorld.getBacteriums();
        ArrayList<Area> areas = myWorld.getAreas();
        ArrayList<Whizzbang> whizzbangs = myWorld.getWhizzbangs();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (this.module != null) {
            this.module.run();
        }

        // Стартуем ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);
        // Стартуем SpriteBatch
        batcher.begin();

        // Отменим прозрачность
        // Это хорошо для производительности, когда отрисовываем картинки без прозрачности
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, 0, 1500, 1500);

        for (Area area : areas) {
            Color ligthColor = new Color(area.getColor().r + (float) 0.75, area.getColor().g + (float) 0.75, area.getColor().b + (float) 0.75, area.getColor().a + (float) 0.75);
            shapeRenderer.setColor(ligthColor);
            shapeRenderer.rect(area.getRectangle().x, area.getRectangle().y, area.getRectangle().width, area.getRectangle().height);
        }

        for (PrimaryBacterium bacter : bacteriums) {
            Color darkCircle = new Color(bacter.getColor().r * 3 / 4, bacter.getColor().g * 3 / 4, bacter.getColor().b * 3 / 4, bacter.getColor().a * 3 / 4);
            shapeRenderer.setColor(darkCircle);
            shapeRenderer.circle(bacter.getX(), bacter.getY(), bacter.getRadius());
            shapeRenderer.setColor(bacter.getColor());
            shapeRenderer.circle(bacter.getX(), bacter.getY(), (float) (bacter.getRadius() * 0.9));
        }

        for (Whizzbang whizzbang : whizzbangs) {
            Color darkCircle = new Color(Color.RED.r * 3 / 4, Color.RED.g * 3 / 4, Color.RED.b * 3 / 4, Color.RED.a * 3 / 4);
            shapeRenderer.setColor(darkCircle);
            shapeRenderer.circle(whizzbang.getX(), whizzbang.getY(), whizzbang.getRadius());
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(whizzbang.getX(), whizzbang.getY(), (float) (whizzbang.getRadius() * 0.9));
        }

        batcher.end();
        // Заканчиваем SpriteBatch
        shapeRenderer.end();

    }

    public void setModule(Module module) {
        this.module = module;
    }

}
