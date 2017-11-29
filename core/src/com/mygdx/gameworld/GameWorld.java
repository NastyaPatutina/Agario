/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameobjects.PlayerBacterium;
import com.mygdx.gameobjects.PrimaryBacterium;

/**
 *
 * @author anast
 */
public class GameWorld {
    
    private PlayerBacterium _primaryBacterium;

    public GameWorld(int midPointY) {
        _primaryBacterium = new PlayerBacterium(33, midPointY - 5, 5);
    }

    public void update(float delta) {
        _primaryBacterium.update(delta);
    }

    public PrimaryBacterium getPrimaryBacterium() {
        return _primaryBacterium;

    }

    public PlayerBacterium getPlayerBacterium() {
        return _primaryBacterium;
    }
}
