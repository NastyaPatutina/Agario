/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld.areas;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author npatutina
 */
public abstract class DinamicArea extends Area{
    
    abstract public float getModificationVelocity();
    
    public DinamicArea(Rectangle rect) {
        super(rect);
    }
    
}
