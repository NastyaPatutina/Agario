package com.mygdx.agario;

import AgarioHelpers.AssetLoader;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.GameScreen;

public class Agario extends Game {

    @Override
    public void create() {
        Gdx.app.log("Agario", "created");
        AssetLoader.load();
        setScreen(new GameScreen());
    }
    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}