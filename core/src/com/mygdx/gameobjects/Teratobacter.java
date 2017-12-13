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
    
    public Teratobacter(GameWorld world) {
        super(world);
         _color = Color.FOREST;

    }

    @Override
    public void improve(PredatoryBacterium bacter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
