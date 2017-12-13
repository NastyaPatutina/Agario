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
public abstract class PredatoryBacterium extends PrimaryBacterium{
     public PredatoryBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
     }
     public PredatoryBacterium() {
        super();
     }
    public void eat(PrimaryBacterium food) {
        if(food instanceof ProfitableBacterium){
            ((ProfitableBacterium)food).improve(this);
        } else         
        if(food instanceof ToxicBacterium){
            ((ToxicBacterium)food).poison(this);
        } else{         
            radius += food.getRadius()/3;
            if (radius > 20)
                radius =20; 
            diffVelocity = 15 - radius;
        }
    }
    
}