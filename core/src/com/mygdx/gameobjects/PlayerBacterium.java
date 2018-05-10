/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameobjects.connect.Changing;
import com.mygdx.gameobjects.connect.Improves;
import com.mygdx.gameworld.GameWorld;

/**
 *
 * @author npatutina
 */
public abstract class PlayerBacterium extends PredatoryBacterium{

    public PlayerBacterium(float x, float y, int radius, GameWorld world) {
        super(new Improves(), x, y, radius, world);
        _color = Color.GOLD;
    }
    
}
