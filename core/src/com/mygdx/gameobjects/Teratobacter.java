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
public class Teratobacter extends BotBacterium implements ProfitableBacterium{

    public Teratobacter(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }
    
    public Teratobacter(GameWorld world, int maxRadius) {
        super(world, maxRadius);
         _color = Color.FOREST;

    }

    @Override
    public void improve(PredatoryBacterium bacter) {
        bacter.addRadius(this.getRadius());
     //   bacter.velocity.x += 2;        
     //   bacter.velocity.y += 2;
    }
    
}
