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
public class Staphylococcus extends SimpleBacterium implements ToxicBacterium  {

    public Staphylococcus(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }
    public Staphylococcus(GameWorld world, int maxRadius) {
        super(world, maxRadius);
         _color = Color.RED;

    }

    @Override
    public void poison(PredatoryBacterium bacter) {
        if (bacter.radius > 3)
            bacter.radius -=3;
        
        bacter.velocity.x --;        
        bacter.velocity.y --;        
    }

    
}
