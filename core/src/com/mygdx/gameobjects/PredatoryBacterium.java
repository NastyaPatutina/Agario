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
}
