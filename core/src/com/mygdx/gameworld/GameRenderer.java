/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author anast
 */
public class GameRenderer {

    private GameWorld myWorld;
    
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;



    public GameRenderer(GameWorld world) {
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        myWorld = world;
    }
    
    public void render() {
        Gdx.app.log("GameRenderer", "render");
    }
}
