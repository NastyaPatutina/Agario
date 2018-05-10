/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.mygdx.gameobjects.connect.Changing;
import com.mygdx.gameworld.GameWorld;

/**
 *
 * @author npatutina
 */
public abstract class PredatoryBacterium extends PrimaryBacterium {

    public PredatoryBacterium(Changing connection, float x, float y, int radius, GameWorld world) {
        super(connection, x, y, radius, world);
    }

    public PredatoryBacterium(Changing connection) {
        super(connection);
    }

    public void eat(PrimaryBacterium food) {
        food.change(this);
    }

}
