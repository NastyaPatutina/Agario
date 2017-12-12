/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author npatutina
 */
public abstract class SimpleBacterium extends PrimaryBacterium {
    public SimpleBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }    
    public SimpleBacterium() {
        super();
    }
}
