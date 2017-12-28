/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameworld.GameWorld;

/**
 *
 * @author npatutina
 */
public class Azotobacter extends SimpleBacterium implements ProfitableBacterium {

    public Azotobacter(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }
    public Azotobacter(GameWorld world, int maxRadius) {
        super(world, maxRadius);
         _color = Color.BLUE;

    }
    
    @Override
    public void improve(PredatoryBacterium bacter) {
        bacter.addRadius(this.getRadius()/2);
    }
    
}
