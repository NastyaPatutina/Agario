/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.mygdx.gameworld.GameWorld;

/**
 *
 * @author npatutina
 */
public class Staphylococcus extends SimpleBacterium implements ToxicBacterium  {

    public Staphylococcus(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }

    @Override
    public void poison(PredatoryBacterium bacter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
