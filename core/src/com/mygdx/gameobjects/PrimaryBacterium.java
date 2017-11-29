/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author anast
 */
public class PrimaryBacterium { 
    Vector2 position;
    //private Vector2 acceleration;
    Vector2 velocity;

    int radius;
    
    public PrimaryBacterium(float x, float y, int radius) {
        this.radius = radius;
        position = new Vector2(x, y);
        //acceleration = new Vector2(0, 460);
        velocity = new Vector2(0, 0);
    }
    
    public void update(float delta) {
        //velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 500) {
            velocity.y = 500;
        }
        if (velocity.x > 500) {
            velocity.x = 500;
        }
        
        position.add(velocity.cpy().scl(delta));
    }
    
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRadius() {
        return radius;
    }

}
