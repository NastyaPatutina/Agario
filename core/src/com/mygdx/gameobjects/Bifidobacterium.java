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
import java.util.Random;

/**
 *
 * @author npatutina
 */
public class Bifidobacterium extends SimpleBacterium {
  
    public Bifidobacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
         _color = Color.WHITE;
    }
    public Bifidobacterium(GameWorld world, int maxRadius) {
        super(world, maxRadius);
         _color = Color.WHITE;

    }

}
