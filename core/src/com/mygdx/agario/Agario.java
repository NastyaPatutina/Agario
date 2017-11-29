package com.mygdx.agario;

import AgarioHelpers.AssetLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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