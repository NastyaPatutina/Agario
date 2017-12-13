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
    public Azotobacter(GameWorld world) {
        super(world);
         _color = Color.BLUE;

    }
    
    @Override
    public void improve(PredatoryBacterium bacter) {
        bacter.radius += 3;
        bacter.velocity.x += 2;        
        bacter.velocity.y += 2;
    }
    
}
