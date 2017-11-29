/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import AgarioHelpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gameobjects.PrimaryBacterium;
import com.mygdx.gameobjects.SimpleBacterium;
import java.util.ArrayList;

/**
 *
 * @author anast
 */
public class GameRenderer {

    private GameWorld myWorld;
    
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;
    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }
    
    public void render() {
        // мы уберем это из цикла далее, для улучшения производительности
        PrimaryBacterium bacterium = myWorld.getPlayerBacterium();
        ArrayList<SimpleBacterium> bacteriums = myWorld.getSimpleBacteriums();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Стартуем ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);

        // Отрисуем Background цвет
        //shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        //shapeRenderer.rect(0, 0, 136, midPointY + 66);
        // Заканчиваем ShapeRenderer
        shapeRenderer.end();
        // Стартуем SpriteBatch
       
        batcher.begin();
        // Отменим прозрачность
        // Это хорошо для производительности, когда отрисовываем картинки без прозрачности
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, 0, 500, 500);


        batcher.end(); 
        shapeRenderer.begin(ShapeType.Filled);
        
        // Отменим прозрачность
        // Это хорошо для производительности, когда отрисовываем картинки без прозрачности
        shapeRenderer.setColor(bacterium.getColor());
        shapeRenderer.circle(bacterium.getX(), bacterium.getY(), bacterium.getRadius());
        for(SimpleBacterium bacter : bacteriums) {
            shapeRenderer.setColor(bacter.getColor());
            shapeRenderer.circle(bacter.getX(), bacter.getY(), bacter.getRadius());   
        }
        // Заканчиваем SpriteBatch
        shapeRenderer.end();     
        

        
    }
}
